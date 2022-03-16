package RestTesting;

import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Ecommerce {
	 public static String baseurl = "https://ecommerceservice.herokuapp.com/";
	 public static String message;
		public static String accesstoken;
		//private Object id;
		public static String id;
		
	 
	 @Test(priority = 0,enabled=false)
		public void signup()
		{
			RestAssured.baseURI =baseurl;
			
		String 	requestbody = "{\n"
				+ "	\"email\": \"suuooop@gmail.com\",\n"
				+ "	\"password\": \"krishna@123\"\n"
				+ "}";
		
		Response resposne = given()
				.header("Content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/signup")
				
				.then()
				.assertThat().statusCode(201).contentType(ContentType.JSON)
				.extract().response();	
		
		String jsonresponse = resposne.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		//nw i have to fetch the id
		message = js.get("message");
		System.out.println(message);	
	}
		
		
		@Test(priority = 0)
		public void Login()
		{
			RestAssured.baseURI =baseurl;
			
		String 	requestbody = "{\n"
				+ "	\"email\": \"suuooop@gmail.com\",\n"
				+ "	\"password\": \"krishna@123\"\n"
				+ "}";
		
		Response resposne = given()
				.header("Content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/login")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();	
		
		String jsonresponse = resposne.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		//nw i have to fetch the id
		accesstoken = js.get("accessToken");
		System.out.println(accesstoken);
		

		}
		@Test(priority = 1)
		 public void getallusers()
		 {
			RestAssured.baseURI =baseurl;
			
				
			
			Response resposne = given()
					.header("Content-Type","application/json")
					.header("Authorization","bearer "+accesstoken)
					
					.when()
					.get("/user")
					
					.then()
					.assertThat().statusCode(200).contentType(ContentType.JSON)
					.extract().response();	
			
			String jsonresponse = resposne.asString();
			//i want to convert the response in to json format
			//why do i use jsonpath to convert the string in to a json format
			JsonPath js = new JsonPath(jsonresponse);
			//nw i have to fetch the id
		    id = js.get("users[21]._id");
			System.out.println("users 20 is:"+id);
			
		 }
		@Test(priority = 2)
		 public void delete()
		 {
			RestAssured.baseURI =baseurl;
			
			
			Response resposne = given()
					.header("Content-Type","application/json")
					
					.header("Authorization","bearer "+accesstoken)
					
					.when()
					.delete("/user/"+id)
					
					.then()
					.assertThat().statusCode(200).contentType(ContentType.JSON)
					.extract().response();	
			
			String jsonresponse = resposne.asString();
			//i want to convert the response in to json format
			//why do i use jsonpath to convert the string in to a json format
			//JsonPath js = new JsonPath(jsonresponse);
			//nw i have to fetch the id
			//accesstoken = js.get("accesstoken");
			System.out.println(jsonresponse);
			
		 }
	
}
