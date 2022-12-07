package utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class TestBaseJunit5 {

    @BeforeAll
    public static void connectionSetUp() {

        baseURI = "http://3.86.177.165:8000";
    }
}
