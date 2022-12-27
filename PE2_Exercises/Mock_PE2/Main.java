import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static String reverse(String s) {
    return Utilities.split(s)
                    .reduce("", 
                        (r, c) ->  c + r, 
                        (fst, snd) -> snd + fst);
  }
  public static Stream<String> palindrome(Stream<String> stream) {
    return stream.filter(s -> s.equals(Main.reverse(s)));
  }
}
