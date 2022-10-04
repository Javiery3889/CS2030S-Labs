/**
 * The Actionable interface that can
 * act when given an action.
 * <p>
 * Contains a single abstract method act.
 * </p>
 *
 * @version CS2030S Lab 4 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
interface Actionable<T> {
  void act(Action<? super T> a);
}
