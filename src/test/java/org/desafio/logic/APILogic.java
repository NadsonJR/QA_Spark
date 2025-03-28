package org.desafio.logic;

import com.itextpdf.layout.Document;
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
    public Response getUsers(Document documentEvidence) {
        response = RestAssured.given()
                .when()
                .get("https://reqres.in/api/users");
        utilities.addResponseToDocument(documentEvidence, response);
        return response;
    }
    @Step("Get user by id {id}")
    public Response getUserById(int id, Document documentEvidence) {
        response = RestAssured.given()
                .when()
                .get("https://reqres.in/api/users/" + id);
        utilities.addResponseToDocument(documentEvidence, response);
        return response;
    }
    @Step("Create user with email {email} and password {password}")
    public Response createUser(String email, String password, Document documentEvidence) {
        response = RestAssured.given()
                .body("{\n" +
                        "    \"email\": \"" + email + "\",\n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users");
        utilities.addResponseToDocument(documentEvidence, response);
        return response;
    }

    @Step("Update user with id {id} and name {name} and job {job}")
    public Response updateUser(int id, String name, String job,Document documentEvidence) {
        response = RestAssured.given()
                .body("{\n" +
                        "    \"name\": \"" + name + "\",\n" +
                        "    \"job\": \"" + job + "\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/" + id);
        utilities.addResponseToDocument(documentEvidence, response);
        return response;
    }
    @Step("Delete user with id {id}")
    public Response deleteUser(int id,Document documentEvidence) {
        response = RestAssured.given()
                .when()
                .delete("https://reqres.in/api/users/" + id);
        utilities.addResponseToDocument(documentEvidence, response);
        return response;
    }
}
