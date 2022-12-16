abstract class Equipment {
  private boolean inUse;
  
  public Equipment() {
    this.inUse = false;
  }

  public void setInUse(boolean b) {
    this.inUse = b;
  }

  public boolean isInUse() {
    return this.inUse;
  }

  public abstract void repair();
}
