/**
 * A generic Immutator that takes in an object
 * that is T and returns an object that is probably T.
 *
 * @version CS2030S Lab 4 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
class Improbable<T> implements Immutator<Probably<T>, T> {
  /**
   * Returns a value of type {@code Probably<T>} from a paramter
   * supplied of type T.
   *
   * @param p The parameter to be mutated.
   * @return The value of the mutated parameter.
   */
  @Override
  public Probably<T> invoke(T p) {
    return Probably.just(p);
  }
}
