package demo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .setContentType(ContentType.JSON)
                .build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("postmanerkam@gmail.com");
        loginRequest.setUserPassword("Erkam@123");

        RequestSpecification reqLogin = given()
                .spec(req)
                .body(loginRequest);


        LoginResponse loginResponse = reqLogin
                .when().log().all()
                .post("/api/ecom/auth/login")
                .then()
                .extract()
                .response()
                .as(LoginResponse.class);
        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();

        System.out.println("::Token::--> " + token);
        System.out.println("::UserId::--> " + userId);


        //Add Product

        RequestSpecification addProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .addHeader("authorization", token)
                .build();

        RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
                .param("productName", "Laptop")
                .param("productAddedBy", userId)
                .param("productCategory", "fashion")
                .param("productSubCategory", "shirts")
                .param("productPrice", "9500")
                .param("productDescription", "HP")
                .param("productFor", "men")
                .multiPart("productImage", new File("C:\\Users\\Ali Erkam İMAM\\Downloads\\maxresdefault.jpg"));

        String addProductResponse = reqAddProduct
                .when()
                .post("/api/ecom/product/add-product")
                .then()
                .log().all()
                .extract()
                .response()
                .asString();

        JsonPath js = new JsonPath(addProductResponse);
        String productId = js.getString("productId");
        System.out.println(productId);


        //Create Product
        RequestSpecification createOrderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .addHeader("authorization", token)
                .setContentType(ContentType.JSON)
                .build();


        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCountry("india");
        orderDetail.setProductOrderedId(productId);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);


        Orders orders = new Orders();
        orders.setOrders(orderDetailList);


        RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
        String responseAddOrder = createOrderReq
                .when()
                .post("/api/ecom/order/create-order")
                .then()
                .log().all()
                .extract()
                .response()
                .asString();

        System.out.println(responseAddOrder);


        //Delete Product
        RequestSpecification deleteProdBaseReq=new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .addHeader("authorization", token)
                .setContentType(ContentType.JSON)
                .build();

        RequestSpecification deleteProdReq=given().log().all().spec(deleteProdBaseReq.pathParam("productId",productId));

        String deleteProductResponse=deleteProdReq
                .when()
                .delete("https://rahulshettyacademy.com/api/ecom/product/delete-product/{productId}")
                .then().log()
                .all().extract()
                .response()
                .asString();

        JsonPath js1=new JsonPath(deleteProductResponse);
        Assert.assertEquals("Product Deleted Successfully",js1.get("message"));












    }
}
