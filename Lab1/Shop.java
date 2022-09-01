/**
 * This class instantiates a SCounter array to store
 * the number of service counter objects created based
 * on standard input.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 */
class Shop {
  /** Array of SCounter objects */
  private SCounter[] sCounters;

  /** 
   * The constructor for a shop. It takes in a
   * integer as an argument. it  instantiates and
   * stores the sCounter objects created based on
   * the integer argument given.
   *
   * @param numOfCounters The number of service
   * counters the shop has.
   */
  public Shop(int numOfCounters) {
    this.sCounters = new SCounter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.sCounters[i] = new SCounter();
    }
  }

  /** 
   * Query state of service counters by iterating
   * array of service counter objects. If no service
   * object is available, let the object be null
   *
   * @return A service counter object, object will be
   * null if no service counter is free.
   */
  public SCounter getAvailSCounter() {
    SCounter sCounter = null;
    for (int i = 0; i < sCounters.length; i++) {
      if (this.sCounters[i].getAvailability()) {
        sCounter = this.sCounters[i];
        this.sCounters[i].setAvailability(false);
        break;
      } 
    }
    return sCounter;
  }
}
