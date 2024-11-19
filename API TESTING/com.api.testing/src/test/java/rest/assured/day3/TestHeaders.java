package rest.assured.day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;



public class TestHeaders {

	//@Test
	void testHeards() {
		
		given()
		
		.when().get("https://www.google.com/")
		
		.then().header("content-type", "text/html; charset=ISO-8859-1").header("content-encoding", "gzip")
		.header("server", "gws").log().all();
	}
	
	@Test
	void getHeards() {
		
		Response res=given()
		
		.when().get("https://www.google.com/");
		
		//Capture single header
		String header=res.getHeader("content-type");
		System.out.println("The value of header is--"+header);
		
		//Capture multiple header
		Headers headers=res.getHeaders();
		for (Header hd:headers) {
			System.out.println("The header is "+"'"+hd.getName()+"'"+"and the value of header is "+"'"+ hd.getValue()+"'");
		}
	}
}
