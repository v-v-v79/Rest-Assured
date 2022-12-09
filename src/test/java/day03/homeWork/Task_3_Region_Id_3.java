package day03.homeWork;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.TestBaseJunit5_HR;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Task_3_Region_Id_3 extends TestBaseJunit5_HR {
    /*
        - Given accept type is Json
        - Query param value q= region_id 3
        - When users sends request to /countries
        - Then status code is 200
        - And all regions_id is 3
        - And count is 6
        - And hasMore is false
        - And Country_name are;
        Australia,China,India,Japan,Malaysia,Singapore

     */
    @DisplayName("GET param value q= region_id 3 from /countries")
    @Test
    public void testGetRegionId() {

        Response response = given().
                accept(ContentType.JSON).
                queryParam("q", "{\"region_id\":3}").
                when().
                get("countries/");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        JsonPath jsonPath = response.jsonPath();
        List<String> expectedCountryNames =
                List.of("Australia", "China", "India", "Japan", "Malaysia", "Singapore");
        System.out.println(expectedCountryNames);
        List<String> actualCountryNames = jsonPath.getList("items.country_name");
        System.out.println(actualCountryNames);
        int i = 0;
        for (String expectedCountryName : expectedCountryNames) {
            assertEquals(expectedCountryName, actualCountryNames.get(i));
            i++;
        }
        List<Integer> actualRegionIds = jsonPath.getList("items.region_id");
        System.out.println(actualRegionIds);
        for (Integer actualRegionId : actualRegionIds) {
            assertEquals(3, actualRegionId);
        }
        System.out.println(actualRegionIds.size());
        assertEquals(6, actualRegionIds.size());
        System.out.println(jsonPath.getBoolean("hasMore"));
        assertFalse(jsonPath.getBoolean("hasMore"));
    }
}
