package tr.com.functional;

import java.util.function.DoubleToIntFunction;
import java.util.function.ToDoubleBiFunction;

public class PrimitiveFunctions {

    public static void main(final String[] args) {
        final DoubleToIntFunction multiplyWith2 = d -> (int)(d * 2);
        System.out.println(multiplyWith2.applyAsInt(3d));

        final ToDoubleBiFunction<String, String> add = (s1, s2) -> (Double.valueOf(s1) + Double.valueOf(s2));
        System.out.println("\"3\" + \"4\" = " + add.applyAsDouble("3", "4" ));
    }
}
