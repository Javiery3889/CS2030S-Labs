/**
 * This class implements negation of a boolean expression.
 * This is created from Not(Cond val) to mean !val. 
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Not implements Cond {
  private Cond val;
  
  public Not(Cond val) {
    this.val = val;
  }
  
  @Override
  public boolean eval() {
    return !this.val.eval();
  }
 
  @Override
  public String toString() {
    return "!(" + this.val + ")";
  }
  
  @Override
  public Cond neg() {
    return this.val;
  }
}
