package day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class P06_MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {
        System.out.println("name = " + name);
    }

    public static List<String> getNames() {
        List<String> listOfNames =
                Arrays.asList("Ksenia", "Ivan", "Slava", "Max");
        return listOfNames;
    }
}

