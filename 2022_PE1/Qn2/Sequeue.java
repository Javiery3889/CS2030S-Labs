/**
 * CS2030S PE1 Question 2
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
class Sequeue<T> extends Pair<T, Queueable<T>> implements Queueable<T> {
  
  public Sequeue(T head, Queueable<T> queue) {
    super(head, queue);
  }

  @Override
  public Queueable<T> enq(T val) {
    return new Sequeue<T>(this.getFst(), this.getSnd().enq(val));
  }
 
  @Override
  public Queueable<T> deq() {
    return this.getSnd(); 
  }

  @Override
  public T peek() {
    return this.getFst();
  }

  @Override
  public int size() {
    if (this.getSnd() != null) {
      return 1 + this.getSnd().size();
    }
    return 1;
  }
}
