/**
 * CS2030S PE1 Question 1
 * AY22/23 Semester 1
 *
 * @author A0000000X
 */
class MysteryCandy extends Candy {
  private String flavour;
  private boolean opened;

  public MysteryCandy(String flavour,int weight) {
    super(weight);
    this.flavour = flavour;
    this.opened = false;
  }

  @Override
  public void eat() {
    if (this.opened == true) {
      this.setEaten();
      this.setWeight();
    }
  }

  public void open() {
    this.opened = true;
  }

  @Override
  public String toString() {
    return String.format("%s Candy (%d gram)",this.flavour,this.getWeight());
  }
}
