package utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBaseJunit5_Bookit {

    @BeforeAll
    public static void connectionSetUp() {

        baseURI = "https://api.qa3.bookit.cydeo.com";
    }
}
