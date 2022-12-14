/**
 * CS2030S PE1 Question 2
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
interface Queueable<T> {
  Queueable<T> enq(T t);
  Queueable<T> deq();
  T peek();
  int size();
}
