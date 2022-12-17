abstract class Candy {
  private boolean eaten;

  public Candy() {
    this.eaten = false;
  }

  public abstract int getWeight();

  public void eat() {
    this.eaten = true;
  }

  public boolean isEaten() {
    return this.eaten;
  }
}
