package utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBaseJunit5_CydeoTraining {

    @BeforeAll
    public static void connectionSetUp() {

        baseURI = "https://api.training.cydeo.com/";
    }
}
