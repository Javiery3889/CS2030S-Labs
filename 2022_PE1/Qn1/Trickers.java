/**
 * CS2030S PE1 Question 1
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
class Trickers extends Person {
  private String trick;
  private final int maxWeight;
  private int currWeight;
  private Candy latestCandy;
  private int trickCount;

  public Trickers(String name, String trick, int maxWeight) {
    super(name);
    this.trick = trick;
    this.maxWeight = maxWeight;
    this.currWeight = 0;
    this.trickCount = 0;
    this.latestCandy = null;
  }

  public boolean receive(Candy c) {
    int candyWeight = c.getWeight();
    if (this.currWeight + candyWeight >= maxWeight) {
      return false;
    }
    this.currWeight+=candyWeight;
    this.latestCandy = c;
    return true;
  }

  public void give(Trickers tricker) throws NoCandyException {
    if (this.latestCandy == null) {
      throw new NoCandyException();
    }
    int candyWeight = this.latestCandy.getWeight();
    this.currWeight-=candyWeight;
    tricker.receive(this.latestCandy);
    this.latestCandy = null;
  }

  public String playTricks(Person p) {
    if (p instanceof Treaters) {
      Treaters treater = (Treaters) p;
      try {
        treater.give(this);
        return String.format("%s receives from %s", this, treater);
      } catch (NoCandyException e) {
        this.trickCount+=1;
        return String.format("%s perform the trick %s to %s", this, this.trick, treater);
      }
    }
    if (p instanceof Trickers) {
      Trickers tricker = (Trickers) p;
      try {
        tricker.give(this);
        return String.format("%s receives from %s", this, tricker);
      } catch (NoCandyException e) {
        this.trickCount+=1;
        return String.format("%s perform the trick %s to %s", this, this.trick, tricker);
      }
    }
    return "";
  }

  @Override
  public String getStatus() {
    int remainder = this.maxWeight - this.currWeight;
    return String.format("%s performed %d tricks and has bag size %d remaining", this, this.trickCount, remainder);
  }

  @Override
  public String toString() {
    return String.format("Tricker %s", super.toString());
  }
}

