package cs2030s.fp;

/**
 * A generic Memo class that encapsulates the value and memoizes it.
 * 
 *
 * @version CS2030S Lab 6 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
public class Memo<T> extends Lazy<T> {
  /**
   * Value of type {@code Actually<T>} to be memoized.
   */
  private Actually<T> value;

  /**
   * Private Constructor for Memo class.
   *
   * @param c Constant supplied to Lazy superclass constructor.
   * @param value Value to be assigned to Memo instnace.
   */
  private Memo(Constant<? extends T> c, Actually<T> value) {
    super(c);
    this.value = value;
  }

  /**
   * Instantiate Memo object with given value, in this case Memo is already computed.
   *
   * @param <T> Type parameter T scoped in this static method.
   * @param v Value to be instantiated with.
   * @return Memo object created through delayed lambda expression.
   */
  public static <T> Memo<T> from(T v) {
    return new Memo<>(() -> v, Actually.ok(v));
  }

  /**
   * Instantiate Memo object with a constant value, in this case Memo is uninitialised.
   * As value might be uninitialised because it is a Failure. 
   *
   * @param <T> Type parameter T scoped in this static method.
   * @param c Constant value to be instantiated with.
   * @return Memo object created through constructor..
   */
  public static <T> Memo<T> from(Constant<? extends T> c) {
    return new Memo<>(c, Actually.err(new Exception("Failure")));
  }

  /**
   * Implementation of Immutator interface. Transforms a T value
   * to {@code Memo<R>}.
   *
   * @param <R> Type parameter R scoped for transform method.
   * @param i Immutator object that takes is value of T type.
   *
   * @return Returns a value of {@code Memo<R>} type.
   */
  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> i) {
    return Memo.<R>from(() -> i.invoke(this.get()));
  }

  /**
   * Mutates {@code Memo<R>} object.
   *
   * @param <R> Type parameter R scoped for transform method.
   * @param i Immutator object that takes is value of T type.
   *
   * @return Returns a value of {@code Memo<R>} type.
   */
  @Override
  public <R> Memo<R> next(Immutator<? extends Lazy<? extends R>, ? super T> i) {
    return Memo.<R>from(() -> i.invoke(this.get()).get());
  }

  /**
   * Combines two Memo objects using lazy evaluation.
   *
   * @param <R> Return type of the combine function.
   * @param <S> The type of the second Memo object.
   * @param m The memo object to be combined with.
   * @param c The Combiner object used.
   * @return The resultant combined Memo object.
   */
  public <R, S> Memo<R> combine(Memo<? extends S> m, 
      Combiner<? extends R, ? super T, ? super S> c) {
    return Memo.<R>from(() -> c.combine(this.get(), m.get()));
  }

  /**
   * Computes value and returns it if it has not been computed.
   * Computed value will be return immediately.
   *
   * @return Value of type T to be returned when needed.
   */
  public T get() {
    T availValue = this.value.except(() -> super.get());
    this.value = Actually.ok(availValue);
    return availValue;
  }

  /**
   * Returns String representation of Memo object.
   *
   * @return String of Memo object
   */
  @Override
  public String toString() {
    return this.value.next(v -> Actually.ok(String.valueOf(v))).unless("?");
  }
}
