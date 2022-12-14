class PrivateCar extends Car {

  public PrivateCar(String licensePlate, int timeAvail) {
    super(licensePlate, timeAvail);
    Car.setServiceAvail(new Service[]{new JustRide(), new ShareARide()});
  }

  @Override
  public String toString() {
    return String.format("PrivateCar %s", super.toString());
  }
}
