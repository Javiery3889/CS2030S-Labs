package cs2030s.fp;

/**
 * This class implements a chained Immutator for both ways.
 * .
 *
 * @version CS2030S Lab 5 AY22/23 Semester 1
 *
 * @author Javier Yong (Lab Group 12B)
 */
public abstract class Transformer<R, P> implements Immutator<R, P> {
  public <N> Transformer<R, N> after(Transformer<P, N> p) {
    Transformer<R, P> f = this;
    return new Transformer<R, N>() {
      @Override
      public R invoke(N n) {
        return f.invoke(p.invoke(n));
      }
    };
  }

  public <T> Transformer<T, P> before(Transformer<T, R> t) {
    Transformer<R, P> f = this;
    return new Transformer<T, P>() {
      @Override
      public T invoke(P p) {
        return t.invoke(f.invoke(p));
      }
    };
  }
}
