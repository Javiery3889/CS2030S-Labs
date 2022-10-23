package cs2030s.fp;

/**
 * The Lazy generic class that can
 * enscapsulate a value and does not compute it until needed.
 *
 * @version CS2030S Lab 6 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
public class Lazy<T> implements Immutatorable<T> {
  /** Constant value to init. */
  private Constant<? extends T> init;

  /**
   * Protected constructor for Lazy class.
   *
   * @param c Takes in a Constant that produces the value.
   */
  protected Lazy(Constant<? extends T> c) {
    this.init = c;
  }
  
  /**
   * Instantiate Lazy Object.
   *
   * @param <T> Type parameter scoped for this from method.
   * @param v  Value to be instantiated with.
   * @return Lazy object created through delayed lambda expression.
   */
  public static <T> Lazy<T> from(T v) {
    return new Lazy<>(() -> v);
  }

  /**
   * Instantiate Lazy Object with Constant.
   *
   * @param <T> Type parameter scoped for this from method.
   * @param c A constant value.
   * @return Lazy object created through constrcutor above.
   */
  public static <T> Lazy<T> from(Constant<? extends T> c) {
    return new Lazy<>(c);
  }

  /**
   * Implementation of Immutator interface. Transforms a T value 
   * to {@code Lazy<R>}. 
   *
   * @param <R> Type parameter R scoped for transform method.
   * @param i Immutator object that takes is value of T type.
   *
   * @return Returns a value of {@code Lazy<R>} type.
   */
  @Override
  public <R> Lazy<R> transform(Immutator<? extends R, ? super T> i) {
    return Lazy.<R>from(() -> i.invoke(this.get()));
  }

  /**
   * Mutates {@code Lazy<R>} object.
   *
   * @param <R> Type parameter R scoped for transform method.
   * @param i Immutator object that takes is value of T type.
   *
   * @return Returns a value of {@code Lazy<R>} type.
   */
  public <R> Lazy<R> next(Immutator<? extends Lazy<? extends R>, ? super T> i) {
    return Lazy.<R>from(() -> i.invoke(this.get()).get());
  }

  /**
   * Compute value and returns it.
   *
   * @return Value to be computed and returned.
   */
  public T get() {
    return this.init.init();
  }

  /**
   * Returns String representation for Lazy.
   *
   * @return String to print.
   */
  @Override
  public String toString() {
    return this.init.init().toString();
  }
}
