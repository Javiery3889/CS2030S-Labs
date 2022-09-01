/**
 * This class reperesents an service-end event where customer is
 * done being served. Afterwards, a departure event is created.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class ServiceEnd extends Event {

  /** The customer associated with this event */
  private Customer customer;

  /** The service counter associated with this event */
  private SCounter sCounter;

  /** Constructor for a service-end event.
   *
   * @param time The time of the event occurred
   * @param customer The customer associated with this event
   * @param shop the shop associated with this event
   */
  public ServiceEnd(double time, Customer customer, SCounter sCounter) {
    super(time);
    this.customer = customer;
    this.sCounter = sCounter;
  }
  
  /**
   * Prints out message after Service-End event is simulated.
   *
   * @return String representation of message to be printed
   */
  @Override
  public String toString() {
    return super.toString() 
        + String.format(": Customer %d service done (by Counter %d)"
            , this.customer.getId(), this.sCounter.getId());
  }
  
  /**
   * Creates a departure event after service-end event is simulated.
   *
   * @return intialised departure event object
   */
  @Override
  public Event[] simulate() {
    this.sCounter.setAvailability(true); 
    return new Event[] {
      new Departure(this.getTime(), this.customer)
     };
  } 
}
