package com.api.naresh;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.jar.JarOutputStream;

import static io.restassured.RestAssured.given;

public class RestSerivces {
    public Response postService(JsonObject payload, String uri){
         return given().contentType(ContentType.JSON).body(payload).when().post(uri);

    }
    public Response getService(String uri){
       return given().contentType("application/json").when().get(uri);

    }
    public Response getByIdService(String uri,int id){
        return given().contentType("application/json").when().get(uri+id);
    }
    public Response putByIdService(String uri,int id){
        return given().contentType("application/json").when().put(uri+id);

    }
    public Response deleteByIdSerive(String uri,int id){
        return given().contentType("application/json").when().delete(uri+id);

    }

}
