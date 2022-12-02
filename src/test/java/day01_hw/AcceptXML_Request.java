package day01_hw;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class AcceptXML_Request {

    String url = "http://3.86.177.165:8000";
    @Test
    public void getAcceptXML() {

        Response response = RestAssured.get(url + "/api/spartans");

        int statusCode = response.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        response.body().prettyPrint();
    }
}
