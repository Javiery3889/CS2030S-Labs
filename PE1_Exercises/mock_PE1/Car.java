abstract class Car implements Comparable<Car> {
  private String licensePlate;
  private int timeAvail;
  private static Service[] serviceAvail;

  public Car(String licensePlate, int timeAvail) {
    this.licensePlate = licensePlate;
    this.timeAvail = timeAvail;
  }

  public String getLicensePlate() {
    return this.licensePlate;
  }

  public int getTimeAvail() {
    return this.timeAvail;
  }

  public static void setServiceAvail(Service[] s) {
    Car.serviceAvail = s;
  }

  public boolean isCompatible(Service s) {
    for (Service res : Car.serviceAvail) {
      if (res.equals(s)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    String minsStr = "mins";
    if (this.timeAvail <= 1) {
      minsStr = "min";
    }
    return String.format("%s (%d %s away)", this.licensePlate, this.timeAvail, minsStr);
  }

  @Override
  public int compareTo(Car c) {
    if (this.timeAvail < c.timeAvail) {
      return -1;
    }
    return 1;
  }
}
