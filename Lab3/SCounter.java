/**
 * The SCounter class represents a service counter with a id and a state.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 *
 */
class SCounter implements Comparable<SCounter> {

  /** Class field used to calculate id for each service counter. */
  private static int scCount = 0;

  /** The id of a service counter associated with the object. */
  private final int id;

  /** boolean flag used to determine if service counter is available. */
  private boolean isAvailable;

  private Queue<Customer> queue;

  /**
   * Constructor for a service counter object.
   *
   * @param maxCQueue the integer value for maximum counter queue.
   */
  public SCounter(int maxCQueue) {
    this.id = scCount++;
    this.isAvailable = true;
    this.queue = new Queue<Customer>(maxCQueue);
  }

  /**
   * Getter method that returns id associated to service counter object.
   *
   * @return A integer representing the service counter's id.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Getter method that returns true if service counter is available.
   * If service counter is not available, it returns false.
   *
   * @return A boolean value representing the service counter's availability.
   */
  public boolean getAvailability() {
    return this.isAvailable;
  }

  /**
   * Setter method for the isAvailable field in the service counter object.
   *
   * @param isAvailable a boolean value to set the availability of service counter object
   */
  public void setAvailability(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public void addToQueue(Customer customer) {
    this.queue.enq(customer);
  }

  public Customer getNextCustomer() {
    return (Customer) this.queue.deq();
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean isQueueEmpty() {
    return this.queue.isEmpty();
  }

  public int getQueueLength() {
    return this.queue.length();
  }

  /**
   * Returns string representation of service counter with corresponding id.
   *
   * @return A string containing id of the service counter
   */
  @Override
  public String toString() {
    return String.format("S%d %s", this.getId(), this.queue);
  }

  @Override
  public int compareTo(SCounter svcCounter) {
    if (this.queue.length() < svcCounter.getQueueLength()) {
      return -1;
    }
    if (this.id < svcCounter.getId()) {
      return -1;
    }
    return 1;
  }
}
