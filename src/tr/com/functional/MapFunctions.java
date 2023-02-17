package tr.com.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapFunctions {

    public static void main(final String[] args) {

        final Function<String, Integer> lengthFunction = s -> {
            System.out.println("Called");
            return s.length();
        };

        final Map<String, Integer> nameToLengthMap = new HashMap<>();
        // Length function is called
        Integer result = nameToLengthMap.computeIfAbsent("serhat", lengthFunction);
        System.out.println("Result : " + result); // 6
        System.out.println("length of serhat is: " + nameToLengthMap.get("serhat")); // 6

        // Length function is not called since value is already present
        result = nameToLengthMap.computeIfAbsent("serhat", lengthFunction);
        System.out.println("Called again when present. Result is : " + result);

        final BiFunction<String, Integer, Integer> addWithLengthFunction = (s, i) -> s.length() + i;
        result = nameToLengthMap.computeIfPresent("serhat", addWithLengthFunction);
        System.out.println("Result of computeIfPresent: " + result);
        System.out.println("New value of serhat: " + nameToLengthMap.get("serhat"));

        result = nameToLengthMap.computeIfPresent("un-existing", addWithLengthFunction);
        System.out.println("Result of un-existing value: " + result);

        try {
            nameToLengthMap.compute("un-existing", addWithLengthFunction);
        }catch (Exception nlpe) {
            System.out.println("Expected null pointer exception since value of un-existing is null");
        }
    }
}
