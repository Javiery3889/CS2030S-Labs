package cs2030s.fp;

/**
 * The Constant interface that can be called
 * on an object of type T to init.
 * <p>
 * Contains a single abstract method init.
 *</p>
 * 
 * @version CS2030S Lab 5 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
public interface Constant<T> {
  T init();
}
