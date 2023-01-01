class MysteryCandy extends Candy {
  private int weight;
  private String flavour;
  private boolean opened;

  public MysteryCandy(String flavour, int weight) {
    this.flavour = flavour;
    this.weight = weight;
    this.opened = false;
  }

  public void open() {
    if (!this.opened && !this.isEaten()) {
      this.opened = true;
    }
  }

  @Override
  public void eat() {
    if (this.opened && !this.isEaten()) {
      super.eat();
      this.weight = 0;
    }
  }

  @Override
  public int getWeight() {
    return this.weight;
  }

  @Override
  public String toString() {
    return String.format("%s Candy (%d gram)", this.flavour, this.weight);
  }
}
