/**
 * This class reperesents an service-end event where customer is
 * done being served. Afterwards, a departure event is created.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class ServiceEnd extends Event {

  /** The customer associated with this event. */
  private Customer customer;

  /** The service counter associated with this event. */
  private SCounter svcCounter;

  /** The shop object associated with this event. */
  private Shop shop;

  /** Constructor for a service-end event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   * @param svcCounter The service counter associated with this event
   * @param shop the shop associated with this event
   */
  public ServiceEnd(double time, Customer customer, SCounter svcCounter, Shop shop) {
    super(time);
    this.customer = customer;
    this.svcCounter = svcCounter;
    this.shop = shop;
  }

  /**
   * Prints out message after Service-End event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() 
      + String.format(": %s service done (by %s)",
          this.customer, this.svcCounter);
  }

  /**
   * Makes service counter available after customer is done being served.
   * Checks if there are customers waiting in the queue. If there are, 
   * create a ServiceBegin event for the customer waiting in queue.
   * Finally, creates a departure event for customer associated
   * with ServiceEnd event.
   *
   * @return intialised Event[] object depending on conditions. 
   */
  @Override
  public Event[] simulate() {
    Customer nextCounterCustomer = this.svcCounter.getNextCustomer();

    if (nextCounterCustomer != null) {
      Customer nextEntranceCustomer = this.shop.getNextCustomer();
      if (nextEntranceCustomer != null) {
        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(this.getTime(), nextCounterCustomer, this.svcCounter, this.shop),
          new JoinCounterQueue(this.getTime(), nextEntranceCustomer, this.svcCounter)
        };
      } else {
        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(this.getTime(), nextCounterCustomer, this.svcCounter, this.shop)
        };
      }
    } else if (!this.shop.isQueueEmpty()) {
      Customer nextEntranceCustomer = this.shop.getNextCustomer();
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), nextEntranceCustomer, this.svcCounter, this.shop)
      };
    }
    this.svcCounter.setAvailability(true);
    return new Event[] {new Departure(this.getTime(), this.customer)};
  } 
}
