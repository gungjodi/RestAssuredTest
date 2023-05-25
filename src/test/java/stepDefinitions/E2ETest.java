package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class E2ETest {
    RequestSpecification requestSpecification;
    JSONObject newUser = new JSONObject();
    Response response;
    JsonPath jsonPath;
    @Given("API HOST {string}")
    public void apiHOST(String apiHost) {
        requestSpecification = given().baseUri(apiHost).contentType(ContentType.JSON);
        if (apiHost.contains("https://gorest.co.in")) {
            requestSpecification.headers("Authorization", "Bearer 308af91b0b57fcb52adcad37b53146b24eea6ff01ce13f8d01a985a709573124");
        }
    }

    @When("I input email {string}")
    public void iInputEmail(String arg0) {
        newUser.put("email", arg0);
    }

    @And("I input password {string}")
    public void iInputPassword(String arg0) {
        newUser.put("password", arg0);
    }

    @Then("I hit register")
    public void iHitRegister() {
        response = requestSpecification.body(newUser.toString()).log().all()
                .when().post("/api/register");
    }

    @Then("response code should be {string}")
    public void responseCodeShouldBe(String arg0) {
        response.then().statusCode(Integer.parseInt(arg0)).log().all();
    }

    @And("response body contains key {string}")
    public void responseBodyContains(String arg0) {
        jsonPath = response.jsonPath();
        response.then().body(arg0, Matchers.notNullValue());
    }

    @Then("I hit login")
    public void iHitLogin() {
        response = requestSpecification.body(newUser.toString()).log().all()
                .when().post("/api/login");
    }

    @Then("I input user email {string}")
    public void iInputUserEmail(String arg0) {
        String email = arg0.replace("<random>", "test"+Math.random() * 1000);
        newUser.put("email", email);
    }

    @Then("I input user name {string}")
    public void iInputUserName(String arg0) {
        newUser.put("name", arg0);
    }

    @Then("I input user gender {string}")
    public void iInputUserGender(String arg0) {
        newUser.put("gender", arg0);
    }

    @Then("I input user status {string}")
    public void iInputUserStatus(String arg0) {
        newUser.put("status", arg0);
    }

    @Then("I hit create user")
    public void iHitCreateUser() {
        response = requestSpecification.body(newUser.toString()).log().all()
                .when().post("/public/v2/users");
    }

    @When("I hit get user detail with newly created user id")
    public void iHitGetUserDetailWithNewlyCreatedUserId() {
        response = requestSpecification.body("{}").when().get("/public/v2/users/"+jsonPath.get("id"));
    }

    @And("response body {string} equals {string}")
    public void responseBodyEquals(String arg0, String arg1) {
        response.then().body(arg0, Matchers.equalTo(arg1)).log().all();
    }
}
