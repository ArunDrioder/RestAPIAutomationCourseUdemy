import Data.RequestPayload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidations
{
    @Test
    public void sumOfCoursePrice()
    {
        int sum = 0;
        JsonPath js = new JsonPath(RequestPayload.coursePrice());
        int count = js.getInt("courses.size()");
        for (int i = 0; i<count;i++)
        {
            int priceValue  = js.get("courses["+i+"].price");
            int copyValue = js.get("courses["+i+"].copies");
            int totalValue = priceValue * copyValue;
            System.out.println(totalValue);
            sum = sum + totalValue;

        }
        System.out.println(sum);

        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);
    }

}
