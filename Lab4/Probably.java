/**
 * This class implements something that
 * probably is just a value but may be nothing.
 * We will never return null in this class, but
 * we may return something that contains nothing
 * where the nothing is a null.
 *
 * @author Javier Yong (Group 12B) 
 * @version CS2030S AY22/23 Semester 1
 */
class Probably<T> implements Actionable<T>, Immutatorable<T>, Applicable<T> {
  private final T value;

  private static final Probably<?> NONE = new Probably<>(null);

  /**
   * Private constructor, can only be invoked inside.
   * This is called a factory method.  We can only
   * create this using the two public static method.
   *
   * @param value The shared NOTHING.
   */
  private Probably(T value) {
    this.value = value;
  }

  /**
   * Implementation of Actionable interface. Performs custom action
   * based on the method's argument if {@code Probably<T>} is not null.
   *
   * @param a The action to be performed.
   */
  @Override
  public void act(Action<? super T> a) {
    if (this.value != null) {
      a.call(this.value);
    }
  }

  /**
   * Implementation of Immutator interface. Transforms a variable
   * of type {@code Probably<T>} to {@code Probably<R>} if value in {@code Probably<T>} is
   * not null. Else, return NONE.
   *
   * @param <R> Type parameter R scoped for transform method.
   * @param i Immutator object that takes is value of {@code Probably<T>} type.
   *
   * @return Returns a value of {@code Probably<R>} type or NONE.
   */
  @Override
  public <R> Probably<R> transform(Immutator<? extends R, ? super T> i) {
    if (this.value != null) {
      return (Probably<R>) new Probably<>(i.invoke(this.value));
    } 
    return none();
  }

  /**
   * Checks if value of {@code Probably<T>} is divisble by checker.
   * If the value of {@code Probably<T>} is null return NONE.
   * Else, if value of {@code Probably<T>} is divisible, return the object
   *
   * @param checker The special Immutator created
   * 
   * @return Returns {@code Probably<T>} object or NONE
   */
  public Probably<T> check(IsModEq checker) {
    if (this.value != null) {
      if (checker.invoke((Integer) this.value)) {
        return this;
      } else {
        return none();
      }
    }
    return none();
  }

  /**
   * Stores Immutator inside {@code Probably<T>} and mutates the value
   * of {@code Probably<T>} if Immutator is not null.
   *
   * @param <R> Type paramter scoped for apply method
   * @param p The Imutator inside the {@code Probably<T>} type object to be mutated
   *
   * @return Returns mutated {@code Probably<T>} value
   */
  @Override
  public <R> Probably<R> apply(Probably<? extends Immutator<? extends R, ? super T>> p) {
    if (this.value != null && p.value != null) {
      return (Probably<R>) new Probably<>(p.value.invoke(this.value));
    }
    return none();
  }

  /**
   * It is probably nothing, no value inside.
   *
   * @param <T> Type parameter scoped for none method
   *
   * @return The shared NOTHING.
   */
  public static <T> Probably<T> none() {
    @SuppressWarnings("unchecked")
    Probably<T> res = (Probably<T>) NONE;
    return res;
  }

  /**
   * It is probably just the given value.
   * Unless the value is null, then nothing is
   * given again.
   *
   * @param <T> Type parameter for just method.
   * @param value Probably this is the value
   *              unless it is null then we say
   *              that there is no 
   * @return The given value or nothing but
   *         never null.
   */
  public static <T> Probably<T> just(T value) {
    if (value == null) {
      return none();
    }
    return (Probably<T>) new Probably<>(value);
  }

  /**
   * Check for equality between something that
   * is probably a value but maybe nothing.
   *
   * @param obj The other value to be compared.
   * @return True if the two values are equal,
   *         false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Probably<?>) {
      Probably<?> some = (Probably<?>) obj;
      if (this.value == some.value) {
        return true;
      }
      if (this.value == null || some.value == null) {
        return false;
      }
      return this.value.equals(some.value);
    }
    return false;
  }

  /**
   * String representation of something that
   * is probably a value but maybe nothing.
   *
   * @return The string representation.
   */
  @Override
  public String toString() {
    if (this.value == null) {
      return "<>";
    } else {
      return "<" + this.value.toString() + ">";
    }
  }
}
