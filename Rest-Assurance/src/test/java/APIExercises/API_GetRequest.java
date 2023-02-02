package APIExercises;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class API_GetRequest {

    @Test
    public void get01() {
        String URL = "https://jsonplaceholder.typicode.com/posts/44";

        JSONObject expectBody = new JSONObject();
        expectBody.put("userId", 5);
        expectBody.put("title", "optio dolor molestias sit");

        Response response=given().when().get(URL);
        //response.prettyPeek();

       response
               .then()
               .assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON);

        JsonPath actBody=response.jsonPath();

        Assert.assertEquals(expectBody.get("userId"),actBody.get("userId"));


    }


}
