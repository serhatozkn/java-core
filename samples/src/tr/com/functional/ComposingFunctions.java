package tr.com.functional;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class ComposingFunctions {

    public static void main(final String[] args) {

        final Function<Integer, String> toStringFunction = i -> i + "";
        final Function<String, String> quoteFunction = s -> "\"" + s + "\"";

        final Function<Integer, String> combinedFunction = toStringFunction.andThen(quoteFunction);
        System.out.println( "5 -> " + combinedFunction.apply(5));

        final Function<Integer, Integer> squareFunction = x -> x * x;
        final Function<String, Integer> toIntFunction = Integer::parseInt;

        // Function passed as parameter called first
        final Function<String, Integer> composed = squareFunction.compose(toIntFunction);
        System.out.println("\"12\" -> " + composed.apply("12"));



    }
}
