class Treadmill extends Equipment {
  private double speed;

  public Treadmill() {
    this.speed = 0;
  }

  public void setSpeed(double s) {
    this.speed = s;
  }

  public double getSpeed() {
    return this.speed;
  }

  @Override
  public void repair() {
    this.speed = 0;
  }

  @Override
  public String toString() {
    return String.format("Treadmill: %.1f km/h", this.speed);
  }
}
