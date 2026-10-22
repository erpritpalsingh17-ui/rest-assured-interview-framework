package com.api.automation.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserApi {


    public Response getUser(int userId) {

        
        Response response = RestAssured
                .given()
                .baseUri("https://dummyjson.com")
                 .header("Content-Type", "application/json")
                 .pathParam("id", userId)
                .queryParam("page", 0)
                .queryParam("Limit", 0)
                .when()
                .get("/users/{id}");
            


        return response;
    }

    public Response createUser(String firstName, String lastName, int age) {
        String requestBody = "{"
                + "\"firstName\":\"" + firstName + "\","
                + "\"lastName\":\"" + lastName + "\","
                + "\"age\":" + age
                + "}";

        Response response = RestAssured
                .given()
                .baseUri("https://dummyjson.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/users/add");

        return response;
    }
     public Response updateUser(String firstName, String lastName, int age) {
        String requestBody = "{"
                + "\"firstName\":\"" + firstName + "\","
                + "\"lastName\":\"" + lastName + "\","
                + "\"age\":" + age
                + "}";

        Response response = RestAssured
                .given()
                .baseUri("https://dummyjson.com")
                .pathParam("age", 40)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put("/users/{id}}");

        return response;
    }

    public Response loginUser() {

    String requestBody = "{"
            + "\"username\":\"emilys\","
            + "\"password\":\"emilyspass\""
            + "}";

    Response response = RestAssured
            .given()
            .log().all()
            .baseUri("https://dummyjson.com")
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when()
            .post("/auth/login");

    return response;
}
public Response getCurrentUser(String token) {

    Response response = RestAssured
            .given()
            .baseUri("https://dummyjson.com")
            .header("Authorization", "Bearer " + token)
            .when()
            .get("/auth/me");

    return response;
} 

}
