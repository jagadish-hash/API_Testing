package rest.assured.day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;



public class DifferentWaysToCreatePostReqBody {

	// Different Ways to create POST Request
	// 1. Using HashMap
	// 2. Using org.json
	// 3. Using POJO class
	// 3. Using external json file data

	// 1. POST request creation using HashMap
//	@Test
	public void testPostUsingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "alpha1");
		data.put("location", "Raichur");
		data.put("phone", "34567876543");
		String cources[] = { "java", "testNG" };
		data.put("courses", cources);
		
		given().contentType("application/json").body(data)
		
		.when().post("http://localhost:3000/students")
		
		.then().statusCode(201)
		.body("name", equalTo("alpha1"))
		.body("location", equalTo("Raichur"))
		.body("phone", equalTo("34567876543"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("testNG"))
		.header("Content-Type", "application/json")
		.log().all();
	}
	
	// 2. POST request creation using org.json library
//	@Test
	public void testPostUsingOrjJsonLibrary() {
		//change is here
		JSONObject data=new JSONObject();
		data.put("name", "alpha1");
		data.put("location", "Raichur");
		data.put("phone", "34567876543");
		String cources[] = { "java", "testNG" };
		data.put("courses", cources);
		//change is here
		given().contentType("application/json").body(data.toString())
		
		.when().post("http://localhost:3000/students")
		
		.then().statusCode(201)
		.body("name", equalTo("alpha1"))
		.body("location", equalTo("Raichur"))
		.body("phone", equalTo("34567876543"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("testNG"))
		.header("Content-Type", "application/json")
		.log().all();
	}
	
	// 3. POST request creation using POJO
		//@Test
		public void testPostUsingPOJO() {
			
			PostRequestPOJO data=new PostRequestPOJO();
			data.setName("alpha1");
			data.setLocation("Raichur");
			data.setPhone("34567876543");
			String cources[] = { "java", "testNG" };
			data.setCourses(cources);
			
			
			given().contentType("application/json").body(data)
			
			.when().post("http://localhost:3000/students")
			
			.then().statusCode(201)
			.body("name", equalTo("alpha1"))
			.body("location", equalTo("Raichur"))
			.body("phone", equalTo("34567876543"))
			.body("courses[0]", equalTo("java"))
			.body("courses[1]", equalTo("testNG"))
			.header("Content-Type", "application/json")
			.log().all();
		}
		
		// 4. POST request creation using external data file
				@Test
				public void testPostUsingExternalDataFile() {
					JSONObject data=null;
					try {
						File file=new File(".\\body.json");
						FileReader reader=new FileReader(file);
						JSONTokener jt=new JSONTokener(reader);
						data = new JSONObject(jt);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					given().contentType("application/json").body(data.toString())
					
					.when().post("http://localhost:3000/students")
					
					.then().statusCode(201)
					.body("name", equalTo("alpha1"))
					.body("location", equalTo("Raichur"))
					.body("phone", equalTo("34567876543"))
					.body("courses[0]", equalTo("java"))
					.body("courses[1]", equalTo("testNG"))
					.header("Content-Type", "application/json")
					.log().all();
				}
}
