import Data.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics
{
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        //Add Place

        String responseString = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(RequestPayload.addPlaceApiPayload()).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).
                header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println("The Success response is" + " "+responseString);

        JsonPath path = new JsonPath(responseString);
        String extractedPlaceId = path.get("place_id");
        System.out.println("The Place ID is" + " "+ extractedPlaceId);

        //Update Place

        String newAddress = "70 winter walk, USA";

        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+extractedPlaceId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all()
                .statusCode(200).body("msg",equalTo("Address successfully updated"));


        //Get the Place

       String getPlaceResponse =  given().log().all().queryParam("key","qaclick123")
                .queryParam("place-id",extractedPlaceId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        System.out.println("The response after changing to post method is"+" "+getPlaceResponse);




//        System.out.println("The Get Place API Response is :" +" "+getPlaceResponse);
//
//       JsonPath jsPath = new JsonPath(getPlaceResponse);
//       String actualAddress = jsPath.get("address");
//        System.out.println("Actual Address is" + actualAddress);


    }
}
