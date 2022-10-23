/**
 * This class implements conjunction of two boolean expressions.
 * This is created from And(Cond lVal, Cond rVal) to mean lVal {@code &&} rVal. 
 *
 * @author Javier Yong (Group 12B) 
 * @version CS2030S AY22/23 Semester 1
 */
class And implements Cond {
  private Cond lVal;
  private Cond rVal;
  
  public And(Cond lVal, Cond rVal) {
    this.lVal = lVal;
    this.rVal = rVal;
  }
  
  @Override
  public boolean eval() {
    return this.lVal.eval() && this.rVal.eval();
  }
  
  @Override
  public String toString() {
    return "(" + this.lVal + " & " + this.rVal + ")";
  }
  
  @Override
  public Cond neg() {
    return new Or(lVal.neg(), rVal.neg());
  }
}
