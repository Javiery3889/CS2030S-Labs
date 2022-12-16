class Trainer extends Person {
  private Customer customer;
  private Equipment equipment;
  private boolean isTraining;

  public Trainer(String name) {
    super(name);
    this.isTraining = false;
  }

  public void startTraining(Customer c, Equipment e) throws CannotTrainException {
    if (this.isTraining) {
      throw new CannotTrainException();
    }
    e.setInUse(true);
    this.isTraining = true;
    this.customer = c;
    this.equipment = e;
  }

  public void stopTraining() {
    this.isTraining = false;
    this.equipment.setInUse(false);
  }

  @Override
  public String toString() {
    return String.format("Trainer: %s", super.toString());
  }

  public String getStatus() {
    if (!this.isTraining) {
      return String.format("%s not training", this.toString());
    }
    return String.format("%s training %s", this.toString(), this.customer);
  }
}
