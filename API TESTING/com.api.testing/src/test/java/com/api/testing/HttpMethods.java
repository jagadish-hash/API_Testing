package com.api.testing;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

//Rest Assured supports BDD format

/*
 * Given(){
 * Content type, set cookies,add auth, add parms, set header info etc...
 * }
 * 
 * When(){
 * get, post,put,delete
 * }
 * 
 * Then(){
 * Validate status code, extract response, extract Headers cookies and Response body..
 * }
 * */
public class HttpMethods {
int id;
	@Test(priority = 1)
	void getUsers() {
		
		given()
		
		.when().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
		.log().all();
		
	}
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap data=new HashMap();
		data.put("name", "raj");
		data.put("job", "trainer");
		
		id=given().contentType("application/json").body(data)
		
		.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
		
	}
	
	@Test(priority = 3,dependsOnMethods = {"createUser"})
	void updateUser() {
		HashMap data=new HashMap();
		data.put("job", "temp");
		
		given().contentType("application/json").body(data)
		
		.when().put("https://reqres.in/api/users/"+id)
		.then().statusCode(200)
		.log().all();
	}
	
	@Test(priority = 4,dependsOnMethods = {"updateUser"})
	void deleteUser() {
		
		
		given()
		
		.when().delete("https://reqres.in/api/users/"+id)
		.then().statusCode(204)
		.log().all();
	}
}
