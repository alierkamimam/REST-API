package APIExercises;

import baseUrlRepo.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class API_BaseUrl_TestUsage extends JsonPlaceHolderBaseUrl {


    @Test
    public void test01(){
        specJsonPlace.pathParam("pp1",22);

        Response response=given().spec(specJsonPlace).when().get("{pp1}");

    }
}
