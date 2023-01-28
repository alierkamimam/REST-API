
import files.ReUseAbleMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) throws IOException {
        //given - all input detail
        //when - Submit the Apı -resource http method
        //then - validate response

        //Add place--> Update Place with New Address --> Get Place to validate if New address is present in response

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Ali Erkam İMAM\\Desktop\\addPlace.json")))).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println("place_id:" + js.getString("place_id") + "\n");

        //Update Place

        String newaddress = "Summer Walk,Africa";
        System.out.println("::::::::::::::Update place:::::::::::::");
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
                body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newaddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").
                when().put("/maps/api/place/update/json").
                then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get Place

        System.out.println("::::::::::::::Get place:::::::::::::");

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
                when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath js1 = ReUseAbleMethods.rawToJson(getPlaceResponse);

        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newaddress);







    }
}
