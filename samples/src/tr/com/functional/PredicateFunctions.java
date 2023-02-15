package tr.com.functional;

import java.util.List;
import java.util.function.Predicate;

public class PredicateFunctions {

    public static void main(final String[] args) {

        final Predicate<String> startsWithH = s -> s.startsWith("H");
        final List<String> carBrands = List.of("Mercedes", "Renault", "Honda", "Suzuki");
        carBrands.stream().filter(startsWithH).forEach(System.out::println);
    }
}
