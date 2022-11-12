package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An implementation of a memoized infinite list.
 *
 * @author Javier Yong (Lab 12B)
 * @version CS2030S AY 22/23 Sem 1
 */
public class InfiniteList<T> {
  /** The head of the InfiniteList, which is of type Actually wrapped in Memo. */
  private Memo<Actually<T>> head;
  /** The tail of the InfiniteList, which is of type InfiniteList wrapped in Memo. */
  private Memo<InfiniteList<T>> tail;
  /** The cached End object. */
  private static final InfiniteList<?> END = new End();

  /** 
   * Private constructor for InfiniteList Object.
   *
   * @param head The head of the InfiniteList object.
   * @param tail The tail of InfiniteList object.
   */
  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Populate InfiniteList using Constant object given, values are memoised.
   *
   * @param <T> The type of values inside the InfiniteList.
   * @param prod The constant object that produces T values.
   * @return The memoised InfiniteList to be returned.
   */
  public static <T> InfiniteList<T> generate(Constant<? extends T> prod) {
    return new InfiniteList<T>(
        Memo.from(() -> Actually.ok(prod.init())), 
        Memo.from(() -> InfiniteList.generate(prod)));
  }

  /**
   * Populate InfiniteList using seed and lambda expression inside Immutator.
   *
   * @param <T> The type of values inside the InfiniteList.
   * @param seed The initial value given to the first element of the InfiniteList.
   * @param func The immutator that is applied to each value in the InfiniteList.
   * @return The InfniteList to be returned.
   */
  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {
    return new InfiniteList<T>(
        Memo.from(Actually.ok(seed)),
        Memo.from(() -> InfiniteList.iterate(func.invoke(seed), 
            func)));
  }

  /**
   * Retrieve value of InfiniteList head, gets next head if current head is err.
   *
   * @return The value of the head.
   */
  public T head() {
    return this.head.get().except(() -> this.tail.get().head());
  }

  /**
   * Retrieve InfiniteList tail which is an InfiniteList, gets next tail if current head is err.
   *
   * @return The InfiniteList to be returned.
   */
  public InfiniteList<T> tail() {
    return this.head.get().transform(x -> this.tail.get()).except(() -> this.tail.get().tail());
  }

  /**
   * Lazily applies Immutator to each element in the InfiniteList.
   *
   * @param <R> The type of the resulting InfiniteList.
   * @param f The Immutator object to mutate elements.
   * @return The resulting InfiniteList with mutated elements.
   */
  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    return new InfiniteList<>(
        this.head.transform(head -> head.transform(f)),
        this.tail.transform(tail -> tail.map(f)));
  }

  /**
   * Filters out elements that fail predicate, marking filtered elements as err.
   *
   * @param pred The predicate Immutator to be used as the filter.
   * @return The resulting InfiniteList with filtered values.
   */
  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    return new InfiniteList<>(
        this.head.transform(head -> head.check(pred)),
        this.tail.transform(tail -> tail.filter(pred)));
  }

  /**
   * Terminates InfiniteList into a finite list, filtered elements are not counted.
   *
   * @param n Number of elements to be truncated.
   * @return The truncated InfiniteList.
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.end();
    }
    return new InfiniteList<>(
        this.head,
        Memo.from(() -> this.head.get().transform(x -> this.tail.get().limit(n - 1))
          .except(() -> this.tail.get().limit(n))));
  }

  /**
   * Truncates list when predicate condition returns false.
   *
   * @param pred The predicate Immutator to evaluate each element.
   * @return The resulting InfiniteList.
   */
  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    Memo<Actually<T>> checkHead = Memo.from(() -> Actually.ok(this.head()).check(pred));
    return new InfiniteList<>(
        checkHead,
        Memo.from(() -> checkHead.get().transform(x -> this.tail().takeWhile(pred))
          .unless(InfiniteList.end())));
  }

  /**
   * Terminal method that converts elements in InfiniteList to List.
   *
   * @return The resulting List.
   */
  public List<T> toList() {
    List<T> res = new ArrayList<>();
    InfiniteList<T> list = this;
    while (!list.isEnd()) {
      list.head.get().finish(elem -> res.add(elem));
      list = list.tail.get();
    }
    return res;
  }

  /**
   * Terminal operation that reduces elements in InfiniteList based on Combiner and initial value.
   *
   * @param <U> Return type of value.
   * @param id The initial value to be used.
   * @param acc The Combiner object to be reduce the elements in the list.
   * @return The reduced value.
   */ 
  public <U> U reduce(U id, Combiner<? extends U, ? super U, ? super T> acc) {
    //perform reduce with value of head, else use id if value is err instead
    return this.head.get().transform(h -> this.tail.get()
        .reduce(acc.combine(id, h), acc)).except(() -> this.tail.get().reduce(id, acc));
  }

  /**
   * Terminal operation that returns number of elements in a InfiniteList object.
   *
   * @return The number of elements.
   */
  public long count() {
    return reduce(0L, (x, y) -> x + 1L);
  }

  /**
   * String representation of an InfiniteList.
   *
   * @return String to be returned.
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  /**
   * Checks if InfiniteList is an instance of End.
   *
   * @return Returns false as it is not an End instance.
   */
  public boolean isEnd() {
    return false;
  }

  /**
   * Static factory method that produces an End Object.
   *
   * @param <T> The type of each element in the list.
   * @return The End instance to return.
   */
  public static <T> InfiniteList<T> end() {
    // End will always use InfiniteList<Object> and will always be empty,
    // hence it is safe to do cast below.
    @SuppressWarnings("unchecked")
    InfiniteList<T> endWrap = (InfiniteList<T>) InfiniteList.END;
    return endWrap;
  }

  /**
   * Static nested class that represent a list that contains nothing.
   */
  private static class End extends InfiniteList<Object> {
    /**
     * Private constructor for End Object.
     */
    private End() {
      super(null, null);
    }

    /**
     * String Representation of End Object.
     *
     * @return The string to be returned.
     */
    @Override
    public String toString() {
      return "-";
    }

    /**
     * Returns True if it is a End Object.
     *
     * @return True because it is an End Object.
     */
    @Override
    public boolean isEnd() {
      return true;
    }

    /**
     * Throws NoSuchElementException because head is null in End Object.
     *
     * @throws NoSuchElementException Head is null.
     */
    @Override
    public Object head() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    /**
     * Throws NoSuchElementException because tail is null in End Object.
     *
     * @throws NoSuchElementException Tail is null.
     */
    @Override
    public InfiniteList<Object> tail() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    /**
     * Returns a End instance when called.
     *
     * @return End Instance 
     */
    @Override
    public <R> InfiniteList<R> map(Immutator<? extends R, ? super Object> f) {
      return InfiniteList.end();
    }

    /**
     * Returns a End instance when called.
     *
     * @return End Instance 
     */
    @Override
    public InfiniteList<Object> filter(Immutator<Boolean, ? super Object> pred) {
      return InfiniteList.end();
    }

    /**
     * Returns a End instance when called.
     *
     * @return End Instance 
     */
    @Override 
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.end();
    }

    /**
     * Returns a End instance when called.
     *
     * @return End Instance 
     */
    @Override
    public InfiniteList<Object> takeWhile(Immutator<Boolean, ? super Object> pred) {
      return InfiniteList.end();
    }

    /**
     * Terminal operation that reduces elements in End object which will just be initial value. 
     *
     * @param <U> Return type of value.
     * @param id The initial value to be used.
     * @param acc The Combiner object to be reduce the elements in the list.
     * @return The reduced value.
     */ 
    @Override
    public <U> U reduce(U id, Combiner<? extends U, ? super U, ? super Object> acc) {
      return id;
    }

    /**
     * Returns number of elements in an End Object.
     *
     * @return Zero because list is empty.
     */
    @Override
    public long count() {
      return 0L;
    }

    /**
     * Returns empty list for End Object.
     *
     * @return Empty List to be returned.
     */
    @Override
    public List<Object> toList() {
      return List.of();
    }
  }
}
