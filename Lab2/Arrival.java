/**
 * This class reperesents an arrival event where it attempts
 * to find an available service counter. Afterwards a new event
 * is generated based on the availability of the counter.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class Arrival extends Event {
  /** The customer associated with this event. */
  private Customer customer;

  /** Shop object that will be used to query service counter availability. */
  private Shop shop;

  /** Constructor for Arrival event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   * @param shop the shop associated with this event
   *
   */
  public Arrival(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Prints out message after Arrival event is simulated along with 
   * the entrance queue of the shop.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrived %s", this.customer, this.shop.getQueue());
  }

  /**
   * Finds first available service counter and generate a service
   * -begin event. If no service counters are available, check
   * if queue is full. A departure event will be generated if
   * the queue is full, else customer will be added to queue
   * using JoinQueue event.
   *
   * @return Initialises a new Event[]   
   */
  @Override
  public Event[] simulate() {
    SCounter svcCounter = this.shop.getAvailSCounter();
    if (svcCounter == null) {
      if (this.shop.isQueueFull()) {
        return new Event[] {
          new Departure(this.getTime(), this.customer)
        };
      } else {
        return new Event[] {
          new JoinQueue(this.getTime(), this.customer, this.shop)
        };
      }
    } else {
      return new Event[] {
        new ServiceBegin(this.getTime(), this.customer, svcCounter, this.shop)
      };
    }
  }
}
