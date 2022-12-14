package day07.homework;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import pojo.Items;
import utilities.TestBaseJunit5_HR;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApiPostPutDelete extends TestBaseJunit5_HR {

    int expectedRegionIdPost1 = 100;
    String expectedRegionNamePost = "Test Region";
    String expectedRegionNamePut = "Wooden Region";

    @Order(value = 1)
    @DisplayName("Task_01 POST a region then do validations")
    @Test
    public void Test01PostRegion() {

        Items.Regions newRegionRequestBody = new Items.Regions();
        newRegionRequestBody.setRegion_id(expectedRegionIdPost1);
        newRegionRequestBody.setRegion_name(expectedRegionNamePost);

        given().accept(ContentType.JSON)
                .log().body().contentType(ContentType.JSON)
                .body(newRegionRequestBody)
                .when().post("regions/")
                .then().statusCode(201)
                .contentType(ContentType.JSON);
    }

    @Order(value = 2)
    @DisplayName("Task_02 GET a region then do validations")
    @Test
    public void Test01GetRegion() {
        Items.Regions newPostedRegion =
                given().accept(ContentType.JSON)
                        .log().all()
                        .when().get("/regions/100")
                        .then().statusCode(200).extract().jsonPath()
                        .getObject("", Items.Regions.class);

        assertEquals(expectedRegionIdPost1, newPostedRegion.getRegion_id());
        assertEquals(expectedRegionNamePost, newPostedRegion.getRegion_name());

    }

    @Order(value = 3)
    @DisplayName("Task_03 PUT a region then do validations")
    @Test
    public void Test02PutRegion() {
        Items.Regions newRegionRequestBody1 = new Items.Regions();
        newRegionRequestBody1.setRegion_id(expectedRegionIdPost1);
        newRegionRequestBody1.setRegion_name(expectedRegionNamePut);

        given()
                .contentType(ContentType.JSON)
                .body(newRegionRequestBody1)
                .when().put("regions/100")
                .then().statusCode(200);

    }

    @Order(value = 4)
    @DisplayName("Task_04 GET a region then do validations")
    @Test
    public void Test02GetRegion() {
        Items.Regions newPutRegion =
                given().accept(ContentType.JSON)
                        .log().all()
                        .when().get("/regions/100")
                        .then().statusCode(200).extract().jsonPath()
                        .getObject("", Items.Regions.class);

        assertEquals(expectedRegionIdPost1, newPutRegion.getRegion_id());
        assertEquals(expectedRegionNamePut, newPutRegion.getRegion_name());
    }

    @Order(value = 5)
    @DisplayName("Task_05 DELETE a region then do validations")
    @Test
    public void Test02DeleteRegion() {
        given()
                .when().delete("/regions/100")
                .then().statusCode(200);
    }
}

