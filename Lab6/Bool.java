import cs2030s.fp.Constant;
import cs2030s.fp.Memo;

/**
 * This class implements stores a boolean value which may be true or false. 
 *
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Bool implements Cond {
  private Memo<Boolean> val;
  
  public Bool(Constant<Boolean> val) {
    this.val = Memo.from(val);
  }
  
  @Override
  public boolean eval() {
    return this.val.get();
  }
  
  @Override
  public String toString() {
    return this.val.toString().substring(0, 1);
  }
  
  @Override
  public Cond neg() {
    return new Not(this);
  }
}
