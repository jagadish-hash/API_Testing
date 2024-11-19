package rest.assured.day3;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class TestLogs {

	@Test
	void testLogs() {
		given()

				.when().get("https://reqres.in/api/users?page=2")

				//log().body();
				//log.headers();
				.then().log().headers();
	}
}
