package day03.homeWork;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.TestBaseJunit5_HR;

import static io.restassured.RestAssured.*;

public class Task_1_US_Countries extends TestBaseJunit5_HR {

    /*
        - Given accept type is Json
        - Path param value - US
        - When users sends request to /countries
        - Then status code is 200
        - And Content - Type is Json
        - And country_id is US
        - And Country_name is United States of America
        - And Region_id is 2
     */

    @DisplayName("GET ALL /countries?country_id=US --> JSONPATH")
    @Test
    public void testUSResponse() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("country_id", "US")
                .when()
                .get("/countries/{country_id}");

        response.prettyPrint();

        int region_id = response.path("region_id");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("US", response.path("country_id"));
        assertEquals("United States of America", response.path("country_name"));
        assertEquals(2, region_id);

    }

}
