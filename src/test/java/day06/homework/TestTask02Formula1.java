package day06.homework;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ConstructorPOJO;
import pojo.MRData;
import utilities.TestBaseJunit5_Formula;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestTask02Formula1 extends TestBaseJunit5_Formula {

    String names = "benetton, mercedes, team_lotus";
    List<String> namesUpperCaseFirstLetter = Arrays.asList("Benetton", "Brabham-Repco",
            "Brawn", "BRM", "Cooper-Climax", "Ferrari", "Lotus-Climax", "Lotus-Ford",
            "Matra-Ford", "McLaren", "Mercedes", "Red Bull", "Renault", "Team Lotus",
            "Tyrrell", "Vanwall", "Williams");

    /*
         HOMEWORK-FormulaAPI INFO --> http://ergast.com/mrd/methods/
        drivers/
        - It's for historical formula one race information
        - In this particular api , it decided to give you ml by default for response
        type and In this particular api , it decided to give you json if you add .json
        at the end of url
        FOR EXAMPLE
        - http://ergast.com/api/f1/drivers.json ---> return JSON
        BASE URL —> http://ergast.com/api/f1/
        TASK 2 :
            NOTE --> Solve same task with 4 different way
            - Use JSONPATH
            int total = jsonpath.getInt(“pathOfField”)
            - Use HAMCREST MATCHERS
            then().body(..........)
            Print all names of constructor by using extract method after HamCrest
            - Convert Constructor information to Java Structure
            List<Map<String,Object>>
            constructorMap=jsonpath.getList(“pathOfConsts”)
            - Convert Constructor information POJO Class
            List<ConstructorPOJO>
            constr=getObject(“pathOfConstr”,ConstructorPOJO.class)
            NOTE —> There is a class in JAVA That’s why give your class name
            ConstrutorPOJO to separate from existing one. Wrong imports may cause
            issue
            - Given accept type is json
            - When user send request /constructorStandings/1/constructors.json -
            Then verify status code is 200
            - And content type is application/json; charset=utf-8
            - And total is 17
            - And limit is 30
            - And each constructor has constructorId
            - And constructor has name
            - And size of constructor is 17 (using with hasSize)
            - And constructor IDs has “benetton”, “mercedes”,”team_lotus”
            - And names are in same order by following list
            List<String> names =Arrays.asList(“Benetton”,”Brabham-Repco”,”
            Brawn”,” BRM”,”Cooper-Climax”,”Ferrari”,”Lotus-Climax”,”Lotus-Ford”,”
            Matra-Ford”,” McLaren”,”Mercedes”,”Red Bull”,”Renault”,”Team Lotus”,”
            Tyrrell”,”Vanwall”,” Williams”);
     */

    @DisplayName("GET constructors")
    @Test
    public void testGetConstructorsJsonPath() {

        //Hamcrest Matchers

        JsonPath jsonPath = given().log().all()
                .when()
                .get("constructorStandings/1/constructors.json")
                .then()
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .body("MRData.total", is ("17"))
                .body("MRData.limit", is("30"))
                .body("MRData.ConstructorTable.Constructors.constructorId", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.constructorId[0]",
                       containsString("benetton"))
                .body("MRData.ConstructorTable.Constructors.constructorId[10]",
                       containsString("mercedes"))
                .body("MRData.ConstructorTable.Constructors.constructorId[13]",
                       containsString("team_lotus"))
                .body("MRData.ConstructorTable.Constructors.name", equalTo(namesUpperCaseFirstLetter))
                .extract().jsonPath();

        //JSON Path

        List<String> cId = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        List<String> nMs = jsonPath.getList("MRData.ConstructorTable.Constructors.name");

        int x = 0;
        int y = 0;
        for (String nM : nMs) {
            assertEquals(namesUpperCaseFirstLetter.get(y), nM);
            y++;
        }

        for (String s : cId) {
            assertNotEquals("", s);
            if(names.contains(s))
                x++;
        }
        assertEquals(3, x);

        String totalJsonPath = jsonPath.getString("MRData.total");
        assertEquals("17", totalJsonPath);
        String limitJsonPath = jsonPath.getString("MRData.limit");
        assertEquals("30", limitJsonPath);

        //Java structure

        List<Map<String, Object> >constructors =
                jsonPath.getList("MRData.ConstructorTable.Constructors");
        int i = 0;
        int count = 0;
        for (Map<String, Object> constructorData : constructors) {
            assertNotEquals("", constructorData.get("constructorId").toString());
            assertNotEquals("", constructorData.get("name").toString());
            if(names.contains(constructorData.get("constructorId").toString()))
                count++;
            assertEquals(namesUpperCaseFirstLetter.get(i).toLowerCase(),
                    constructorData.get("name").toString().toLowerCase());
            i++;
        }
        assertEquals(3, count);
        assertEquals(17, constructors.size());


        //POJO way

        List<ConstructorPOJO> constructorPOJOS =
                jsonPath.getList("MRData.ConstructorTable.Constructors", ConstructorPOJO.class);
        MRData mrData = jsonPath.getObject("MRData", MRData.class);

        int j = 0;
        int k = 0;
        for (ConstructorPOJO constructorPOJO : constructorPOJOS) {
            assertNotEquals("", constructorPOJO.getConstructorId());
            assertNotEquals("", constructorPOJO.getName());
            if(names.contains(constructorPOJO.getConstructorId()))
                j++;
            assertEquals(namesUpperCaseFirstLetter.get(k), constructorPOJO.getName());
            k++;
        }
        assertEquals(3, j);
        assertEquals("30", mrData.getLimit());
        assertEquals("17", mrData.getTotal());

    }
}
