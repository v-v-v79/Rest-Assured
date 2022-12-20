package day11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import utilities.TestBaseJunit5_Spartans;
import io.restassured.http.ContentType;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class P02_ResponseTimeTest extends TestBaseJunit5_Spartans{

    @DisplayName("GET /api/spartans/ to get response time")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .time(both(greaterThan(200l)).and(lessThan(1000l)))
                .extract().response();

        System.out.println("response.getTimeIn(TimeUnit.MICROSECONDS) = "
                + response.getTimeIn(TimeUnit.MICROSECONDS));

    }
}
