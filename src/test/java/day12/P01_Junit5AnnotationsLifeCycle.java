package day12;

import org.junit.jupiter.api.*;

public class P01_Junit5AnnotationsLifeCycle {

    @BeforeAll //Needs to be static
    public static void init() {

        System.out.println("--------------> BeforeAll is running");
    }

    @BeforeEach
    public void beforeEach() {

        System.out.println("--------------> BeforeEach is running");
    }

    @AfterEach
    public void afterEach() {

        System.out.println("--------------> AfterEach is running");
    }

    @Test
    public void test1() {
        System.out.println(("--------------> Test1 is running"));
    }

    //Ignore Test --> TestNG --> @Ignore
    //@Disabled --> It is possible to use without message as well
    @Disabled("This test is ignored")
    @Test
    public void test2() {
        System.out.println(("--------------> Test2 is running"));
    }

    @AfterAll //Needs to be static
    public static void destroy() {

        System.out.println("--------------> AfterAll is running");
    }
}
