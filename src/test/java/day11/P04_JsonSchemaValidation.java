package day11;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.TestBaseJunit5_Spartans;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class P04_JsonSchemaValidation extends TestBaseJunit5_Spartans {

    @DisplayName("GET /api/spartans to validate with JsonSchemaValidator")
    @Test
    public void test1() {

        given().
                accept(ContentType.JSON)
                .when()
                .get(baseURI + "/api/spartans")
                .then().statusCode(200)
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath
                                ("AllSpartansSchema.json"));
    }

    @DisplayName("GET /api/spartans/{id} to validate with JsonSchemaValidator")
    @Test
    public void test2() {

        given().
                accept(ContentType.JSON)
                .when().pathParam("id", 3)
                .get(baseURI + "/api/spartans/{id}")
                .then().statusCode(200)
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath
                                ("SingleSpartanSchema.json"));
    }
    @DisplayName("GET /api/spartans/search to validate with JsonSchemaValidator")
    @Test
    public void test3() {

        given().
                accept(ContentType.JSON)
                .when()
                .get(baseURI + "/api/spartans/search")
                .then().statusCode(200)
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath
                                ("SearchSpartanSchema.json"));
    }
}
