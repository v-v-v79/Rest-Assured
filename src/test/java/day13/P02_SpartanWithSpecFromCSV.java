package day13;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.SpartanAuthTestBaseNew;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P02_SpartanWithSpecFromCSV extends SpartanAuthTestBaseNew {



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
