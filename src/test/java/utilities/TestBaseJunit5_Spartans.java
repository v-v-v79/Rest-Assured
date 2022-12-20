package utilities;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utilities.TestBaseJunit5_Spartans;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class TestBaseJunit5_Spartans {

    @BeforeAll
    public static void connectionSetUp() {

        baseURI = "http://3.86.177.165:8000";
    }
}
