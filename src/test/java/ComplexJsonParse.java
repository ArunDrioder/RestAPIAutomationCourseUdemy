import Data.RequestPayload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse
{
    public static void main(String[] args)
    {
        JsonPath jsonPath = new JsonPath(RequestPayload.coursePrice());

        //print no.of courses returned by API
        int courseCount = jsonPath.getInt("courses.size()");
        System.out.println(courseCount);

        //print purchase amount

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        //print the title of the 1st course

        String firstCourseTitle = jsonPath.get("courses[0].title");
        System.out.println(firstCourseTitle);

        //print all course titles & their respective prices :

        for(int i = 0 ; i<courseCount; i++)
        {
            String allCoursesTitle = jsonPath.get("courses["+i+"].title");
            System.out.println(allCoursesTitle);

            int allCoursesPrice = jsonPath.getInt("courses["+i+"].price");
            System.out.println(allCoursesPrice);

            //suppose if the price is String then,

            String allCoursesPriceInString = jsonPath.get("courses["+i+"].price").toString();
            System.out.println("In String format :" + " "+allCoursesPriceInString);
            if (allCoursesTitle.equalsIgnoreCase("RPA"))
            {
                int soldRPACourseCopyCount = jsonPath.get("courses["+i+"].copies");
                System.out.println("The RPA Sold Count is : "+" "+soldRPACourseCopyCount);
            }


        }







    }
}
