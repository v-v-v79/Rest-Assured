package day13;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBaseNew;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class P01_OldRestAssured extends SpartanAuthTestBaseNew {


    @Test
   public void getAllSpartan() {


        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
        .when().get("/spartans").
        then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(2))
                .body("id[1]",is(3));

    }


    @Test
    public void getAllSpartanOldWay() {


        given().accept(ContentType.JSON)
                .auth().basic("admin","admin").
        expect().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(2))
                .body("id[1]",is(3)).
        when().get("/spartans");


    }

    /*

    OLD WAY --> EXPECT()
        - it works like soft assertion

    NEW WAY --> then()  (This is the that we are gonna use now also in the future if they will not release new version )
        - it works like hard assertion

     */






}
