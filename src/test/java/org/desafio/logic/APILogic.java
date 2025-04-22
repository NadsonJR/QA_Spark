package org.desafio.logic;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.desafio.config.BaseConfig;
import org.desafio.utils.DocumentConfig;
import org.json.JSONObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class APILogic {
    private Response response;
    private int lastCreatedUserId;
    private DocumentConfig documentConfig;

    public APILogic() {
        documentConfig = new DocumentConfig();
        BaseConfig.setupRestAssured();
    }
    @Step("Get users")
    public Response getBase(String resource) {
        response = RestAssured.given()
                .when()
                .get(BaseConfig.API_BASE_URL+ resource);
        documentConfig.addResponseToDocument(response);
        return response;
    }
    @Step("Get user by id {id}")
    public Response getUserById(String resource ,int id) {
        response = RestAssured.given()
                .when()
                .get(BaseConfig.API_BASE_URL + resource+"/"+id);
        documentConfig.addResponseToDocument(response);
        return response;
    }
    @Step("Create user with email {email} and password {password}")
    public Response createUser(String route ,String email, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);
        response = RestAssured.given()
                .body(requestBody)
                .when()
                .post(BaseConfig.API_BASE_URL+ route);
        documentConfig.addResponseToDocument(response);
        if (response.getStatusCode() == 201) {
            lastCreatedUserId = response.jsonPath().getInt("id");
        }
        return response;
    }
    @Step("Update user with id {id} and name {name} and job {job}")
    public Response updateUser(int id, String name, String job) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("job", job);
        response = RestAssured.given()
                .body(requestBody)
                .when()
                .put(BaseConfig.API_BASE_URL + id);
        documentConfig.addResponseToDocument(response);
        return response;
    }
    @Step("Update last created user with name {name} and job {job}")
    public Response updateLastCreatedUser(String name, String job) {
        return response = updateUser(lastCreatedUserId, name, job);
    }
    @Step("Delete user with id {id}")
    public Response deleteUser(int id) {
        response = RestAssured.given()
                .when()
                .delete(BaseConfig.API_BASE_URL + id);
        documentConfig.addResponseToDocument(response);
        return response;
    }
    @Step("Delete last created user")
    public Response deleteLastCreatedUser() {
        return response = deleteUser(lastCreatedUserId);
    }
    @Step("Validate status code {statusCode}")
    public void validateStatusCode(int statusCode, Response response) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }
}
