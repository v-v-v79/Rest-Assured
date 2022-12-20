package day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40, 50})
    //@Test // needs to be removed in that case
    public void test1 (int number){
        System.out.println(number);
        assertTrue(number < 30);

    }

    @ParameterizedTest(name = "{index} name is {0}")
    //{index} tast name {0} - dada
    @ValueSource(strings = {"Jose", "Mehmet", "Alena", "Ada", "Asya"})
    //@Test // needs to be removed in that case
    public void test2 (String names){
        System.out.println(names);
        assertTrue(names.startsWith("A"));

    }
}
