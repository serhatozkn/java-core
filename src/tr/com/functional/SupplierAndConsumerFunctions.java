package tr.com.functional;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SupplierAndConsumerFunctions {

    // Can be used for long-taking actions lazy invocation
    // Takes nothing as parameter produces value

    public static void main(final String[] args) {

        // Can't use int pre1, pre2 because variables have to be final or effectively final
        int[] fibs = {0, 1};
        final Supplier<Integer> fiboSupplier = () ->  {
            int result = fibs[1];
            int fib3 = fibs[0] + fibs[1];
            fibs[0] = fibs[1];
            fibs[1] = fib3;
            return result;
        };

        final Consumer<Integer> consumer = System.out::println;

        Stream.generate(fiboSupplier).limit(20).forEach(consumer);
    }
}
