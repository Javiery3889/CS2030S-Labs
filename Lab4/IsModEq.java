/**
 * A non-generic Immutator with parameter
 * div and mod that takes in an integer val
 * and return the boolean value by checking
 * if the given value is equal to mod when
 * divided by div.
 *
 * @version CS2030S Lab 4 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
class IsModEq implements Immutator<Boolean, Integer> {
  private int div;
  private int check;
  
  /**
   * Public constructor that takes in two positive integers.
   *
   * @param div Integer value used as divisor
   * @param check Integer value used to check
   */
  public IsModEq(int div, int check) {
    this.div = div;
    this.check = check;
  }

  /**
   * Check if remainder of val when divided by div
   * is equals to check.
   *
   * @param val Integer value that will be used a quotient
   *
   * @return Boolean value that returns true if result is equal. Else returns false.
   *
   */
  @Override
  public Boolean invoke(Integer val) {
    try {
      return val % this.div == this.check;
    } catch (ArithmeticException e) {
      System.err.println("Divide by 0 operation perfomed.");
    }
    return Boolean.getBoolean("false");
  } 
}
