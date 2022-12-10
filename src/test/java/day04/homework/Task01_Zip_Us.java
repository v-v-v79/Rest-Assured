package day04.homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.TestBaseJunit5_ZIP;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Task01_Zip_Us extends TestBaseJunit5_ZIP {

    /*
            Given Accept application/json
            And path zipcode is 22031
            When I send a GET request to /us endpoint
            Then status code must be 200
            And content type must be application/json
            And Server header is cloudflare
            And Report-To header exists
            And body should contains following information
            post code is 22031
            country  is United States
            country abbreviation is US
            place name is Fairfax
            state is Virginia
            latitude is 38.8604
     */

    List<String> states = List.of();
    List<String> latitude = List.of();
    List<String> placesName = List.of("Fairfax");


    @DisplayName("GET zip 22031 from us/")
    @Test
    public void testGetZip22031_fromUs() {


        JsonPath jsonPath = given()
                .log().all()
                .accept(ContentType.JSON)
                .when()
                .get("us/22031").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Server", equalTo("cloudflare"))
                .header("Report-To", notNullValue())
                .body("'post code'", equalTo("22031"))
                .body("country", equalTo("United States"))
                .body("'country abbreviation'", equalTo("US"))
                .body("places[0].'place name'", equalTo("Fairfax"))
                .body("places[0].state", equalTo("Virginia"))
                .body("places[0].latitude", equalTo("38.8604"))
                .extract().jsonPath();

        List<Map<String, Object>> list = jsonPath.getList("places");
        System.out.println(list);
    }
}
