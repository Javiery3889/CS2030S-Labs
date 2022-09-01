import java.util.Random;

/**
* CS2030S Lab 0: RandomPoint.java
* Semester 1, 2022/23
*
* <p>The RandomPoint class represents a Point subclass generated by a
* pseudo-random number generator (PRNG).
*
* @author Javier Yong
*/
class RandomPoint extends Point {
  /** The pseudo-random number generator, with a default seed of 1. */
  private static Random rng = new Random(1);
  
  /**
   * Sets pseudo-random number generator's seed to the given long seed.
   *
   * @param seed The seed to test.
   */
  public static void setSeed(long seed) {
      rng.setSeed(seed);
  }
  
  /** 
   * Invoke Point superclass constructor using random generated x and y coordinate.
   *
   * @param minX The minimum x coordinate value the PRNG will use.
   * @param maxX The maximum x coordinate value the PRNG will use.
   * @param minY The minimum y coordinate value the PRNG will use.
   * @param maxY The maximum y coordinate value the PRNG will use.
   *
   */
  public RandomPoint(double minX, double maxX, double minY, double maxY){
      super(minX + (maxX - minX) * rng.nextDouble(), minY + (maxY - minY) * rng.nextDouble());
  }
}
