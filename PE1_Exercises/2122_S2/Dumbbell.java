class Dumbbell extends Equipment {
  private final double weight;
  private int counter;

  public Dumbbell(double weight) {
    this.weight = weight;
    this.counter = 0;
  }

  public double getWeight() {
    return this.weight;
  }

  @Override
  public void repair() {
    this.counter++;
  }

  public int getRepairCount() {
    return this.counter;
  }

  @Override
  public String toString() {
    return String.format("Dumbbell: %.1f kg", this.weight);
  }
}
