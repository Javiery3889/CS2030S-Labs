package cs2030s.fp;
/**
 * CS2030S PE2 Question 1
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
public abstract class Try<T> {

  public static <T> Try<T> of(Producer<? extends T> p) {
    try {
      return Try.<T>success(p.produce());
    } catch (Throwable t) {
      return Try.<T>failure(t);
    }
  }

  public static <T> Try<T> success(T value) {
    return new Success<>(value);
  }

  public static <T> Try<T> failure(Throwable throwable) {
    // safe to cast as Failure will always be of type Try<Object>
    @SuppressWarnings("unchecked")
    Try<T> failure = (Try<T>) new Failure(throwable);
    return failure;
  }

  public abstract T get() throws Throwable;

  public abstract <U> Try<U> map(Transformer<? super T, ? extends U> t);

  public abstract <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> t);

  public abstract Try<T> onFailure(Consumer<? super Throwable> c);

  public abstract Try<T> recover(Transformer<? super Throwable, ? extends T> t);

  public static final class Success<T> extends Try<T> {
    private final T value;

    private Success(T value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Success<?>) {
        Success<?> some = (Success<?>) o;
        if (some.value == this.value) {
          return true;
        }
        if (some.value == null || this.value == null) {
          return false;
        }
        return this.value.equals(some.value);
      }
      return false;
    }

    @Override
    public T get() {
      return this.value;
    }

    @Override
    public <U> Try<U> map(Transformer<? super T, ? extends U> t) {
      return Try.<U>of(() -> t.transform(this.value));
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> t) {
      return Try.<U>of(() -> t.transform(this.value).get());
    }

    @Override
    public Try<T> onFailure(Consumer<? super Throwable> c) {
      return Try.success(this.value);
    }

    @Override
    public Try<T> recover(Transformer<? super Throwable, ? extends T> t) {
      return Try.success(this.value);
    }
  }

  public static final class Failure extends Try<Object> {
    private final Throwable throwable;

    private Failure(Throwable throwable) {
      this.throwable = throwable;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Failure) {
        Failure some = (Failure) o;
        if (some.throwable.toString() == this.throwable.toString()) {
          return true;
        }
      }
      return false;
    }

    @Override
    public Object get() throws Throwable {
      throw this.throwable;
    }

    @Override
    public <U> Try<U> map(Transformer<? super Object, ? extends U> t) {
      return Try.failure(this.throwable);
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super Object, ? extends Try<? extends U>> t) {
      return Try.failure(this.throwable);
    }

    @Override
    public Try<Object> onFailure(Consumer<? super Throwable> c) {
      try {
        c.consume(this.throwable);
        return Try.failure(this.throwable);
      } catch (Throwable t) {
        return Try.failure(t);
      }
    }

    @Override
    public Try<Object> recover(Transformer<? super Throwable, ? extends Object> t) {
      return Try.of(() -> t.transform(this.throwable));
    }
  }
}
