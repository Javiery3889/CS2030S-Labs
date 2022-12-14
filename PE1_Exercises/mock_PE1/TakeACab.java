class TakeACab implements Service {
  private static final int FARE = 33;
  private static final int BOOKINGFEE = 200;

  @Override
  public int computeFare(Request r) {
    int res = TakeACab.BOOKINGFEE;
    res += r.getDistance() * TakeACab.FARE;
    return res;
  }

  @Override
  public String toString() {
    return "TakeACab";
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof TakeACab) {
      return true;
    }
    return false;
  }
}
