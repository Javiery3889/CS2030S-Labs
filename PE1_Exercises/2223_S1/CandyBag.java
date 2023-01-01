class CandyBag extends Candy {
  private int candyCount;
  private int weight;
  private int remainder;

  public CandyBag(int candyCount, int weight) {
    this.candyCount = candyCount;
    this.weight = weight;
    this.remainder = candyCount;
  }

  @Override
  public int getWeight() {
    return this.remainder * this.weight;
  }

  @Override
  public void eat() {
    if (this.remainder > 0 && !this.isEaten()) {
      this.remainder--;
      if (this.remainder == 0) {
        super.eat();
      }
    }
  }
  
  @Override
  public String toString() {
    return String.format("CandyBag %d/%d (%d gram)", this.remainder, this.candyCount, this.getWeight());
  }
}
