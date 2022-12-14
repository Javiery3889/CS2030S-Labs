class Cab extends Car {
  public Cab(String licensePlate, int timeAvail) {
    super(licensePlate, timeAvail);
    Car.setServiceAvail(new Service[]{new JustRide(), new TakeACab()});
  }

  @Override
  public String toString() {
    return String.format("Cab %s", super.toString()); 
  }
}
