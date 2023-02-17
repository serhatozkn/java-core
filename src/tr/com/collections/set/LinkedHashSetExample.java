package tr.com.collections.set;

import java.util.LinkedHashSet;

/**
 * LinkedHashSet maintains LinkedList along with HashMap to store its elements.
 *
 * Advantages
 *
 * -LinkedHashSet maintains insertion order of elements. i.e elements are placed as they are inserted.
 * -LinkedHashSet gives the performance of order O(1) for insertion, removal and retrieval operations.
 * -LinkedHashSet allows maximum one null element.
 * -Iterator returned by LinkedHashSet is fail-fast in nature. i.e You will get ConcurrentModificationException if they are modified after the creation of Iterator object
 *
 * Disadvantages
 *
 * -LinkedHashSet maintains LinkedList along with HashMap to store its elements for which it requires more memory
 * -The performance of LinkedHashSet is between HashSet and TreeSet. Its performance is almost similar to HashSet. But slightly in the slower side as it also maintains LinkedList internally to maintain the insertion order of elements.
 */
public class LinkedHashSetExample {

    public static void main(final String[] args) {

        final LinkedHashSet<Integer> integerSet = new LinkedHashSet<>();
        integerSet.add(1);
        integerSet.add(2);
        integerSet.add(2);
        integerSet.add(3);
        integerSet.add(4);
        integerSet.add(4);
        integerSet.add(5);

        // Insertion Order is maintained
        integerSet.stream().forEach(System.out::println);
        System.out.println("---");
        //Remove an element
        integerSet.remove(3);
        //Order is still maintained
        integerSet.stream().forEach(System.out::println);
    }
}
