package rest.assured.day3;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Cookies {

	//@Test
	void testCookies() {
		
		given()
		
		.when().get("https://www.google.com/")
		
		.then().cookie("AEC","AVYB7cqo6I6Z4avOcVAfol7C-n7mOs4gBekpHHc2PXbQHs2Ba1bEUAofBxc").log().all();
	}
	
	@Test
	void getCookiesInfo() {
		
		Response res=given()
		
		.when().get("https://www.google.com/");
		
		//Get Single cookie
		String cookieValue=res.getCookie("NID");
		
		System.out.println("The value of cookie is "+cookieValue);
		
		//Get all the cookie information
		Map<String, String> cookies=res.getCookies();
		System.out.println("Cookies Key -- " + cookies.keySet());
		System.out.println("Cookies Values -- " + cookies.values());
	}
}
