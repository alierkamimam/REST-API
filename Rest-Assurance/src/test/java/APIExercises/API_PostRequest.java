package APIExercises;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class API_PostRequest {
    @Test
    public void post1() {
        //1) Request Url and create body

        String URL = "https://jsonplaceholder.typicode.com/posts";

        JSONObject reqBody = new JSONObject();

        reqBody.put("title", "API");
        reqBody.put("body", "Learning api is funny");
        reqBody.put("userId", 10);

        //2) Expected Data
        JSONObject expBody = new JSONObject();

        expBody.put("title", "API");
        expBody.put("body", "Learning api is funny");
        expBody.put("userId", 10);

        // Record  Response
        Response response = given().contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post(URL);

        JsonPath actBody=response.jsonPath();

        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(201);

        Assert.assertEquals(expBody.get("title"),actBody.get("title"));
        Assert.assertEquals(expBody.get("body"),actBody.get("body"));
        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));





    }
}
