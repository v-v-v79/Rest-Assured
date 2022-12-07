package day04;

import utilities.TestBaseJunit5_HR;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class P01_HRWithJsonPath extends TestBaseJunit5_HR {

    /*
    Given accept type is application/json

     */

    @DisplayName("Get all employees with Query limit=200 --> JSONPATH ")
    @Test
    public void test1() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .queryParam("limit", 200)
                .when().get("/employees")
                        .jsonPath();
        /**TODO practice jsonPath*/



        //Status code 200
        //get all emails
        //get all emails who is working as IT_PROG


        //get all employees first names with salary > 10000

        //System.out.println(jsonPath.getString("items.max{it.salary}.first_name"));
    }

}
