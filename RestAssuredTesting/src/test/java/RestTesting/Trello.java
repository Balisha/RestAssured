package RestTesting;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Trello {
	
	public static String baseurl = "https://api.trello.com";
	private Object id;
	
	@Test(priority=0)
	  public void createboards()
	  {
		RestAssured.baseURI = baseurl;
		
		Response response = given().queryParam("name","BoardMya")
		.queryParam("key","8c810baf574b75580d3f9ca001778071")
		.queryParam("token","d8748885d77a6f44b3189674d023a26fba1945b27af3301b65ae533404558229")
		.header("Content-Type","application/json")
		
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		
		.extract().response();
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		//nw i have to fetch the id
		 id = js.get("id");
		System.out.println(id);
		
	  }
	
	@Test(priority=1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
		Response response = given()
		.queryParam("key","8c810baf574b75580d3f9ca001778071")
		.queryParam("token","d8748885d77a6f44b3189674d023a26fba1945b27af3301b65ae533404558229")
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
	}
	@Test(priority = 2)
	public void DeleteBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
	Response response =	given()
		.queryParam("key", "8c810baf574b75580d3f9ca001778071")
		.queryParam("token", "d8748885d77a6f44b3189674d023a26fba1945b27af3301b65ae533404558229")
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
		String jsonresponse = response.asString();
		
		System.out.println(jsonresponse);
	}
	
	
	

}
