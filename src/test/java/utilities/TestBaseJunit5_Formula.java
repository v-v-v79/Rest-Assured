package utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBaseJunit5_Formula {

    @BeforeAll
    public static void connectionSetUp() {

        baseURI = "http://ergast.com/api/f1/";
    }
}
