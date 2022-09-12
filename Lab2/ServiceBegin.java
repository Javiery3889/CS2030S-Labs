/**
 * This class reperesents an service-begin event where customer is
 * being served. Afterwards, a service-end event is created.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class ServiceBegin extends Event {

  /** The customer associated with this event. */
  private Customer customer;

  /** The service counter associated with this event. */
  private SCounter sCounter;

  /** The shop object associated with this event. */
  private Shop shop;

  /** Constructor for a service-end event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   * @param sCounter the service counter associated with this event
   * @param shop the shop associated with this event
   */
  public ServiceBegin(double time, Customer customer, SCounter sCounter, Shop shop) {
    super(time);
    this.customer = customer;
    this.sCounter = sCounter;
    this.shop = shop;
  }

  /**
   * Prints out message after Service-Begin event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() 
        + String.format(": %s service begin (by %s)",
             this.customer, this.sCounter);
  }

  /**
   * Sets service counter to not available once customer leaves queue to service counter.
   * Creates a ServiceEnd event after service-begin event is simulated.
   *
   * @return intialised departure event object
   */
  @Override
  public Event[] simulate() {
    this.sCounter.setAvailability(false);
    return new Event[] {
      new ServiceEnd(this.getTime() + this.customer.getServiceTime(), 
          this.customer, this.sCounter, this.shop)
    };
  } 
}
