/** 
 * This class that represent a Event when 
 * a customer is added to the queue.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class JoinQueue extends Event {
  /** The customer associated with this event. */
  private Customer customer;

  /** The shop object associated with this event. */
  private Shop shop;

  /** Constructor for JoinQueue event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   * @param shop the shop associated with this event
   *
   */
  public JoinQueue(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Prints out message after JoinQueue event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() + 
        String.format(": %s joined queue %s", this.customer, this.shop.getQueue());
  }

  /**
   * Adds customer to the queue and returns an empty event object.
   *
   * @return Empty Event object
   */
  @Override
  public Event[] simulate() {
    this.shop.addToQueue(this.customer);
    return new Event[] {};
  }
}
