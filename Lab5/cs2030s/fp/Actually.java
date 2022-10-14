package cs2030s.fp;

/**
 * This class implements something that represents either a
 * successful computation or a failure.
 * 
 * <p>
 * A failure will be represented as a Exception
 *</p>
 *
 * @version CS2030S Lab 5 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {

  public static <T> Actually<T> ok(T res) {
    return (Success<T>) new Success<>(res);
  }

  public static <T> Actually<T> err(Exception exc) {
    // Failure will always use Actually<Object> and not type T,
    // hence it is safe to do a unchecked cast below.
    @SuppressWarnings("unchecked") 
    Actually<T> failWrap = (Actually<T>) new Failure(exc);
    return failWrap;
  }

  public abstract T unwrap() throws Exception;

  public abstract T except(Constant<? extends T> c);

  public abstract void finish(Action<? super T> a);

  public abstract T unless(T t);

  public abstract <R> Actually<R> next(Immutator<? extends Actually<R>, ? super T> i);

  private static class Success<T> extends Actually<T> {
    private final T value;

    private Success(T value) {
      this.value = value;
    }

    @Override
     public T unwrap() {
      return this.value;
    }

    @Override
    public T except(Constant<? extends T> c) {
      return this.value;
    }

    @Override
    public void finish(Action<? super T> a) {
      a.call(this.value);
    }

    @Override
    public T unless(T t) {
      return this.value;
    }

    @Override
    public <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> i) {
      try {
        return Actually.ok(i.invoke(this.value));
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    @Override
    public void act(Action<? super T> a) {
      a.call(this.value);
    }

    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<R>, ? super T> i) {
      try {
        return i.invoke(this.value);
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    @Override
    public String toString() {
      if (this.value != null) {
        return "<" + this.value.toString() + ">"; 
      }
      return "<>";
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Success<?>) {
        Success<?> some = (Success<?>) obj;
        if (this.value == some.value) {
          return true;
        }
        if (some.value == null || this.value == null) {
          return false;
        }
        return this.value.equals(some.value);
      }
      return false;
    }
  }

  private static class Failure extends Actually<Object> {
    private final Exception exc;

    private Failure(Exception exc) {
      this.exc = exc;
    }

    @Override
    public Exception unwrap() throws Exception {
      throw this.exc;
    }

    @Override
    public String toString() {
      return "[" + this.exc.getClass().getName() + "] " + this.exc.getMessage();
    }

    @Override
    public Object except(Constant<? extends Object> c) {
      return c.init();
    }

    @Override
    public void finish(Action<Object> a) {
      return;
    }

    @Override
    public Object unless(Object o) {
      return o;
    }

    @Override
    public <R> Immutatorable<R> transform(Immutator<? extends R, Object> i) {
      return Actually.err(this.exc);
    }
    
    @Override
    public void act(Action<Object> a) {
      return;
    }

    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<R>, Object> i) {
      return Actually.err(this.exc);
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Failure) {
        Failure some = (Failure) obj;
        if (this.exc.getMessage() == null || some.exc.getMessage() == null) {
          return false;
        } else if (this.exc.getMessage() == some.exc.getMessage()) {
          return true;
        }
      }
      return false;
    }
  }
}
