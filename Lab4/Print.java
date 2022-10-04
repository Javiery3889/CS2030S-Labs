/**
 * A non-generic Action to print the String
 * representation of the object.
 *
 * @version CS2030S Lab 4 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
class Print implements Action<Object> {
  /**
   * Prints out string representation of the input argument
   * into standard output.
   *
   * @param o Object to be printed
   */
  public void call(Object o) {
    System.out.println(o);
  }
}
