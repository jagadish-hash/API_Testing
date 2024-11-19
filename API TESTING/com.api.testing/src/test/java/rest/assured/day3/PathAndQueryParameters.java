package rest.assured.day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParameters {

	@Test
	void testPathAndQueryParameters() {
		// https://reqres.in/api/users?page=2
		given().pathParam("myPath", "users") // path parameters
				.queryParam("page", 2) // query Parameters

				.when().get("https://reqres.in/api/{myPath}") // Query parameters will go along with request

				.then().statusCode(200).log().all();

	}
}
