/**
 * CS2030S PE1 Question 1
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
class CandyBag extends Candy {
  private int numOfCandy;
  private int counter;

  public CandyBag(int numOfCandy, int weight) {
    super(weight);
    this.numOfCandy = numOfCandy;
    this.counter = numOfCandy;
  }

  @Override
  public void eat() {
    if (this.counter > 0) {
      this.counter--;
      if (this.counter == 0) {
        this.setEaten();
      }
    }
  }

  @Override
  public int getWeight() {
    return this.counter * super.getWeight();
  }

  @Override
  public String toString() {
    return String.format("CandyBag %d/%d (%d gram)", this.counter, this.numOfCandy, this.getWeight());
  }
}
