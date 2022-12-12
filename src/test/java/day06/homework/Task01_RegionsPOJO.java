package day06.homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Region;
import utilities.TestBaseJunit5_HR;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task01_RegionsPOJO extends TestBaseJunit5_HR {

    /*
        Given accept is application/json
        When send request  to /regions endpoint
        Then status should be 200
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
            -- Create Regions POJO
            -- And ignore field that you dont need
     */

    @DisplayName("GET /regions from HR test content")
    @Test
    public void testGetRegionsPojoPractice() {

        JsonPath jsonPath = given().log().all()
                .accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .contentType("application/json")
                .statusCode(200)
                .extract().jsonPath();


//        List<Integer> itemsList = jsonPath.getList("items");
//        System.out.println(itemsList);
        /**TODO Why is it printing Objects besides it is INTEGER*/

        List<Region> itemsList = jsonPath.getList("items", Region.class);
        System.out.println(itemsList);

        List<Integer> regionIdsList = new ArrayList<>();
        for (Region region : itemsList) {
            regionIdsList.add(region.getRegion_id());
        }
        System.out.println("regionIdsList = " + regionIdsList);

        int[] expectedRegionIds = {1, 2, 3, 4};
        int i = 0;
        for (Integer regionId : regionIdsList) {
            assertEquals(expectedRegionIds[i], regionId);
            i++;
        }
        String[] expectedRegionNames = {"Europe", "Americas", "Asia", "Middle East and Africa"};
        List<String> actualRegionNames = new ArrayList<>();
        for (Region region : itemsList) {
           actualRegionNames.add(region.getRegion_name());
        }
        i = 0;
        for (String actualRegionName : actualRegionNames) {
            assertEquals(expectedRegionNames[i], actualRegionName);
            i++;
        }
    }

}
