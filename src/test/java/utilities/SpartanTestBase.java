package utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://3.80.111.193:8000";

    }
}
