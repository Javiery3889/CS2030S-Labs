/**
 * This class reperesents an arrival event where it attempts
 * to find an available service counter. Afterwards a new event
 * is generated based on the availability of the counter.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class Arrival extends Event {
  /** Customer who has arrived into the shop */
  private Customer customer;

  /** Shop object that will be used to query service counter availability */
  private final Shop shop;

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
   * Prints out message after Arrival event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d arrives", this.customer.getId());
  }
  
  /**
   * Finds first available service counter and generate a service
   * -begin event. If no service counters are available, generate
   *  a departure event.
   *
   *  @return Initialises a new event depending on the availability
   *  of a service counter
   */
  @Override
  public Event[] simulate() {
    SCounter sCounter = this.shop.getAvailSCounter();
    if (sCounter == null) {
      return new Event[] {
        new Departure(this.getTime(), this.customer)
      };
    } else {
      return new Event[] {
        new ServiceBegin(this.getTime(), this.customer, sCounter)
      };
    }
  }
}
