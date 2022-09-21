/**
 * The Array class for CS2030S. 
 *
 * @author Javier Yong (Group 12B) 
 * @version CS2030S AY21/22 Semester 2
 */
public class Array<T extends Comparable<T>> { 
  private T[] array;

  public Array(int size) {
    // The only way we can insert object into array is through
    // the set() method and the only way we can get an object
    // from array is through get() method.
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public int length() {
    return this.array.length;
  }
  
  public T min() {
    T min = this.array[0];

    for (T curr : this.array) {
      if (curr.compareTo(min) < 0) {
        min = curr;
      }
    }
    return min;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
