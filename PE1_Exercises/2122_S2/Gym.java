class Gym {
  private int capacity;
  private int count;

  public Gym(int capacity) {
    this.capacity = capacity;
    this.count = 0;
  }

  public void enter(Person p) {
    if (this.count < this.capacity) {
      System.out.println(p.toString() + " can enter");
      this.count++;
    } else {
      System.out.println(p.toString() + " cannot enter");
    }
  }

  @Override
  public String toString() {
    return String.format("Gym Capacity: %d/%d", this.count, this.capacity);
  }
}
