package day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import utilities.TestBaseJunit5;

public class JsonPathPractice extends TestBaseJunit5 {

    @DisplayName("Get Spartan Iinfo with JsonPath")
    @Test
    public void jsonPathPractice() {

        Response response = given().
                accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();

        /* The Same:
         * JsonPath jsonPath = given().
                accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .jsonPath();
         */

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }
}
