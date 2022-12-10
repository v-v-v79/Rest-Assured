package utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBaseJunit5_Spartans {

    @BeforeAll
    public static void connectionSetUp() {

        baseURI = "http://3.86.177.165:8000";
    }
}
