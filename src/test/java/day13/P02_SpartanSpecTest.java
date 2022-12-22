package day13;


import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBaseNew;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class P02_SpartanSpecTest extends SpartanAuthTestBaseNew {

    @Test
    public void getAllSpartans() {

        given().log().all().accept(ContentType.JSON)
                .auth().basic("admin","admin").
        when().get("/spartans").
        then().
                statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void getAllSpartansWithReqResSpec() {

        given().spec(reqSpec).
        when().get("/spartans").
        then().spec(resSpec);


    }

    @Test
    public void getSingleSpartanWithReqResSpec() {

        given().spec(reqSpec).
                pathParam("id", 3)
                .when().get("/spartans/{id}").prettyPeek()
                .then().spec(resSpec)
        .body("id", is(3));


    }

    @Test
    public void getSingleSpartanWithReqResSpecMethods() {

        given().spec(dynamicReqSpec("user", "user")).
                pathParam("id", 3)
                .when().get("/spartans/{id}").prettyPeek()
                .then().spec(dynamicResSpec(200))
        .body("id", is(3));
    }
}
