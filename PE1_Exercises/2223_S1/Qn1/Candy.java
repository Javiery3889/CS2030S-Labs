/**
 * CS2030S PE1 Question 1
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
abstract class Candy {
  private int weight;
  private boolean eaten;

  public Candy(int weight) {
    this.weight = weight;
    this.eaten = false;
  }

  public abstract void eat();

  public int getWeight() {
    return this.weight;
  }

  public boolean isEaten() {
    return this.eaten;
  }

  public void setEaten() {
    this.eaten = true;
  }

  public void setWeight() {
    this.weight = 0;
  }
}

