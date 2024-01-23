package com.gorest.productandstoreinfo;

import com.gorest.testbase.TestBase;
import com.gorest.userandpostinfo.UserSteps;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)

public class UsersCrudTestWithSteps extends TestBase {
    static String name = TestUtils.getRandomValue() + "ABC";
    static String email = TestUtils.getRandomValue() + "abc@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;

    @Steps
    UserSteps steps;
    @Title ("This will create a new user")
    @Test
    public void test001(){
        ValidatableResponse response = steps.createUser(name,email,gender,status).statusCode(201);
      userId=   response.extract().jsonPath().getInt("id");
        System.out.println(userId);

    }
    @Title("Verify the user added to the application")
    @Test
    public void test002(){
        String userList = steps.getUserInfoByName(userId);
        Assert.assertEquals(name,userList);

    }
    @Title("Verify and update user information")
    @Test
    public void test003(){
         steps.partiallyUpdateUserInformation(userId,name,email,gender,status).statusCode(200);


        String userList = steps.getUserInfoByName(userId);
        Assert.assertEquals(name,userList);
    }
    @Title("This will delete userId and verify if user is deleted")

    @Test
    public void test004(){
        steps.deleteUser(userId).statusCode(204);
        steps.getUserById(userId).statusCode(404);
    }

}
