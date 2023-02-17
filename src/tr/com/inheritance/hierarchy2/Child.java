package tr.com.inheritance.hierarchy2;

import tr.com.inheritance.hierachy1.A;
import tr.com.inheritance.hierachy1.B;
import tr.com.inheritance.hierachy1.C;

/**
 * Result of this work is; return types while overriding methods
 * return type can be specialized but can't be generalized.
 *
 * Parameters neither can be specialized nor generalized
 *
 * Access-level can be generalized / vice-versa is not acceptable (no-sense)
 * no-access specifier -> protected -> public
 *
 * private methods are not overridable / they can't be even called; how could they be overridden!
 *
 * Constructors can't be overridden
 *
 * Multiple inheritance is not supported by java; but a class can implement more than one interface
 *
 *  If a function at parent class is defined as final; base class can't override it.
 *  If parent class is declared as final, it can not be extended
 *  Also if a parent class has a private constructor; it can't be extended (? how con it be constructed / super() is disabled)
 *
 * // Exceptions
 *
 * If the superclass method does not declare an exception
 * subclass overridden method cannot declare the checked exception, but it can declare unchecked exception.
 *
 * If the superclass method declares an exception,
 * subclass overridden method can declare same, subclass exception or no exception but cannot declare parent exception.
 */
public class Child extends Parent {

    /** Covariant return type
     Parent returns A, and every B is A. So there is no problem about returning B. **/
    @Override
    public B doSth(A a) {
        // do sth....
        return new B();
    }

    /** Compile time error, parent returns B. And every A is not B. **/
   /*  @Override
    public A doSth2(B b) {
        return new C();
    }*/


    /** Compile time error, why ?
       Isn't C is subclass of B; and every C is a B.
       But what if there was another class D which extends B
       Then;
       Parent parent = new Child();
       parent.doSth2(new D()); --> D is not a C -
     */
    /*@Override
    public B doSth2(C c) {
        return new B();
    }*/

    /** Compile time error; every A is not C. So the parameters can't be generalized. **/
    /*@Override
    public C doSth3(A c) {
        return new C();
    }*/

    /** Compile time error, subclass can't make overriden functiona access-level more restrictive. Imagine;
        Child c = new Child();
        c.doSth2(); -> not accessible
       ((Parent)c).doSth2(); -> is accessible
       It wouldn't make any sense!!! **/
    /*@Override
    private C doSth2(B b) {
        return b;
    }*/

    /**
     * Access specifier can be more general than parent
     */
    @Override
    protected C doSth3(C c) {
        return new C();
    }

    /**
     * If parent function doesnt throw exception,
     * Subclass can throw runtimeexception / unchecked no problem
     *
     * But it can't throw checked exception; the condition below would have caused problem
     *   B b = new B();
     *   try {
     *     b.call(); // Assume that call() is declared at class B is declared as throws Exception
     *    }catch(Exception e) {
     *     ...
     *    }
     *
     *    A a = b;
     *    a.call(); // Assume that call() is declared without throwing an exception
     *
     *    b.call() and a.call() executes the same function with same params at memory.
     *    One has to be controlled and other does not need to be checked.
     *    Wouldn't this be ridicioluos?
     */
    @Override
    public void noThrows() throws NullPointerException {

    }
}
