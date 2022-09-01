/**
 * The SCounter class represents a service counter with a id and a state.
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY 22/23 Semester 1
 *
 */
class SCounter {

  /** Class field used to calculate id for each service counter */
  private static int scCount = 0;

  /** The id of a service counter associated with the object */
  private final int id;

  /** boolean flag used to determine if service counter is available */
  private boolean isAvailable;

  /**
   * Constructor for a service counter object
   *
   * @param id The id of a service counter
   * @param isAvailable To check if service counter is available,
   * always true by default
   */
  public SCounter() {
    this.id = scCount++;
    this.isAvailable = true;
  }

  /**
   * Getter method that returns id associated to service counter object
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
   * Setter method for the isAvailable field in the service counter object
   *
   * @param isAvailable a boolean value to set the availability of service counter object
   */
  public void setAvailability(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
}
