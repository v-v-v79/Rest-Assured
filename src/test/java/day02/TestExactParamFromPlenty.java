package day02;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

public class TestExactParamFromPlenty extends TestBase {
    @Test
    public void testExactParamsFromPlenty(){

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");
        int firstId = response.path("id[0]");
        String firstNameInTheList = response.path("name[0]");
        System.out.println("firstId = " + firstId);
        System.out.println("firstNameInTheList = " + firstNameInTheList);

        int lastId = response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        List<String> spartansNames = response.path("name");
        int i = 1;
        for (String spartansName : spartansNames) {
            System.out.println(i + ") " + spartansName);
            i++;
        }

    }
}
