/**
 * CS2030S Lab 0: Point.java
 * Semester 1, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author Javier Yong
 */
class Point {
  /** The x-coordinate of the point */
  private double x;

  /** The y-coordinate of the point */
  private double y;

  /**
   * Constructor for a Point. takes in a x
   * and y coordinate
   *
   * @param x The x-coordinate of the new point.
   * @param y the y-coordinate of the new point
   */
  public Point(double x, double y) {
      this.x = x;
      this.y = y;
  }
  
  /**
   * Getter method of x-coordinate for a Point.
   *
   * @return The x-coordinate of the Point.
   */
  public double getX() {
      return this.x;
  }
  
  /**
   * Getter method of y-coordinate for a Point.
   *
   * @return The y-coordinate of the Point.
   */
  public double getY() {
      return this.y;
  }
  
  /**
   * Caclulate distance of a Point from the center of a Circle.
   *
   * @param cCenter The center of the circle.
   *
   * @return The double value of the distance calculated.
   */
  public double distanceTo(Point cCenter) {
      double dX = this.x - cCenter.getX();
      double dY = this.y - cCenter.getY();
      return dX*dX + dY*dY;
  }

  /**
   * Return the string representation of this point.
   *
   * @return The string representation of this point
   */
  @Override
  public String toString() {
      return "(" + this.x + ", " + this.y + ")";
  }
}
