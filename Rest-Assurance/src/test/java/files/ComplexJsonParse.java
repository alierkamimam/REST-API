package files;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {
    public static void main(String[] args) {


        JsonPath jsonPath = new JsonPath(files.payload.CoursePrice());

        // Print No of courses returned by API
        int courseCount = jsonPath.getInt("courses.size()");
        System.out.println(courseCount);

        //Print Purchase Amount

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        System.out.println("----------------------");

        //Print Title of the first course
        String titleOfFirstCourse=jsonPath.getString("courses[0].title");
        System.out.println(titleOfFirstCourse);
        System.out.println("----------------------");

        // Print All course titles and their respective Prices

        for (int i = 0; i <courseCount ; i++) {
            String titleAllCourse=jsonPath.getString("courses["+i+"].title");
            int allPrices=jsonPath.getInt("courses["+i+"].price");
            System.out.println(titleAllCourse+"\n"+allPrices);
            System.out.println("--");
        }

        System.out.println("Print no of copies sold by RPA Course\n");

        for (int i = 0; i < courseCount; i++) {
            String titleAllCourse=jsonPath.get("courses["+i+"].title");
            if (titleAllCourse.equalsIgnoreCase("RPA")){
                int copies=jsonPath.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }

        }


        System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
        int sum=0;

        for (int i = 0; i <courseCount ; i++) {
            int allPrices=jsonPath.getInt("courses["+i+"].price");
            int copies=jsonPath.get("courses["+i+"].copies");
            sum+=allPrices*copies;

        }
        System.out.println(sum);
        Assert.assertEquals(purchaseAmount,sum);



    }
}
