package tr.com.collections.set;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeSetExample {

    public static void main(final String[] args) {
        final TreeSet<Double> orderedSet = new TreeSet<>();
        orderedSet.add(5d);
        orderedSet.add(2d);
        orderedSet.add(12d);
        orderedSet.add(32d);
        orderedSet.add(1d);
        orderedSet.add(-1d);
        final String ascendingOrder = orderedSet.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(ascendingOrder);

        final String descendingOrder = orderedSet.descendingSet().stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(descendingOrder);

        try {
            final TreeSet<Altitude> altitudes = new TreeSet<>();
            altitudes.add(new Altitude(5));
            altitudes.add(new Altitude(3));
        }catch (ClassCastException classCastException) {
            System.err.println("Type parameter class of TreeSet must either implement the Comparable interface; or " +
                    "must supply Comparator parameter to constructor");
        }

        final TreeSet<Altitude> altitudes = new TreeSet<>(Comparator.comparing(Altitude::altitude));
        altitudes.add(new Altitude(12_000));
        altitudes.add(new Altitude(15_000));
        altitudes.add(new Altitude(40_000));
        altitudes.add(new Altitude(17_500));
        altitudes.add(new Altitude(11_000));
        final String altitudesText = altitudes.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(altitudesText);

        final TreeSet<Speed> speeds = new TreeSet<>();
        speeds.add(new Speed(50));
        speeds.add(new Speed(52));
        speeds.add(new Speed(49));
        speeds.add(new Speed(44));
        final String speedsText = speeds.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(speedsText);
    }

    private record Altitude (int altitude) { }

    private record Speed (int speed) implements Comparable<Speed> {

        @Override
        public int compareTo(Speed otherObj) {
            return Integer.valueOf(speed).compareTo(otherObj.speed());
        }
    }
}
