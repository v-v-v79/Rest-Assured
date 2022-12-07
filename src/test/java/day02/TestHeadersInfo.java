package day02;

import io.restassured.response.Response;
import org.junit.Test;
import utilities.TestBase;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class TestHeadersInfo extends TestBase {

    @Test
    public void testHeadersInfo() {

        Response response = get("/api/hello");

        String contentType = response.contentType();
        String contentLength = response.header("Content-Length");
        String dateHeater = response.header("Date");
        String connectionHeader = response.header("Connection");

        System.out.println("contentType = " + contentType);
        System.out.println("contentLength = " + contentLength);
        System.out.println("dateHeater = " + dateHeater);
        System.out.println("connectionHeader = " + connectionHeader);

        String expectedContentType = "text/plain;charset=UTF-8";
        String expectedContentLength = "17";
        String expectedConnection = "keep-alive";

        assertEquals(expectedContentType, contentType);
        assertEquals(expectedContentLength, contentLength);
        assertEquals(expectedConnection, connectionHeader);

    }
}
