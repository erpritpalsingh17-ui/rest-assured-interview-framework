package com.api.automation.tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.automation.api.UserApi;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTest {

    @DataProvider(name = "userIds")
    public Object[][] getUserIds() {

        return new Object[][] {
            {5},
            {2},
            {3}
        };
    }

    @Test(dataProvider = "userIds")
    public void getUserTest(int userId) {

        UserApi userApi = new UserApi();

        Response response = userApi.getUser(userId);
        int userId1 = response.jsonPath().getInt("id");

       System.out.println("User ID: " + userId1);

        response.prettyPrint();

        //Status Code
        Assert.assertEquals(response.getStatusCode(), 200);
        //Respone header

    String contentType = response.getHeader("Content-Type");
    System.out.println("Content-Type: " + contentType);
    Assert.assertTrue(contentType.contains("application/json"));
    long responseTime = response.getTime();

    Assert.assertTrue(responseTime<2000);
   // Assert.assertEquals(response.jsonPath().getList("users").size(),1);


        
    }


    @Test
public void createUserTest() {

    UserApi userApi = new UserApi();

    Response response = userApi.createUser(
            "Pritpal",
            "Singh",
            37
    );

    response.prettyPrint();

    Assert.assertEquals(response.getStatusCode(), 201);

    Assert.assertEquals(
            response.jsonPath().getString("firstName"),
            "Pritpal"
    );
}

@Test
public void updateUserTest() {

    UserApi userApi = new UserApi();

    Response response = userApi.createUser(
            "Pritpal",
            "Singh",
            40
    );

    response.prettyPrint();

    Assert.assertEquals(response.getStatusCode(), 201);

        Assert.assertEquals(
            response.jsonPath().getString("firstName"),
            "Pritpal"
        );
        Assert.assertEquals(
            response.jsonPath().getString("age"),
            "40"
        );
        Assert.assertEquals(
            response.jsonPath().getString("lastName"),
            "Singh");
}

@Test
public void loginTest() {

    UserApi userApi = new UserApi();

    Response response = userApi.loginUser();

    response.prettyPrint();

    Assert.assertEquals(response.getStatusCode(), 200);

    String token = response.jsonPath().getString("accessToken");

    System.out.println("Token: " + token);

    Assert.assertNotNull(token);
}
@Test
public void authenticatedUserTest() {

    UserApi userApi = new UserApi();

    // Login
    Response loginResponse = userApi.loginUser();

    Assert.assertEquals(loginResponse.getStatusCode(), 200);

    // Extract token
    String token = loginResponse
            .jsonPath()
            .getString("accessToken");

    System.out.println("Token: " + token);

    // Call authenticated API
    Response userResponse = userApi.getCurrentUser(token);

    userResponse.prettyPrint();

    // Validate response
    Assert.assertEquals(userResponse.getStatusCode(), 200);
}

@Test
public void getUserSchemaTest() {

    UserApi userApi = new UserApi();

    Response response = userApi.getUser(1);

    response.prettyPrint();

    Assert.assertEquals(response.getStatusCode(), 200);

    response.then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath(
                    "schemas/user-schema.json"
            ));
}
       
}
