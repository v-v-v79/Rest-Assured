package utilities;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBaseNew {

    public static RequestSpecification reqSpec;
    public static RequestSpecification reqUserSpec;
    public static ResponseSpecification resSpec;

    @BeforeAll
    public static void init(){

        baseURI="http://3.86.177.165";
        port = 7000;
        basePath="/api";

        reqSpec = given().log().all().accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        reqUserSpec = given().log().all().accept(ContentType.JSON)
                .auth().basic("user", "user");

        resSpec = expect().statusCode(200)
                        .contentType(ContentType.JSON);

    }

    public static RequestSpecification dynamicReqSpec(String username, String password) {

        return given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic(username, password);
    }
    public static ResponseSpecification dynamicResSpec(int statusCode) {

        return expect()
                .statusCode(statusCode)
                .contentType(ContentType.JSON);
    }
}
