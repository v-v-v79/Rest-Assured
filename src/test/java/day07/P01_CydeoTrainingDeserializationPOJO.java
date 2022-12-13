package day07;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Students;
import utilities.TestBaseJunit5_Students;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserializationPOJO extends TestBaseJunit5_Students {


      /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222

       // Ignore all non necessray fields

     */

    @DisplayName("GET /student/{id} 2 ")
    @Test
    public void test1() {

        JsonPath jsonPath = given().log().all().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2).
                when().get("/student/{id}").
                then()
                .statusCode(200)
                .extract().jsonPath();

        Students students = jsonPath.getObject("students[0]", Students.class);
        //System.out.println(students);
        Students.Contacts contacts = jsonPath.getObject("students[0].contact", Students.Contacts.class);
        //System.out.println(contacts);
        Students.Company company = jsonPath.getObject("students[0].company", Students.Company.class);
        //System.out.println(company);
        Students.Company.Address address =
                jsonPath.getObject("students[0].company.address", Students.Company.Address.class);
        //System.out.println(address);

        assertEquals("Mark", students.getFirstName());
        assertEquals("13", students.getBatch());
        assertEquals("math", students.getMajor());

        assertEquals("mark@email.com", contacts.getEmailAddress());

        assertEquals("Cydeo", company.getCompanyName());

        assertEquals("777 5th Ave", address.getStreet());
        assertEquals("33222", address.getZipCode());

    }

}
