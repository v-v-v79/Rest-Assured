package day08;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.BookITUtils;
import utilities.BookitTestBase;

import static io.restassured.RestAssured.given;
public class P03_NewsApi extends BookitTestBase {

    @DisplayName("GET /api/users/me ")
    @Test
    public void test3() {
        given().accept(ContentType.JSON)
                .header("X-Api-Key", "36e08ff97f864feea772c9d88858f774").
                when().get("https://newsapi.org/v2/everything?q=bitcoin'").prettyPeek().
                then().statusCode(200);


    }
    @DisplayName("GET /api/users/me ")
    @Test
    public void test4() {
        given().accept(ContentType.JSON)
                .header("Authorization", "36e08ff97f864feea772c9d88858f774").
                when().get("https://newsapi.org/v2/everything?q=bitcoin'").prettyPeek().
                then().statusCode(200);



    }
    @DisplayName("GET /api/users/me ")
    @Test
    public void test5() {
        given().accept(ContentType.JSON)
                .header("Authorization", " Bearer 36e08ff97f864feea772c9d88858f774").
                when().get("https://newsapi.org/v2/top-headlines?country=us").prettyPeek().
                then().statusCode(200);



    }

}
