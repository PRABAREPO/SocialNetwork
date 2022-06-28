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

public class AddCommentsImplementation {

	RequestSpecification request;
	Response response;
	String requestBody;
	String baseURL;
	String basePath;

	readPropertyFile property = new readPropertyFile();

	public void takeRequestBody() throws IOException, ParseException {

		FileReader reader = new FileReader("./testdata/requests/addComments.json");
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonReq = (JSONArray) jsonParser.parse(reader);

		requestBody = jsonReq.toString();

		System.out.println("===============================================================");
	}

	public void sendValidRequestToServer(String validBasePath) throws IOException {

		baseURL = property.readPropertyData("baseUrl");
		basePath = validBasePath;

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
		System.out.println("===============================================================" + "\n");

	}

	public void verifyResponseBody() throws ParseException {

		String responseBody = response.getBody().asString();

		System.out.println("Response Body is:" + responseBody);
		System.out.println("===============================================================");

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

		JSONObject jsonObject1 = (JSONObject) jsonObject.get("0");

		String actualFirstCommentBody = (String) jsonObject1.get("body");
		String expectedFirstCommentBody = "This is First Comment";

		System.out.println("actualFirstCommentBody : " + actualFirstCommentBody);
		System.out.println("expectedFirstCommentBody : " + expectedFirstCommentBody);

		Assert.assertEquals(actualFirstCommentBody, expectedFirstCommentBody);

		JSONObject jsonObject2 = (JSONObject) jsonObject.get("1");

		String actualSecondCommentBody = (String) jsonObject2.get("body");
		String expectedSecondCommentBody = "This is Second Comment";

		System.out.println("actualSecondCommentBody : " + actualSecondCommentBody);
		System.out.println("expectedSecondCommentBody : " + expectedSecondCommentBody);

		Assert.assertEquals(actualSecondCommentBody, expectedSecondCommentBody);

		System.out.println("===============================================================");
		System.out.println("User has added 2 Comments successfully");
		System.out.println("===============================================================" + "\n");

	}

	public void sendInvalidRequestToServer(String invalidBasePath) throws IOException {

		baseURL = property.readPropertyData("baseUrl");
		basePath = invalidBasePath;

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
		System.out.println("User is unable to add comments due to invalid endpoint");
		System.out.println("===============================================================");

	}

}
