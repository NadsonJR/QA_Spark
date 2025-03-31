package org.desafio.steps;

import com.github.javafaker.Faker;
import com.itextpdf.layout.Document;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.desafio.config.CucumberHooks;
import org.desafio.logic.APILogic;

public class APISteps {
    private APILogic apiLogic;
    private Faker faker;
    private Response response;
    public APISteps() {
        apiLogic = new APILogic();
        faker = new Faker();
        Document documentEvidence = CucumberHooks.getDocumentEvidence();
    }
    @Given("I get users")
    public void i_get_users() {
        response = apiLogic.getUsers();
    }
    @Then("I should see the status code {int}")
    public void i_should_see_the_status_code(Integer statusCode) {
        apiLogic.validateStatusCode(statusCode, response);
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
        response = apiLogic.updateUser(4, faker.name().fullName(), faker.job().title());
    }
    @Given("I delete a user")
    public void i_delete_a_user() {
        response = apiLogic.deleteUser(4);
    }
    @Given("I update last created user")
    public void i_update_last_created_user() {
        response = apiLogic.updateLastCreatedUser(faker.name().fullName(), faker.job().title());
    }
    @Given("I delete last created user")
    public void i_delete_last_created_user() {
        response = apiLogic.deleteLastCreatedUser();
    }

}
