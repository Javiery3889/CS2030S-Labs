class ArrayStack<T> implements Stack<T> {
  private int maxDepth;
  private int index;
  private T[] array;

  public ArrayStack(int maxDepth) {
    // The only way we can add/remove objects are using push() and pop()
    // Hence, it is safe to cast.
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[maxDepth];
    this.array = temp;
    this.maxDepth = maxDepth;
    this.index = 0;
  }

  public static <T> ArrayStack<T> of(T[] arr, int maxDepth) {
    ArrayStack<T> res = new ArrayStack<>(maxDepth);
    for (T item : arr) {
      res.push(item);
    }
    return res;
  }

  // st produces values in this case
  public void pushAll(ArrayStack<? extends T> st) {
    while (st.index > 0) {
      this.push(st.pop());
    }
  }

  // st consumes values in this case
  public void popAll(ArrayStack<? super T> st) {
    while (this.index > 0) {
      st.push(this.pop());
    }
  }

  @Override
  public T pop() {
    if (this.index == 0) {
      return null;
    }
    this.index--;
    T res = this.array[this.index];
    return res;
  }

  @Override
  public void push(T val) {
    if (this.index < this.maxDepth) {
      this.array[this.index] = val;
      this.index++;
    }
  }

  @Override
  public int getStackSize() {
    return this.index;
  }

  @Override
  public String toString() {
    String res = "";
    for (int i = 0; i < this.index; i++) {
      res += this.array[i] + " ";
    }
    return String.format("Stack: %s", res);
  }
}
