package stepimplementations;

import java.io.FileReader;
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

public class AddPostsImplementation {

	RequestSpecification request;
	Response response;
	String requestBody;
	String baseURL;
	String basePath;

	readPropertyFile property = new readPropertyFile();

	public void takeRequestBody() throws IOException, ParseException {

		FileReader reader = new FileReader("./testdata/requests/addPosts.json");
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonReq = (JSONArray) jsonParser.parse(reader);

		requestBody = jsonReq.toString();

		System.out.println("===============================================================");
	}

	public void sendValidRequestToServer() throws IOException {

		baseURL = property.readPropertyData("baseUrl");
		basePath = property.readPropertyData("valid.addPosts.basepath");

		request = RestAssured.given();
		response = request.header("Content-Type", "application/json").baseUri(baseURL).basePath(basePath)
				.body(requestBody).log().all().post();

	}

	public void verifySuccessResponseStatusCode() {

		int actualResponseStatusCode = response.getStatusCode();
		int expectedResponseStatusCode = 201;
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
		JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

		JSONObject jsonObject1 = (JSONObject) jsonObject.get("0");

		String actualFirstPostTitle = (String) jsonObject1.get("title");
		String expectedFirstPostTitle = "This is First Post";

		System.out.println("actualFirstPostTitle : " + actualFirstPostTitle);
		System.out.println("expectedFirstPostTitle : " + expectedFirstPostTitle);

		Assert.assertEquals(actualFirstPostTitle, expectedFirstPostTitle);

		JSONObject jsonObject2 = (JSONObject) jsonObject.get("1");

		String actualSecondPostTitle = (String) jsonObject2.get("title");
		String expectedSecondPostTitle = "This is Second Post";

		System.out.println("actualSecondPostTitle : " + actualSecondPostTitle);
		System.out.println("expectedSecondPostTitle : " + expectedSecondPostTitle);

		Assert.assertEquals(actualSecondPostTitle, expectedSecondPostTitle);

		JSONObject jsonObject3 = (JSONObject) jsonObject.get("2");

		String actualLastPostTitle = (String) jsonObject3.get("title");
		String expectedLastPostTitle = "This is Last Post";

		System.out.println("actualLastPostTitle : " + actualLastPostTitle);
		System.out.println("expectedLastPostTitle : " + expectedLastPostTitle);

		Assert.assertEquals(actualLastPostTitle, expectedLastPostTitle);

		System.out.println("===============================================================");
		System.out.println("User has added all the 3 Posts successfully");
		System.out.println("===============================================================" + "\n");

	}

	public void sendInvalidRequestToServer() throws IOException {

		baseURL = property.readPropertyData("baseUrl");
		basePath = property.readPropertyData("invalid.addPosts.basepath");

		request = RestAssured.given();
		response = request.header("Content-Type", "application/json").baseUri(baseURL).basePath(basePath)
				.body(requestBody).log().all().post();

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
		System.out.println("User is unable to add posts due to invalid endpoint");
		System.out.println("===============================================================");

	}

}
