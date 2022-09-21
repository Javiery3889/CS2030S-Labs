/**
 * This class instantiates a SCounter array to store
 * the number of service counter objects created based
 * on standard input.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class Shop {
  /** Array of SCounter objects. */
  private Array<SCounter> svcCounters;

  /** Queue object to represent entrance queue. */
  private Queue<Customer> queue;

  /** 
   * The constructor for a shop. It takes in a
   * integer as an argument. it  instantiates and
   * stores the svcCounter objects created based on
   * the integer argument given.
   *
   * @param numOfCounters The number of service counters the shop has.
   * @param counterQueueLength The maximum number of customers in counter queue.
   * @param entranceQueueLength The maximum number of customers in entrance queue.
   */
  public Shop(int numOfCounters, int counterQueueLength, int entranceQueueLength) {
    this.svcCounters = new Array<SCounter>(numOfCounters);
    this.queue = new Queue<Customer>(entranceQueueLength);
    for (int i = 0; i < numOfCounters; i++) {
      this.svcCounters.set(i, new SCounter(counterQueueLength));
    }
  }

  /** 
   * Query state of service counters by iterating
   * array of service counter objects. If no service
   * object is available, let the object be null
   *
   * @return A service counter object, object will be null if no service counter is free.
   */
  public SCounter getAvailSCounter() {
    SCounter svcCounter = null;
    for (int i = 0; i < this.svcCounters.length(); i++) {
      svcCounter = this.svcCounters.get(i);
      if (svcCounter.getAvailability()) {
        return svcCounter;
      } 
    }
    return null;
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
  
  public SCounter getOpenCounter() {
    SCounter minCounter = this.svcCounters.min();
    if (!minCounter.isQueueFull()) {
      return minCounter;
    }
    return null;
  }
  
  public String getQueue() {
    return this.queue.toString();
  }
}
