package day04.homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utilities.TestBaseJunit5_ZIP;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task02_ZipCode50000 extends TestBaseJunit5_ZIP {


    @DisplayName("GET zip 50000 from us/")
    @Test
    public void testGetZip50000() {

        JsonPath jsonPath = given().log().all()
                .accept(ContentType.JSON)
                .when()
                .get("us/50000")
                .then()
                .statusCode(404)
                .contentType("application/json")
                .extract().jsonPath().prettyPeek();

    }
    @DisplayName("GET zips from us/")
    @ParameterizedTest(name = "{index} name is {0}")
    @ValueSource(ints = {22030, 22031, 22032, 22033, 22034, 22035, 22036})
    public void testGetZips(int zip) {

        JsonPath jsonPath = given().log().all()
                .accept(ContentType.JSON)
                .when()
                .get("us/" + zip)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath().prettyPeek();

    }
}
