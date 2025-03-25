package org.desafio.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.desafio.logic.APILogic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class APISteps {

    private APILogic apiLogic;
    private Response response;
   public APISteps() {
        apiLogic = new APILogic();
    }

    @Given("I get users")
    public void i_get_users() {
        response = apiLogic.getUsers();
    }
    @Then("I should see the status code {int}")
    public void i_should_see_the_status_code(Integer statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Given("I get user by id {int}")
    public void i_get_user_by_id(Integer int1) {
        response = apiLogic.getUserById(int1);
    }
    @Given("I create a user")
    public void i_create_a_user() {
        response = apiLogic.createUser("eve.holt@reqres.in", "pistol");
    }
    @Given("I update a user")
    public void i_update_a_user() {
        response = apiLogic.updateUser(4, "morpheus", "zion resident");
    }

    @Given("I delete a user")
    public void i_delete_a_user() {
        response = apiLogic.deleteUser(4);
    }

}
