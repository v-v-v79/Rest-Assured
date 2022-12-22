package day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.ExcelUtil;
import utilities.TestBaseJunit5_Bookit;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class P08_BookitLoginDDTTest extends TestBaseJunit5_Bookit {



    @ParameterizedTest
    @MethodSource({"getQA3Sheet"})
    public void testBookItQA3Sheet(Map<String, String> userInfo) {

        String accessTocken = given().accept(ContentType.JSON)
                .queryParams(userInfo)
                .when().get("/sign")
                .then().statusCode(200).extract()
                .jsonPath().getString("accessToken");
        System.out.println(accessTocken);
    }
    public static List<Map<String, String>> getQA3Sheet() {

        ExcelUtil excelUtil = new ExcelUtil
                ("src/test/resources/BookitQa3.xlsx", "QA3");
        return excelUtil.getDataList();
    }

    //create a method to read bookitqa3 excel file information (QA3 Sheet)
    //use those information as an email and password to send a get request to /sign endpoint
    //verify you got 200 for each request
    //print accessToken for each request

}
