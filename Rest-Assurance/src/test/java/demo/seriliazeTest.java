package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.A;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class seriliazeTest {
    public static void main(String[] args) {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite(" https://rahulshettyacademy.com");
        p.setName("Frontline house");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response res = given()
                .log().all()
                .queryParam("key ", "qaclick123")
                .body(p)
                .when()
                .post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);
        JsonPath js = new JsonPath(responseString);
        System.out.println("---------------");
        String s = js.getString("place_id");
        System.out.println(s);


    }
}
