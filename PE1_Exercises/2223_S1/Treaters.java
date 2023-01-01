/**
 * CS2030S PE1 Question 1
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
class Treaters extends Person {
  private Candy c;
  private final int numOfCandies;
  private int count;

  public Treaters(String name, Candy c, int numOfCandies) {
    super(name);
    this.c = c;
    this.numOfCandies = numOfCandies;
    this.count = 0;
  }

  public void give(Trickers tricker) throws NoCandyException {
    if (this.count == this.numOfCandies) {
      throw new NoCandyException();
    } else {
      if (tricker.receive(this.c) == true) {
        this.count++;
      }
    }
  }

  @Override
  public String getStatus() {
    return String.format("%s gave %d away out of %d %s", 
        this, this.count, this.numOfCandies, this.c);
  }

  @Override
  public String toString() {
    return String.format("Treater %s", super.toString());
  }
}
