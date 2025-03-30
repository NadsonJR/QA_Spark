package org.desafio.logic;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.desafio.utils.Utilities;

public class APILogic {
    private Response response;
    private Utilities utilities;

    public APILogic() {
        utilities = new Utilities();
    }
    @Step("Get users")
    public Response getUsers() {
        response = RestAssured.given()
                .when()
                .get("https://reqres.in/api/users");
        utilities.addResponseToDocument(response);
        return response;
    }
    @Step("Get user by id {id}")
    public Response getUserById(int id) {
        response = RestAssured.given()
                .when()
                .get("https://reqres.in/api/users/" + id);
        utilities.addResponseToDocument(response);
        return response;
    }
    @Step("Create user with email {email} and password {password}")
    public Response createUser(String email, String password) {
        response = RestAssured.given()
                .body("{\n" +
                        "    \"email\": \"" + email + "\",\n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users");
        utilities.addResponseToDocument(response);
        return response;
    }

    @Step("Update user with id {id} and name {name} and job {job}")
    public Response updateUser(int id, String name, String job) {
        response = RestAssured.given()
                .body("{\n" +
                        "    \"name\": \"" + name + "\",\n" +
                        "    \"job\": \"" + job + "\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/" + id);
        utilities.addResponseToDocument(response);
        return response;
    }
    @Step("Delete user with id {id}")
    public Response deleteUser(int id) {
        response = RestAssured.given()
                .when()
                .delete("https://reqres.in/api/users/" + id);
        utilities.addResponseToDocument(response);
        return response;
    }
}
