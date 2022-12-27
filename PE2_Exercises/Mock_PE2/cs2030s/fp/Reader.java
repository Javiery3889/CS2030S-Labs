package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Reader<T> {

  private static final Reader<?> EOF = new Empty();

  @SafeVarargs
  public static <T> Reader<T> of(T... items) {
    List<T> res = new ArrayList<>();
    for (T item : items) {
      res.add(item);
    }
    return new NonEmpty<>(res);
  }

  public static <T> Reader<T> of() {
    // safe to cast because EOF is always of type Reader<Object>
    @SuppressWarnings("unchecked")
    Reader<T> empty = (Reader<T>) Reader.EOF; 
    return empty;
  }

  public abstract T read();

  public abstract boolean hasNext();

  public abstract Reader<T> consume();

  public abstract <R> Reader<R> map(Immutator<? extends R, ? super T> i);

  public abstract Reader<T> flatMap(Immutator<? extends Reader<? extends T>, ? super T> i);

  public static final class NonEmpty<T> extends Reader<T> {
    private final List<T> values;

    private NonEmpty(List<T> values) {
      this.values = values;
    }

    @Override
    public T read() {
      return this.values.get(0);
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public Reader<T> consume() {
      if (this.values.size() == 1) {
        return Reader.of();
      }
      List<T> res = new ArrayList<>(this.values);
      res.remove(0);
      return new NonEmpty<>(res);
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof NonEmpty<?>) {
        return true;
      }
      return false;
    }

    @Override
    public <R> Reader<R> map(Immutator<? extends R, ? super T> i) {
      List<R> res = new ArrayList<>();
      for (T value: this.values) {
        res.add(i.invoke(value));
      }
      return new NonEmpty<R>(res);
    }

    @Override
    public Reader<T> flatMap(Immutator<? extends Reader<? extends T>, ? super T> i) {
      NonEmpty<? extends T> temp = (NonEmpty<? extends T>) i.invoke(this.values.get(0));
      List<T> res = new ArrayList<>(temp.values);
      for (int j = 1; j < this.values.size(); j++) {
        res.add(this.values.get(j));
      }
      return new NonEmpty<>(res);
    }

    @Override
    public String toString() {
      return "Reader";
    }
  }

  public static final class Empty extends Reader<Object> {

    @Override
    public Object read() {
      throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Reader<Object> consume() {
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Empty) {
        return true;
      }
      return false;
    }

    @Override
    public <R> Reader<R> map(Immutator<? extends R, ? super Object> i) {
      return Reader.of();
    }

    @Override
    public Reader<Object> flatMap(Immutator<? extends Reader<? extends Object>, ? super Object> i) {
      return Reader.of();
    }

    @Override
    public String toString() {
      return "EOF";
    }
  }
}
