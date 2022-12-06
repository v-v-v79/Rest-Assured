package day02;

import io.restassured.RestAssured;
import org.junit.Before;

public abstract class TestBase {

    @Before
    public void setUpConnection() {
        RestAssured.baseURI = "http://3.86.177.165:8000";
    }
}
