import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Javier Yong (Group 12B) 
 * @version CS2030S AY22/23 Semester 1
 */ 
class ShopSimulation extends Simulation {
  
  /** Shop object that that stores state of service counters. */
  private final Shop shop;
  
  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    this.initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int counterQueueLength = sc.nextInt();
    int entranceQueueLength = sc.nextInt();
    
    this.shop = new Shop(numOfCounters, counterQueueLength, entranceQueueLength);
    for (int i = 0; i < this.initEvents.length; i++) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      
      Customer customer = new Customer(arrivalTime, serviceTime);
      this.initEvents[i] = new Arrival(arrivalTime, customer, this.shop);
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
