package files;

import io.restassured.path.json.JsonPath;

public class ReUseAbleMethods {

    public static JsonPath rawToJson(String str){
        JsonPath jsonPath=new JsonPath(str);
        return jsonPath;
    }
}
