package tr.com.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class Operators {

    public static void main(final String[] args) {
        final List<Integer> values = Arrays.asList(13, 25, 38, 49, 52);
        final BinaryOperator<Integer> add = (x, y) -> (x + y);
        final int sum = values.stream()
                .reduce(0, add);
        System.out.println("sum: " + sum);
    }
}
