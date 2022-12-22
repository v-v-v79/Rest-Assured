package day12;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.SpartanAuthTestBaseNew;
import utilities.TestBaseJunit5_Spartans;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithSpecFromCSV extends SpartanAuthTestBaseNew {



    @ParameterizedTest
    @CsvFileSource(resources = "/Spartan2.csv", numLinesToSkip = 1)
    public void getSpartansFromCsvFile(String username, String password, int id, int statusCode) {

        given().spec(dynamicReqSpec(username, password))
                .pathParam("id", 3)
                .when().get("/spartans/{id}")
                .then().spec(dynamicResSpec(statusCode))
                .body("id", is(id));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DELETE_RBAC.csv", numLinesToSkip = 1)
    public void deleteSpartansFromCsvFile(String username, String password, int id, int statusCode) {

        given().spec(dynamicReqSpec(username, password))
                .pathParam("id", id)
                .when().delete("/spartans/{id}")
                .then().spec(dynamicResSpec(statusCode));
    }
}
