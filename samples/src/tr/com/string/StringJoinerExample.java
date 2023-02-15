package tr.com.string;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Introduced in java 8.
 * Uses string builder inside
 */
public class StringJoinerExample {

    public static void main(final String[] args) {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        stringJoiner.add("first");
        stringJoiner.add("second");
        stringJoiner.add("third");
        System.out.println(stringJoiner);

        // Another alternative way
        List<String> listOfStrings = Arrays.asList("First", "Second", "Third");
        String joinedString = listOfStrings.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(joinedString);
    }

}
