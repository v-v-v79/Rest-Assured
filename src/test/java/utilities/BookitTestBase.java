package utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BookitTestBase {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI="https://api.qa.bookit.cydeo.com";

    }
}
