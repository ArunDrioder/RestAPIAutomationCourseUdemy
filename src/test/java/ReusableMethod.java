import io.restassured.path.json.JsonPath;

public class ReusableMethod
{
    public static JsonPath rawToJson(String response)
    {
        JsonPath jsPath = new JsonPath(response);
        return jsPath;

    }
}
