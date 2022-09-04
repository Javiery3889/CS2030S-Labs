/**
 * This class reperesents an departure event where it attempts
 * to find an available service counter.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class Departure extends Event {
  /** Customer who has arrived into the shop */
  private Customer customer;

  /** Constructor for Departure event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   */
  public Departure(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  /**
   * Prints out message after Departure event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s departed", this.customer.toString());
  }

  /**
   * Returns an empty event object to indicate the departure of a customer.
   *
   * @return Empty Event object
   */
  @Override
  public Event[] simulate() {
    return new Event[] {}; 
  }
}
