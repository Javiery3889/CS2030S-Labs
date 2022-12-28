package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S PE1 Question 1.
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */
public abstract class Either<L, R> {

  public static <L, R> Either<L, R> left(L value) {
    return new Left<>(value);
  }

  public static <L, R> Either<L, R> right(R value) {
    return new Right<>(value);
  }

  public abstract boolean isLeft();
  
  public abstract boolean isRight();

  public abstract L getLeft();

  public abstract R getRight();

  public abstract <T, U> Either<T, U> map(
      Transformer<? super L, ? extends T> t1, 
      Transformer<? super R, ? extends U> t2);
  
  public abstract <T, U> Either<T, U> flatMap(
      Transformer<? super L, ? extends Either<? extends T, ? extends U>> t1, 
      Transformer<? super R, ? extends Either<? extends T, ? extends U>> t2);

  public abstract <T> T fold(
      Transformer<? super L, ? extends T> t1, 
      Transformer<? super R, ? extends T> t2);

  public abstract Either<L, R> filterOrElse(
      BooleanCondition<? super R> b, 
      Transformer<? super R, ? extends L> t);

  private static final class Left<L, R> extends Either<L, R> {
    private final L value;

    private Left(L value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return true;
    }

    @Override
    public boolean isRight() {
      return false;
    }

    @Override
    public L getLeft() {
      return this.value;
    }

    @Override
    public R getRight() {
      throw new NoSuchElementException();
    }

    @Override
    public <T, U> Either<T, U> map(
        Transformer<? super L, ? extends T> t1, 
        Transformer<? super R, ? extends U> t2) {
      return Either.left(t1.transform(this.value));
    }

    @Override
    public <T, U> Either<T, U> flatMap(
        Transformer<? super L, ? extends Either<? extends T, ? extends U>> t1, 
        Transformer<? super R, ? extends Either<? extends T, ? extends U>> t2) {
      return Either.left(t1.transform(this.value).getLeft());
    }
  
    @Override
    public <T> T fold(
        Transformer<? super L, ? extends T> t1, 
        Transformer<? super R, ? extends T> t2) {
      return t1.transform(this.value);
    }
  
    @Override
    public Either<L, R> filterOrElse(
        BooleanCondition<? super R> b, 
        Transformer<? super R, ? extends L> t) {
      return Either.left(this.value);
    }
    
    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Left<?, ?>) {
        Left<?, ?> some = (Left<?, ?>) obj;
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
    public String toString() {
      return "Left[" + this.value + "]";
    }
  }

  private static final class Right<L, R> extends Either<L, R> {
    private final R value;

    private Right(R value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return false;
    }

    @Override
    public boolean isRight() {
      return true;
    }

    @Override
    public L getLeft() {
      throw new NoSuchElementException();
    }

    @Override
    public R getRight() {
      return this.value;
    }
    
    @Override
    public <T, U> Either<T, U> map(
        Transformer<? super L, ? extends T> t1, 
        Transformer<? super R, ? extends U> t2) {
      return Either.right(t2.transform(this.value));
    }
    
    @Override
    public <T, U> Either<T, U> flatMap(
        Transformer<? super L, ? extends Either<? extends T, ? extends U>> t1, 
        Transformer<? super R, ? extends Either<? extends T, ? extends U>> t2) {
      return Either.right(t2.transform(this.value).getRight());
    }

    @Override
    public <T> T fold(
        Transformer<? super L, ? extends T> t1, 
        Transformer<? super R, ? extends T> t2) {
      return t2.transform(this.value);
    }
    
    @Override
    public Either<L, R> filterOrElse(
        BooleanCondition<? super R> b, 
        Transformer<? super R, ? extends L> t) {
      if (b.test(this.value)) {
        return Either.right(this.value);
      }
      return Either.left(t.transform(this.value));
    }
    
    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Right<?, ?>) {
        Right<?, ?> some = (Right<?, ?>) obj;
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
    public String toString() {
      return "Right[" + this.value + "]";
    }
  }
}
