package utilities;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
public class BookITUtils {


    public static String getToken(String email,String password){

        String accessToken = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password).
                when().get("/sign")
                .then().statusCode(200)
                .extract().jsonPath()
                .getString("accessToken");

        // This is a verification to see we are getting not null Value
        assertThat(accessToken,not(emptyOrNullString()));

        return "Bearer "+accessToken;
    }


    // how  you build connection here ?
    /*
        Before all will init BASEURI since it is static field for RestAssured
        when we send any request it will use as BASEURI --> BOOKIT BASEURI


     */
    /*

    if we change the role of the User from teamleader to teacher can we use the same token?

        public static String getTokenByRole(String role){

            String email;
            String password;

        switch(role){

        case "team-leader":

                email=ConfigurationReader.getProperty("team-leader-email");
                password =ConfigurationReader.getProperty("team-leader-email")
                break;
         case "team-member":

         .....
         ...
         ...

        }

        email and password after switch case


        String accessToken = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password).
                when().get("/sign")
                .then().statusCode(200)
                .extract().jsonPath()
                .getString("accessToken");

        // This is a verification to see we are getting not null Value
        assertThat(accessToken,not(emptyOrNullString()));

        return "Bearer "+accessToken;




        }


     */





}
