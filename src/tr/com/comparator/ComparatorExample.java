package tr.com.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Import not: Java from Oracle off. document
 * The ordering imposed by a comparator c on a set of elements S is said to be consistent with equals
 * if and only if c.compare(e1, e2)==0 has the same boolean value as e1.equals(e2) for every e1 and e2 in S.
 *
 * For example, suppose one adds two elements a and b such that (a.equals(b) && c.compare(a, b) != 0) to an empty TreeSet
 * with comparator c. The second add operation will return true (and the size of the tree set will increase) because a and b
 * are not equivalent from the tree set's perspective, even though this is contrary to the specification of the Set.add method.
 */
public class ComparatorExample {

    public static void main(final String[] args) {
        final List<Student> students = new ArrayList<>();
        fillStudents(students);
        // Sort by first name
        students.sort(Comparator.comparing(Student::firstName));
        System.out.println("Sorted by name: ");
        System.out.println(students);

        students.sort(Comparator.comparing(Student::firstName).thenComparing(Student::surname));
        System.out.println("Sorted by name and surname: ");
        System.out.println(students);

        // What if we insert null values to the list
        // Two sort functions above will fail -> Throw NLPE

        students.add(null);
        students.add(null);

        students.sort(Comparator.nullsFirst(Comparator.comparing(Student::firstName)));
        System.out.println("Sorted by name nulls are first:");
        System.out.println(students);
        // There is also nullsLast function
    }

    private static void fillStudents(final List<Student> students) {
        students.add(new Student("Serhat", "Ozkan", 21027301));
        students.add(new Student("Serhat", "Usta", 21028525));
        students.add(new Student("Serhat", "Atil", 21038525));
        students.add(new Student("Mustafa", "Karakaya", 21032685));
        students.add(new Student("Ferhat", "Salmanli", 2112345123));
        students.add(new Student("Ali Haydar", "Atil", 2102564724));
    }

    public record Student(String firstName, String surname, int studentId) { }
}
