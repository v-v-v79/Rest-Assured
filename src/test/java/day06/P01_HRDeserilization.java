package day06;


import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.TestBaseJunit5_HR;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_HRDeserilization extends TestBaseJunit5_HR {
    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */
    @DisplayName("GET /locations to deserilization into Java Collections")
    @Test
    public void test1() {

        JsonPath jsonPath = given().log().uri().
                when().get("/locations").
                then().statusCode(200).extract().response().jsonPath();


        System.out.println("====== GET FIRST LOCATION  ======");
        Map<String, Object> firstMap = jsonPath.getMap("items[0]");
        System.out.println("firstMap = " + firstMap);


        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        Map<String, Object> firstMapLinks = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstMapLinks = " + firstMapLinks);
        // How to get href value from firstMapLinks
        System.out.println("firstMapLinks.get(\"href\") = " + firstMapLinks.get("href"));


        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String, Object>> allLocationsMap = jsonPath.getList("items");
        for (Map<String, Object> eachLocations : allLocationsMap) {
            System.out.println("eachLocations = " + eachLocations);
        }

        System.out.println("====== FIRST LOCATION  FROM ALL LOCATIONS ======");
        System.out.println("allLocationsMap.get(0) = " + allLocationsMap.get(0));

        System.out.println("====== FIRST LOCATION ID FROM ALL LOCATIONS======");
        System.out.println("allLocationsMap.get(0).get(\"location_id\") = " + allLocationsMap.get(0).get("location_id"));


        System.out.println("====== FIRST LOCATION COUNTRY_ID FROM ALL LOCATIONS======");
        System.out.println("allLocationsMap.get(0).get(\"country_id\") = " + allLocationsMap.get(0).get("country_id"));


        System.out.println("====== GET FIRST LOCATION FIRST LINK ALL LOCATIONS ====== ");

        System.out.println("====== LAST LOCATION ID ALL LOCATIONS ======");


    }
}
