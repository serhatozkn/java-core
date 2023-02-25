package tr.com.collections.map;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author serhat.ozkan
 */

/**
 * Oracle JavaDoc Note:
 * Note: great care must be exercised if mutable objects are used as map keys.
 * The behavior of a map is not specified if the value of an object is changed in a manner
 * that affects equals comparisons while the object is a key in the map.
 * A special case of this prohibition is that it is not permissible for a map to contain itself as a key.
 * While it is permissible for a map to contain itself as a value,
 * extreme caution is advised: the equals and hashCode methods are no longer well defined on such a map.
 */
public class MapInterfaceExample {

    private final Map<String, Color> cityColorMap = new HashMap<>();

    private final Color defaultColor = Color.RED;

    public static void main(final String[] args) {
        MapInterfaceExample mapInterfaceExample = new MapInterfaceExample();
        mapInterfaceExample.demo();
    }

    private void demo() {
        final String ankara = "Ankara";
        final String istanbul = "Istanbul";
        final String izmir = "Izmir";

        /********************/
        /******* put ********/
        /********************/
        // Put returns the previous value - null if absent
        System.out.println(cityColorMap.put("Konya", Color.RED));
        System.out.println(cityColorMap.put("Konya", Color.GREEN));

        /*************************/
        /***** getOrDefault *****/
        /***********************/
        Color ankaraColor = cityColorMap.getOrDefault(ankara, defaultColor);
        System.out.println(ankara + " -> " + ankaraColor);

        cityColorMap.put(ankara, Color.GREEN);
        ankaraColor = cityColorMap.getOrDefault(ankara, defaultColor);
        System.out.println(ankara + " -> " + ankaraColor);

        /************************/
        /***** putIfAbsent *****/
        /************************/

        Color istanbulColor = cityColorMap.putIfAbsent(istanbul, Color.BLUE);
        // If the key is absent; inserts the k/v pair to the map. Returns null
        System.out.println("Returned val : " + istanbulColor);
        System.out.println(istanbul + " -> " + cityColorMap.get(istanbul));
        // If the key is present; returns the value at map. Doesn't change the value inside map
        istanbulColor = cityColorMap.putIfAbsent(istanbul, Color.GRAY);
        System.out.println("Returned val : " + istanbulColor);
        System.out.println(istanbul + " -> " + cityColorMap.get(istanbul));

        /** putIfAbsent with null value **/
        cityColorMap.put(izmir, null);
        Color izmirColor = cityColorMap.putIfAbsent(izmir, Color.GREEN);
        // Null is considered as absent
        System.out.println("Returned val : " + izmirColor);
        System.out.println(izmir + " -> " + cityColorMap.get(izmir));

        /****************************/
        /***** computeIfAbsent ******/
        /****************************/
        final Map<Integer, Integer> squareMap = new HashMap<>();
        Integer returnValue = squareMap.computeIfAbsent(5, this::square);
        System.out.println("computeIfAbsent returns " + returnValue + " if value is absent.");

        // Square function is not called since key 5 is present
        returnValue = squareMap.computeIfAbsent(5, this::square);
        System.out.println("computeIfAbsent returns " + returnValue + " if value is present.");

    }

    private Integer square(Integer val) {
        System.out.println("Square function is called...");
        return val * val;
    }
}
