package stepimplementations;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.readPropertyFile;

public class FetchUserListImplementation {

	RequestSpecification request;
	Response response;
	String requestBody;
	String baseURL;
	String basePath;

	readPropertyFile property = new readPropertyFile();


	public void sendValidRequestToServer() throws IOException {
		
		System.out.println("===============================================================");

		baseURL = property.readPropertyData("baseUrl");
		basePath = property.readPropertyData("valid.fetchUserList.basepath");

		request = RestAssured.given();
		response = request.baseUri(baseURL).basePath(basePath).log().all().get();

	}

	public void verifySuccessResponseStatusCode() {

		int actualResponseStatusCode = response.getStatusCode();
		int expectedResponseStatusCode = 200;
		Assert.assertEquals(actualResponseStatusCode, expectedResponseStatusCode);

		System.out.println("===============================================================");
		System.out.println("actualResponseStatusCode : " + actualResponseStatusCode);
		System.out.println("expectedResponseStatusCode : " + expectedResponseStatusCode);
		System.out.println("===============================================================");

	}

	public void verifyResponseBody() throws ParseException {

		String responseBody = response.getBody().asString();

		System.out.println("Response Body is:" + responseBody);
		System.out.println("===============================================================");

		JSONParser jsonParser = new JSONParser();
		
		JSONArray object = (JSONArray) jsonParser.parse(responseBody);
		
		int ActualTotalUserListFetched = object.size();
		int ExpectedTotalUserListFetched = 10;
		
		System.out.println("ActualTotalUserListFetched : " + ActualTotalUserListFetched);
		System.out.println("ExpectedTotalUserListFetched : " + ExpectedTotalUserListFetched);

		Assert.assertEquals(ActualTotalUserListFetched, ExpectedTotalUserListFetched);
		
		JSONObject firstjsonobject = (JSONObject)object.get(0);
		String ActualUsernameOfFirstUser = (String) firstjsonobject.get("username");	
		String ExpectedUsernameOfFirstUser = "Bret";
		
		System.out.println("ActualUsernameOfFirstUser : " + ActualUsernameOfFirstUser);
		System.out.println("ExpectedUsernameOfFirstUser : " + ExpectedUsernameOfFirstUser);

		Assert.assertEquals(ActualUsernameOfFirstUser, ExpectedUsernameOfFirstUser);
		
		
		JSONObject lastjsonobject = (JSONObject)object.get(9);
		String ActualUsernameOfLastUser = (String) lastjsonobject.get("username");	
		String ExpectedUsernameOfLastUser = "Moriah.Stanton";
		
		System.out.println("ActualUsernameOfLastUser : " + ActualUsernameOfLastUser);
		System.out.println("ExpectedUsernameOfLastUser : " + ExpectedUsernameOfLastUser);

		Assert.assertEquals(ActualUsernameOfLastUser, ExpectedUsernameOfLastUser);
		
		System.out.println("===============================================================");
		System.out.println("Admin User has fetched all the 10 user list successfully");
		System.out.println("===============================================================" + "\n");

	}

	public void sendInvalidRequestToServer() throws IOException {

		baseURL = property.readPropertyData("baseUrl");
		basePath = property.readPropertyData("invalid.fetchUserList.basepath");

		request = RestAssured.given();
		response = request.baseUri(baseURL).basePath(basePath).log().all().get();

	}

	public void verifyErrorResponseStatusCode() {

		int actualResponseStatusCode = response.getStatusCode();
		int expectedResponseStatusCode = 404;
		Assert.assertEquals(actualResponseStatusCode, expectedResponseStatusCode);

		System.out.println("===============================================================");
		System.out.println("actualResponseStatusCode : " + actualResponseStatusCode);
		System.out.println("expectedResponseStatusCode : " + expectedResponseStatusCode);
		System.out.println("===============================================================");

	}

	public void verifyEmptyResponseBody() throws ParseException {

		String actualResponseBody = response.getBody().asString();
		String expectedResponseBody = "{}";

		System.out.println("actualResponseBody : " + actualResponseBody);
		System.out.println("expectedResponseBody : " + expectedResponseBody);

		Assert.assertEquals(actualResponseBody, expectedResponseBody);

		System.out.println("===============================================================");
		System.out.println("User is unable to fetch the list of users due to invalid endpoint");
		System.out.println("===============================================================");

	}

}
