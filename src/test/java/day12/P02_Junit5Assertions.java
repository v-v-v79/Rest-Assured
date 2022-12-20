package day12;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class P02_Junit5Assertions {

    /**
     * HARD ASSERT --> ASSERT
     * - Test Execution will be aborted if assert failed
     */

    @Test
    public void hardAssert() {

        assertEquals(10, 5 + 5);
        System.out.println("----- First assert is done");
        assertEquals(9, 5 + 5);
        System.out.println("----- Second assert is done");
        //This is Hard assert ---> that's why it stops running after assertion is failed
        assertEquals(10, 5 + 5);
        System.out.println("----- Third assert is done");

    }

    /**
     * SOFT ASSERT --> ASSERT
     * - Test Execution will CONTINUE  EVEN if assert failed
     *
     *    <<TestNG is using SoftAssert
     */

    @Test
    public void softAssert() {

        assertAll("Learning soft assert",
                () -> assertEquals(10, 5 + 5),
                () -> assertEquals(9, 5 + 5),
                () -> assertEquals(9, 5 + 5)
            );

    }
}
