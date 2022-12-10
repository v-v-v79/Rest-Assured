package utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBaseJunit5_ZIP {

    @BeforeAll
    public static void connectionSetUp() {

        baseURI = "http://api.zippopotam.us/";
    }
}
