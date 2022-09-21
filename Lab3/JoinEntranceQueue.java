/** 
 * This class that represent a Event when 
 * a customer is added to the queue.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class JoinEntranceQueue extends Event {
  /** The customer associated with this event. */
  private Customer customer;

  /** The shop object associated with this event. */
  private Shop shop;

  /** Constructor for JoinEntranceQueue event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   * @param shop the shop associated with this event
   *
   */
  public JoinEntranceQueue(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Prints out message after JoinEntranceQueue event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() + 
        String.format(": %s joined shop queue %s", this.customer, this.shop.getQueue());
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
