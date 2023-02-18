package tr.com.arrays;

import java.util.Arrays;

/**
 * @author serhat.ozkan
 */


/**
 * Note:
 *
 * Arrays.deepEquals() method compare recursively if an array contains another array.
 */
public class ArraysExample {
    public static void main(final String[] args) {

        final int[][] array1 = new int[][] {{1, 2 ,3}, {4, 5, 6}, {7, 8, 9}};
        final int[][] array2 = new int[][] {{1, 2 ,3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println("Array 1 and Array 2 equals? : " + Arrays.equals(array1, array2));
        System.out.println("Array 1 and Array 2 equals? : " + Arrays.deepEquals(array1, array2));
    }
}
