package utilities;


import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Spartan;

import static io.restassured.RestAssured.given;

public class SpartanUtil {


    public static Spartan createSpartan() {
        Spartan spartan=new Spartan();
        Faker faker=new Faker();
        spartan.setName(faker.name().firstName());
        //"1234545363463"                  method is giving me the String then I need to convert into te long
        spartan.setPhone(Long.parseLong(faker.numerify("###########")));

        int i = faker.number().numberBetween(0, 1);
        if (i==0){
            spartan.setGender("Female");
        }else {
            spartan.setGender("Male");
        }

        return spartan;
    }

    public static Response postSpartan(Spartan spartan) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans");
        return response;


    }

    public static Response getSpartan(int createdSpartanId) {
        Response response = given()
                .accept(ContentType.JSON)
             //   .contentType(ContentType.JSON)
                .pathParam("id",createdSpartanId)
                .when()
                .get("/api/spartans/{id}");
        return response;

    }

    public static Response updateSpartan(int createdSpartanId, Spartan updatedSpartan) {

        Response response = given()
                .accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                .pathParam("id",createdSpartanId)
                .body(updatedSpartan)
                .when()
                .put("/api/spartans/{id}");
        return response;



    }

    public static Response deleteSpartan(int createdSpartanId) {
        Response response = given()
                // .accept(ContentType.JSON)
                //   .contentType(ContentType.JSON)
                .pathParam("id",createdSpartanId)
                .when()
                .delete("/api/spartans/{id}");
        return response;

    }
}
