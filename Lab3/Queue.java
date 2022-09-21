/**
 * The Queue class implements a simple FIFO data structure
 * with limited capacity that can store any Object instances.
 * Not to be confused with java.util.Queue.
 *
 * @author Javier Yong (Group 12B) 
 * @version CS2030S AY22/23 Semester 1
 */
class Queue<T> {
  /** An array to store the items in the queue. */
  private T[] items;

  /** Index of the first element in the queue. */
  private int first;

  /** Index of the last element in the queue. */
  private int last;

  /** Maximum size of the queue. */
  private int maxSize;

  /** Number of elements in the queue. */
  private int len;

  /**
   * Constructor for a queue.
   *
   * @param size The maximum num of elements we can put in the queue.
   */
  public Queue(int size) {
    this.maxSize = size;
    
    // The only way we can insert an object into array is through the
    // method enq(T t) and only way we can retreive an object from array
    // is through T deq(). Hence, it is safe to cast Object[] to T[].
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[size];
    this.items = temp;
    this.first = -1;
    this.last = -1;
    this.len = 0;
  }

  /**
   * Add the object e into the queue.
   *
   * @param t The item to put in the queue.
   * @return false if the queue is full; true if e is added successfully.
   */
  public boolean enq(T t) {
    if (this.isFull()) {
      return false;
    }
    if (this.isEmpty()) {
      this.first = 0;
      this.last = 0;
    } else {
      this.last = (this.last + 1) % this.maxSize;
    }
    this.items[last] = t;
    this.len += 1;
    return true;
  }

  /**
   * Remove the object from the queue.
   *
   * @return null if the queue is empty; the object removed from the queue otherwise.
   */
  public T deq() {
    if (this.isEmpty()) {
      return null;
    }
    T t = this.items[this.first];
    this.first = (this.first + 1) % this.maxSize;
    this.len -= 1;
    return t;
  }

  /**
   * Checks if the queue is full.
   *
   * @return true if the queue is full; false otherwise.
   */
  boolean isFull() {
    return (this.len == this.maxSize);
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty; false otherwise.
   */
  boolean isEmpty() {
    return (this.len == 0);
  }

  /**
   * Return the number of elements in the queue.
   *
   * @return The number of elements in the queue.
   */
  public int length() {
    return this.len;
  }

  /**
   * Returns the string representation of the queue.
   * 
   * @return A string consisting of the string representation of every object in the queue.
   */
  @Override
  public String toString() {
    String str = "[ ";
    int i = this.first;
    int count = 0;
    while (count < this.len) {
      str += this.items[i] + " ";
      i = (i + 1) % this.maxSize;
      count++;
    } 
    return str + "]";
  }
}