import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * CS2030S PE1 Question 2.
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

class Query {

  public static <T, S> Stream<Map.Entry<T, S>> getFilteredByKey(Map<T, S> table, Predicate<T> p) {
    return table.entrySet()
      .stream()
      .filter(pair -> p.test(pair.getKey())); 
  }

  public static Stream<Integer> getIdsFromName(Map<String, List<Integer>> table, String name) {
    return Query.getFilteredByKey(table, s -> s.equals(name))
      // Convert List<Integer> (a Collection) to a Stream of Integers
      .flatMap(pair -> pair.getValue().stream());
  }

  public static Stream<Double> getCostsFromIDs(Map<Integer, Double> table, Stream<Integer> ids) {
    return ids.map(id -> table.get(id))
      .filter(d -> d != null);
  }

  public static Stream<String> allCustomerCosts(
      Map<String, List<Integer>> custTable, 
      Map<Integer, Double> salesTable) {
    return custTable.keySet()
      .stream()
      .flatMap(name -> custTable.get(name)
          .stream()
          .filter(id -> salesTable.get(id) != null)
          .map(id -> name + ": " + salesTable.get(id))
          );
  }

  public static Stream<String> totaledCustomerCosts(
      Map<String, List<Integer>> custTable, 
      Map<Integer, Double> salesTable) {
    return custTable.keySet()
      .stream()
      .map(name -> name + ": " + custTable.get(name)
          .stream()
          .filter(id -> salesTable.get(id) != null)
          .map(id -> salesTable.get(id))
          .reduce(0.0, (x, y) -> x + y, (x, y) -> x + y)
          );
  }
}

