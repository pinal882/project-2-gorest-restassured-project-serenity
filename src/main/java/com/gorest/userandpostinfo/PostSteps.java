package com.gorest.userandpostinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.PostsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class PostSteps {
    @Step
    public ValidatableResponse createPost(int user_Id, String title, String body ){
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setUser_id(user_Id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .body(postsPojo)
                .when()
                .post(EndPoints.GET_ALL_POSTS)
                .then().log().all();
    }
    @Step
    public String  getPostByIdAndVerifyTitle(int postId){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("postId",postId)
                .when()
                .get(EndPoints.GET_SINGLE_POST_BY_Id)
                .then().log().all().statusCode(200)
                .extract().path("title");
    }
    @Step
    public ValidatableResponse updatePost(int postId, int user_Id,String title,String body){
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setUser_id(user_Id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("postId", postId)
                .body(postsPojo)
                .when()
                .put(EndPoints.UPDATE_POST_BY_Id)
                .then().log().all();
    }
    @Step
    public ValidatableResponse deletePost(int postId){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("postId",postId)
                .when()
                .delete(EndPoints.DELETE_POST_BY_ID)
                .then().log().all();
    }
    @Step
    public ValidatableResponse verifyingPostDeletedById(int postId){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("postId",postId)
                .when()
                .get(EndPoints.GET_SINGLE_POST_BY_Id)
                .then().log().all();
    }
}
