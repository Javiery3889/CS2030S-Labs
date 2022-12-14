class Request {
  private int distance;
  private int passengers;
  private int time;

  public Request(int distance, int passengers, int time) {
    this.distance = distance;
    this.passengers = passengers;
    this.time = time;
  }

  public int getDistance() {
    return this.distance;
  }

  public int getPassengers() {
    return this.passengers;
  }

  public int getTime() {
    return this.time;
  }
}
