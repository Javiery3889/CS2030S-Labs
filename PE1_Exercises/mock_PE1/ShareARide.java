class ShareARide implements Service {
  private static final int BASEFARE = 50;
  private static final int SURCHARGE = 500;

  @Override
  public int computeFare(Request r) {
    int res = 0;
    int time = r.getTime();
    if (time >= 600 && time <= 900) {
      res += ShareARide.SURCHARGE;
    }
    res += r.getDistance() * ShareARide.BASEFARE / r.getPassengers();
    return res;
  }

  @Override
  public String toString() {
    return "ShareARide";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ShareARide) {
      return true;
    }
    return false;
  }
}
