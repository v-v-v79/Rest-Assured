package day06.homework;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.DriverF1;
import pojo.MRData;
import utilities.TestBaseJunit5_Formula;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask01Formula1 extends TestBaseJunit5_Formula {

    String[] expectedFernandoAlonsoData = {"1", "Fernando", "Alonso", "Spanish" };

    /*
         HOMEWORK-FormulaAPI INFO --> http://ergast.com/mrd/methods/
        drivers/
        - It's for historical formula one race information
        - In this particular api , it decided to give you ml by default for response
        type and In this particular api , it decided to give you json if you add .json
        at the end of url
        FOR EXAMPLE
        - http://ergast.com/api/f1/drivers.json ---> return JSON
        BASE URL —> http://ergast.com/api/f1/
        TASK 1 :
        NOTE --> Solve same task with 4 different way
        - Use JSONPATH
        int total = jsonpath.getInt(“pathOfField”)
        - Use HAMCREST MATCHERS
        then().body(..........)
        Print givenName of Driver by using extract method after HamCrest
        - Convert Driver information to Java Structure
        Map<String,Object> driverMap=jsonpath.getMap(“pathOfDriver”)
        - Convert Driver information POJO Class
        Driver driver=getObject(“pathOfDriver”,Driver.class)

        - Given accept type is json
        - And path param driverId is alonso
        - When user send request /drivers/{driverId}.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 1
        - And givenName is Fernando
        - And familyName is Alonso
        - And nationality is Spanish
     */

    @DisplayName("GET FernandoAlonsoJsonPath")
    @Test
    public void testGetFernandoAlonsoJsonPath() {

        JsonPath jsonPath = given().log().all()
                .when()
                .get("drivers/alonso.json")
                .then()
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .extract().jsonPath();

        int totalJsonPath = jsonPath.getInt("MRData.total");
        String givenNameJsonPath = jsonPath.getString("MRData.DriverTable.Drivers[0].givenName");
        String familyNameJsonPath = jsonPath.getString("MRData.DriverTable.Drivers[0].familyName");
        String nationalityJsonPath = jsonPath.getString("MRData.DriverTable.Drivers[0].nationality");
        String[] actualFernandoAlonsoData =
                {totalJsonPath + "", givenNameJsonPath, familyNameJsonPath, nationalityJsonPath};
        int i = 0;
        for (String expectedFernandoAlonsoDatum : expectedFernandoAlonsoData) {
            assertEquals(expectedFernandoAlonsoDatum, actualFernandoAlonsoData[i]);
            i++;
        }
    }

    @DisplayName("GET FernandoAlonsoHamCrestMatchers")
    @Test
    public void testGetFernandoAlonsoHamCrestMatchers() {

        JsonPath jsonPath = given().log().all()
                .when()
                .get("drivers/alonso.json")
                .then()
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .body("MRData.total", is("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", is("Fernando"))
                .body("MRData.DriverTable.Drivers[0].familyName", is("Alonso"))
                .body("MRData.DriverTable.Drivers[0].nationality", is("Spanish"))
                .extract().jsonPath();

        System.out.println(jsonPath.getString("MRData.DriverTable.Drivers[0].givenName"));
    }

    @DisplayName("GET FernandoAlonsoJavaStructure")
    @Test
    public void testGetFernandoAlonsoJavaStructure() {

        JsonPath jsonPath = given().log().all()
                .when()
                .get("drivers/alonso.json")
                .then()
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .extract().jsonPath();

        Map<String, Object> fernandoAlonsoDataMap =
                jsonPath.getMap("MRData.DriverTable.Drivers[0]");
        String totalJsonPath = jsonPath.getString("MRData.total");
        String expectedTotal = "1";
        assertEquals(expectedTotal, totalJsonPath);
        String[] ferNandoArr = {"Fernando", "Alonso", "Spanish" };
        int i = 0;
        for (Map.Entry<String, Object> stringObjectEntry : fernandoAlonsoDataMap.entrySet()) {
            if (stringObjectEntry.getValue().equals(ferNandoArr[i])) {
                assertEquals(ferNandoArr[i], stringObjectEntry.getValue());
                i++;
            }
        }
    }

    @DisplayName("GET FernandoAlonsoPojo")
    @Test
    public void testGetFernandoAlonsoPojo() {

        JsonPath jsonPath = given().log().all()
                .when()
                .get("drivers/alonso.json")
                .then()
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .extract().jsonPath();

        DriverF1 driverF1 =
                jsonPath.getObject("MRData.DriverTable.Drivers[0]", DriverF1.class);
        MRData mrData = jsonPath.getObject("MRData", MRData.class);

        String[] actualFernandoAlonsoData =
                {mrData.getTotal(), driverF1.getGivenName(), driverF1.getFamilyName(), driverF1.getNationality()};
        int i = 0;
        for (String expectedFernandoAlonsoDatum : expectedFernandoAlonsoData) {
            assertEquals(expectedFernandoAlonsoDatum, actualFernandoAlonsoData[i]);
            i++;
        }
    }
}
