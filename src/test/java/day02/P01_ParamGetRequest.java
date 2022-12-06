package day02;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_ParamGetRequest {

    String url = "http://3.86.177.165:8000/";

    @Test
    public void paramGetRequest(){
        Response response = RestAssured.given().
                accept(ContentType.JSON)
                .pathParam("id", 3)
                .when()
                .get(url + "api/spartans/{id}");
        //verify statusCode
        assertEquals(response.statusCode(), 200);

        //verify contentType
        assertEquals("application/json", response.contentType());

        System.out.println("id: " + response.body().path("id").toString());
        System.out.println("name: " + response.body().path("name").toString());
        System.out.println("gender: " + response.body().path("gender").toString());
        System.out.println("phone: " + response.path("phone").toString());

        int id = response.body().path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(3, id);
        assertEquals("Fidole", name);
        assertEquals("Male", gender);
        assertEquals(6105035231L, phone);

    }


}
