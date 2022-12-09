package day03.homeWork;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.TestBaseJunit5_HR;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task_2_Dept_Id_80 extends TestBaseJunit5_HR {

    /*
        - Given accept type is Json
        - Query param value - q={"department_id":80}
        - When users sends request to /employees
        - Then status code is 200
        - And Content - Type is Json
        - And all job_ids start with 'SA'
        - And all department_ids are 80
        - Count is 25
     */

    @DisplayName("GET Query param value - q={\"department_id\":80}" +
            "from /employees")
    @Test
    public void testDeptIdFromEmployees() {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .and()
                .contentType(ContentType.JSON)
                .when()
                .get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        List<String> jobIds = response.path("items.job_id");
        System.out.println(jobIds);

        List<Integer> departmentIds = response.path("items.department_id");
        System.out.println(departmentIds);

        int count = departmentIds.size();
        for (String jobId : jobIds) {
            assertTrue(jobId.startsWith("SA"));
        }
        for (Integer departmentId : departmentIds) {
            assertEquals(80, (int) departmentId);
        }
        assertEquals(25, count);
    }

}
