package rest.assured.day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ParsingJsonResponseData {
	
	//@Test
	void testJsonResponse() {
		
		/*
		 * //Approach 1
		 * 
		 * given().contentType(ContentType.JSON)
		 * 
		 * .when().get("http://localhost:3000/students")
		 * 
		 * .then().statusCode(200) .header("Content-Type","application/json")
		 * .body("[1].name", equalTo("ramesh")) .log().all();
		 */
		 
		
		//Approach 2
		Response res=given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/students");
		
		Assert.assertEquals(res.getStatusCode(), 200); 
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		Assert.assertEquals(res.jsonPath().get("[1].name").toString(), "ramesh");
		
	}

	@Test
	void testJsonResponseBodyData() {
		boolean status=false;
		Response res = given().contentType(ContentType.JSON)
				.when().get("https://reqres.in/api/users?page=2");

		/*
		 * Assert.assertEquals(res.getStatusCode(), 200);
		 * Assert.assertEquals(res.header("Content-Type"), "application/json");
		 * Assert.assertEquals(res.jsonPath().get("[1].name").toString(), "ramesh");
		 */	
		
		JSONObject obj=new JSONObject(res.asString());
		
		//print all email ids
		for(int i=0;i<obj.getJSONArray("data").length();i++) {
			System.out.println(obj.getJSONArray("data").getJSONObject(i).get("email").toString());
		}
		
		//check if the specific email is present or not
		for(int i=0;i<obj.getJSONArray("data").length();i++) {
			String emailId=obj.getJSONArray("data").getJSONObject(i).get("email").toString();
			if(emailId.equals("george.edwards@reqres.in")) {
				status=true;
			}
		}
		
		Assert.assertEquals(status, true);
	}
}
