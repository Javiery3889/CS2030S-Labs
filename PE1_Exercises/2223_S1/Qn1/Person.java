abstract class Person {
  private String name;

  public Person(String name) {
    this.name = name;
  }

  public abstract String getStatus();

  @Override
  public String toString() {
    return this.name;
  }
}
