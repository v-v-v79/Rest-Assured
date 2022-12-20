package day11;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilities.TestBaseJunit5_Spartans;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanXMLTest extends TestBaseJunit5_Spartans {

    /**
     * Given accept type application/xml
     */
@DisplayName("GET /api/spartans with using XML path")
    @Test
    public void test1() {

        given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans").prettyPeek()
                .then().statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("Male"));

    }
    @DisplayName("GET /api/spartans with using XMLPath")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();
        System.out.println("xmlPath.getString(\"List.item[0].name\") = " + xmlPath.getString("List.item[0].name"));
        System.out.println("xmlPath.getString(\"List.item[-1].name\") = " + xmlPath.getString("List.item[-1].name"));
        System.out.println("xmlPath.getString(\"List.item.name\") = " + xmlPath.getList("List.item.name"));
        System.out.println("xmlPath.getString(\"List.item[-1].id\") = " + xmlPath.getString("List.item[-1].id"));

        List<Object> spartans = xmlPath.getList("List.item");
        System.out.println(spartans.size());


    }
}
