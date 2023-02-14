package tr.com.optional;

import java.util.Optional;

public class OrElseGetExample {

    public static void main(final String[] args) {
        Optional<Car> car = Optional.ofNullable(null);

        System.out.println("Expecting constructor to be called");
        Car honda = car.orElse(new Car());

        car = Optional.of(honda);

        System.out.println("Event though car is present constructor is called below. Unnecessary call.");
        Car newCar = car.orElse(new Car());

        System.out.println("To avoid this unnecessary call we can use orElseGet(Supplier)");
        newCar = car.orElseGet(Car::new);
        System.out.println("Constructor did not called for the 3. time. Saved resource (minor depending on object size).");
        // If the car object was not present (.isPresent() == false) then the constructor reference passed as
        // supplier method was going to be invoked.
    }


    private static class Car {

        private static int constructorCallCounter = 0;
        private Car() {
            System.out.println(String.format("## Constructor of %s invoked for the %d. time ## %s",
                    getClass().getSimpleName(), ++constructorCallCounter, System.lineSeparator()));
        }
    }
}
