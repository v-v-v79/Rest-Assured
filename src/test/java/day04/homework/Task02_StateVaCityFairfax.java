package day04.homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pojo.ZipPlace;
import pojo.ZipSearchFairfax;
import utilities.TestBaseJunit5_ZIP;

import static io.restassured.RestAssured.given;

public class Task02_StateVaCityFairfax extends TestBaseJunit5_ZIP {

    /*
            Given accept application/json
            And path state is va
            And path city is fairfax
            When I send a GET request to /us endpoint
            Then status code must be 200
            And content type must be application/json
            And payload should contain following information
            country abbreviation is US
            country  United States
            place name  Fairfax
            each places must contain fairfax as a value
            each post code must start with 22
     */

    @DisplayName("GET zip 50000 from us/")
    @Test
    public void testGetZip50000() {

        JsonPath jsonPath = given().log().all()
                .accept(ContentType.JSON)
                .when()
                .get("us/va/fairfax")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath();

        ZipSearchFairfax zipSearchFairfax = jsonPath.getObject("", ZipSearchFairfax.class);

        for (ZipPlace place : zipSearchFairfax.getPlaces()) {
            System.out.println(place);
        }
        assertEquals("US", zipSearchFairfax.getCountryAbbreviation());
        assertEquals("United States", zipSearchFairfax.getCountry());
        assertEquals("Fairfax", zipSearchFairfax.getPlaceName());
        for (ZipPlace place : zipSearchFairfax.getPlaces()) {
            System.out.println(place.getPlaceName());
            assertTrue(place.getPlaceName().contains("Fairfax"));
        }for (ZipPlace place : zipSearchFairfax.getPlaces()) {
            System.out.println(place.getPostCode());
            assertTrue(place.getPostCode().startsWith("22"));
        }
    }
}
