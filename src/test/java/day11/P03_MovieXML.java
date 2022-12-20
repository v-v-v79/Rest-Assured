package day11;

import org.junit.jupiter.api.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class P03_MovieXML {


    @Test
    public void test1() {

        Response response = given()
                .queryParam("t", "Superman")
                .queryParam("r", "xml")
                .queryParam("apikey", "81815fe6")
                .when().get("http://www.omdbapi.com");

        XmlPath xmlPath = response.xmlPath();

        System.out.println(xmlPath.getString("root.movie"));
        System.out.println(xmlPath.getString("root.movie.@year"));

    }
}
