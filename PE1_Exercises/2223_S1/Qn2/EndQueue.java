/**
 * CS2030S PE1 Question 2
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
class EndQueue<T> extends Pair<T, Queueable<T>> implements Queueable<T> {
  public EndQueue() {
    super(null, null);
  }
  
  @Override
  public Queueable<T> enq(T val) {
    return new Sequeue<T>(val, this);
  }

  @Override
  public Queueable<T> deq() {
    // Cannot dequeue empty queue
    return null;
  }

  @Override
  public T peek() {
    // Cannot peek empty queue
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public String toString() {
    return "{END}";
  }
}
