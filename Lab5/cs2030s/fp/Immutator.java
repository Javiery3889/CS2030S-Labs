package cs2030s.fp;

/**
 * The Immutator interface that can transform 
 * to type T2, an object of type T1.
 * <p>
 * Contains a single abstract method invoke.
 * </p>
 *
 * @version CS2030S Lab 4 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */

public interface Immutator<R, P> {
  R invoke(P p);
}
