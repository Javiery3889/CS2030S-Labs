/**
 * The Action interface that can be called
 * on an object of type T to act.
 * <p>
 * Contains a single abstract method call.
 *</p>
 * 
 * @version CS2030S Lab 4 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
interface Action<T> {
  void call(T t);
}
