package tr.com.overloading;

import tr.com.inheritance.hierachy1.A;
import tr.com.inheritance.hierachy1.B;
import tr.com.inheritance.hierachy1.C;

import java.util.Arrays;

public class Overloading {

    public static void main(final String[] args) {
        final Overloading overloading = new Overloading();

        final Object o = new B();
        final A a = new A();
        final A ab = new B();
        final B b = (B) ab;

        /**
         * Java doc says:
         * If more than one member method is both accessible and applicable to a method invocation …
         * The Java programming language uses the rule that the most specific method is chosen.
         *
         * and
         *
         * Overloading is resolved at compile time, so you can't rely on the runtime types.
         */
        overloading.process(o); // Object ? -> will call process(Object)
        overloading.process(a); // A a: -> will call process(A)
        overloading.process(ab); // A ab = new B(); -> Poly type can be B - But class declaration is A -> will call A
        overloading.process(b); // B b = (ab) -> Will call the one with param B
        overloading.process(null); // See java doc. Most specific one will be called ->  process(C c) will be called

        System.out.println(overloading.add(new int[] {1, 2, 3, 4, 5}));
        System.out.println(overloading.add(new double[] {1d, 2d, 3d, 4d, 5d}));
        System.out.println(overloading.add(1, 2 ,3));
        System.out.println(overloading.add(1d, 2d ,3d));
    }

    /**
     * With inherited types
     */
    public void process(Object obj) {
        System.out.println("process(Object obj) is called...");
    }

    public void process(A a) {
        System.out.println("process(A a) is called...");
    }

    public void process(B b) {
        System.out.println("process(B b) is called...");
    }

    public void process(C c) {
        System.out.println("process(C c) is called...");
    }

    /**
     * Note: Overloading is resolved at compile time, so you can't rely on the runtime types.
     * Since 2 method below has the same erasure type they can't be overloaded
     */
 /*   public void process(List<? extends String> list) {

    }

    public void process(List<Integer> list) {

    }*/


    /**
     * Return types isn't part of overloading
     */
   /* public int func() {
        return 0;
    }

    public double func() {
        return 0.0d;
    }*/

    /**
     *  Parameter count and type is counted as method overloading
     */
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double add(double a, double b, double c) {
        return a + b + c;
    }


    public int add(int... values) {
        return Arrays.stream(values).sum();
    }

    public double add(double... values) {
        return Arrays.stream(values).sum();
    }

}
