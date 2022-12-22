package day12;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.TestBaseJunit5_Spartans;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class P09_SpartanPOSTDDT extends TestBaseJunit5_Spartans {

    /**
     *
     *  POST SPARTAN DDT
     *
     *  Given content type is json
     *  And accept type is json
     *  When I POST Spartan
     *  And status code needs to 201
     *  Verify spartan informations macthing with dynamic taht we provide
     *
     *  Generate DUMMY DATA
     *  https://www.mockaroo.com/
     *
     *  name
     *  gender
     *  phone
     *
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/Spartan.csv", numLinesToSkip = 1)
    public void postSpartansFromCsvFile(String name, String gender, long phone) {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", name);
        requestBody.put("gender", gender);
        requestBody.put("phone", phone);

        JsonPath jsonPath = given().accept(ContentType.JSON).log()
                .body().contentType(ContentType.JSON)
                .body(requestBody).when()
                .post("/api/spartans")
                .then().statusCode(201).contentType("application/json")
                .extract().jsonPath();

        assertEquals(requestBody.get("name"), jsonPath.getString("data.name"));
        assertEquals(requestBody.get("gender"), jsonPath.getString("data.gender"));
        assertEquals(requestBody.get("phone"), jsonPath.getLong("data.phone"));
    }
}
