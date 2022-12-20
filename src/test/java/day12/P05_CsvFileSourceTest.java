package day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class P05_CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv", numLinesToSkip = 1)
    public void test1( int n1, int n2, int total) {
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);
        System.out.println("total = " + total);
    }
}
