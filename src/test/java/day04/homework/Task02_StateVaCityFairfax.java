package day04.homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pojo.ZipPlace;
import pojo.ZipSearchCity;
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

        ZipSearchCity zipSearchCity = jsonPath.getObject("", ZipSearchCity.class);

        for (ZipPlace place : zipSearchCity.getPlaces()) {
            System.out.println(place);
        }
        assertEquals("US", zipSearchCity.getCountryAbbreviation());
        assertEquals("United States", zipSearchCity.getCountry());
        assertEquals("Fairfax", zipSearchCity.getPlaceName());
        for (ZipPlace place : zipSearchCity.getPlaces()) {
            System.out.println(place.getPlaceName());
            assertTrue(place.getPlaceName().contains("Fairfax"));
        }
        for (ZipPlace place : zipSearchCity.getPlaces()) {
            System.out.println(place.getPostCode());
            assertTrue(place.getPostCode().startsWith("22"));
        }
    }

    @DisplayName("GET states and cities from us/")
    @ParameterizedTest(name = "{index} city is {1}")
    @CsvSource({"NY, New York City",
            "CO, Denver",
            "VA, Fairfax",
            "MA, Boston",
            "MD, Annapolis Junction"})
    public void testGetCityState(String state, String city) {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("us/" + state + "/" + city)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath();

        ZipSearchCity zipSearchCity =
                jsonPath.getObject("", ZipSearchCity.class);

        assertEquals(city, zipSearchCity.getPlaceName());
        int count = 0;
        for (ZipPlace place : zipSearchCity.getPlaces()) {
            assertTrue(place.getPlaceName().contains(city));
            count++;
        }
        System.out.println(state + ", " + city + ", " + count);
    }

    @DisplayName("GET states and cities from us/")
    @ParameterizedTest
    @CsvSource({"NY, New York City",
            "CO, Denver",
            "VA, Fairfax",
            "MA, Boston",
            "MD, Annapolis Junction"})
    public void testGetCityStatesMehmet(String state, String city) {

        int placeNumber = given()
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when()
                .get("us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places.'place name'", everyItem(containsString(city)))
                .extract().jsonPath().getList("places").size();

        System.out.println(placeNumber);
    }

    @DisplayName("GET states and cities from file with request us/{state}/{city}")
    @ParameterizedTest(name = "{index} city is {1}")
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    public void testGetCityStateFile(String state, String city, int total) {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("us/" + state + "/" + city)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath();

        ZipSearchCity zipSearchCity =
                jsonPath.getObject("", ZipSearchCity.class);

        assertEquals(city, zipSearchCity.getPlaceName());
        assertEquals(state, zipSearchCity.getStateAbbreviation());
        int count = 0;
        for (ZipPlace place : zipSearchCity.getPlaces()) {
            assertTrue(place.getPlaceName().contains(city));
            count++;
        }
        assertEquals(total, count);

        System.out.println(state + ", " + city + ", " + count);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv",numLinesToSkip = 1)
    public void test2Mehmet(String state,String city,int zipCount ){


        given().baseUri("https://api.zippopotam.us")
                .pathParam("state",state)
                .pathParam("city",city).
                when().get("/us/{state}/{city}").
                then().statusCode(200).body("places",hasSize(zipCount));
    }
}
