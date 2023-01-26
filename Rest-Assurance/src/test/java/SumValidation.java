import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourses() {

        JsonPath js = new JsonPath(files.payload.CoursePrice());
        int count = js.getInt("courses.size()");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        int sum=0;
        for (int i = 0; i < count; i++) {
            int allPrices = js.getInt("courses[" + i + "].price");
            int copies = js.get("courses[" + i + "].copies");
            int amount = allPrices * copies;
            System.out.println(amount);
            sum+=amount;


        }
        Assert.assertEquals(purchaseAmount,sum,"not equald");


    }
}
