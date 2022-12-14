class Booking implements Comparable<Booking> {
  private Car car;
  private Service service;
  private int fare;

  public Booking(Car car, Service service, Request request) {
    if (!car.isCompatible(service)) {
      throw new IllegalArgumentException(
          String.format("%s does not provide the %s service.", car, service));
    }
    this.car = car;
    this.service = service;
    this.fare = service.computeFare(request);
  }

  @Override
  public int compareTo(Booking b) {
    if (this.fare == b.fare) {
      return this.car.compareTo(b.car);
    } else if (this.fare < b.fare) {
      return -1;
    }
    return 1;
  }
}
