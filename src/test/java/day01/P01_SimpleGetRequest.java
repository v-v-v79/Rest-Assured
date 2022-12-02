package day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SimpleGetRequest {

    String url = "http://3.86.177.165:8000/api/spartans";

    @Test
    public void simpleGetRequest() {

        // send request to url and get response as Response interface
        Response response = RestAssured.get();

        // Both same no difference.We are gonna use to assert
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getBody() = " + response.getBody());
        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));


        // it gives all status line
        System.out.println("response.statusLine() = " + response.statusLine());


        int actualStatusCode = response.getStatusCode();

        Assertions.assertEquals(200,actualStatusCode);

        //how to print into screen ?
        response.prettyPrint();


    }
}
