/** 
 * This class that represent a Event when 
 * a customer is added to the counter queue.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class JoinCounterQueue extends Event {
  /** The customer associated with this event. */
  private Customer customer;

  /** The shop object associated with this event. */
  private SCounter svcCounter;

  /** Constructor for JoinCounterQueue event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   * @param svcCounter the service counter associated with this event
   *
   */
  public JoinCounterQueue(double time, Customer customer, SCounter svcCounter) {
    super(time);
    this.customer = customer;
    this.svcCounter = svcCounter;
  }

  /**
   * Prints out message after JoinCounterQueue event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() + 
        String.format(": %s joined counter queue (at %s)", this.customer, this.svcCounter);
  }

  /**
   * Adds customer to the queue and returns an empty event object.
   *
   * @return Empty Event object
   */
  @Override
  public Event[] simulate() {
    this.svcCounter.addToQueue(this.customer);
    return new Event[] {};
  }
}
