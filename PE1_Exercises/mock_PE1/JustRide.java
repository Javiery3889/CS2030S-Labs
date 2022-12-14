class JustRide implements Service {
  private static final int FARE = 22;
  private static final int SURCHARGE = 500;

  @Override
  public int computeFare(Request r) {
    int res = 0;
    int time = r.getTime();
    if (time >= 600 && time <= 900) {
      res += JustRide.SURCHARGE;
    }
    res += r.getDistance() * JustRide.FARE;
    return res;
  }

  @Override
  public String toString() {
    return "JustRide";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof JustRide) {
      return true;
    }
    return false;
  }
}
