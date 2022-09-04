/** 
 * This class that represent a customer with an ID
 * and a pair of time values.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class Customer {
  /** Class field used to calculate id for each customer */
  private static int customerCount = 0;

  /** The id of a customer associated with the object */
  private final int id;
  
  /** The pair of time values that corresponds to a customer */
  private final double arrivalTime;
  private final double serviceTime;

  /**
   * Constructor for a customer object
   *
   * @param id The id of a customer
   * @param arrivalTime The arrival time of a customer
   * @param serviceTime The service time of a customer
   *
   */
  public Customer(double arrivalTime, double serviceTime) {
    this.id = customerCount++;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }
  
  /**
   * Getter method that returns id associated to customer object
   *
   * @return A integer representing the customer's id.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Getter method that returns the service time of the associated
   * customer object.
   *
   * @return A double representing customer's service time.
   */
  public double getServiceTime() {
    return this.serviceTime;
  }

  /**
   * Returns string representation of Customer with corresponding id
   *
   * @return A string containing id of the customer
   */
  @Override
  public String toString() {
    return String.format("Customer %d", this.getId());
  }
}
