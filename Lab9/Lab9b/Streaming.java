import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

/**
 * Returns a list that is the result of encoding the stream.
 *
 */
public class Streaming {
  public static <T> List<Pair<Integer, T>> encode(Stream<T> stream) {
    return stream.map(val -> new Pair<Integer, T>(0, val))
      .collect(ArrayList<Pair<Integer, T>>::new,
          (lst, p) -> {
            // check if AL if empty in case of exception
            if (lst.isEmpty()) {
              lst.add(p);
            } else if (!lst.get(lst.size() - 1).getSnd().equals(p.getSnd())) {
              lst.add(p);
            }
            lst.get(lst.size() - 1).setFst(lst.get(lst.size() - 1).getFst() + 1);
          },
          // combiner merges two lists
          (lst1, lst2) -> {
            if (lst1.get(lst1.size() - 1).getSnd().equals(lst2.get(0).getSnd())) {
              lst1.get(lst1.size() - 1).setFst(lst1.get(lst1.size() - 1).getFst() + lst2.get(0).getFst());
              lst2.remove(0);
            }
            lst1.addAll(lst2);
          });
  }
}
