package day08;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.BookITUtils;
import utilities.BookitTestBase;

import static io.restassured.RestAssured.given;
public class P02_BookITTest extends BookitTestBase {



    String email="lfinnisz@yolasite.com";
    String password="lissiefinnis";
    String accessToken= BookITUtils.getToken(email,password);

    @DisplayName("GET /api/campuses ")
    @Test
    public void test1() {
        System.out.println(accessToken);
        given().accept(ContentType.JSON)
                .header("Authorization",accessToken).
        when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);


    }

    // Create new Util class and it will generate token based on your email and password
    // BookITUtils.getToken(String username,String password)


    @DisplayName("GET /api/users/me ")
    @Test
    public void test2() {
        System.out.println(accessToken);
        given().accept(ContentType.JSON)
                .header("Authorization", accessToken).
        when().get("/api/users/me").prettyPeek().
        then().statusCode(200);


    }@DisplayName("GET /api/users/me ")
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
