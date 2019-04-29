package com.api.naresh;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegressionTestSuite extends RestSerivces{
    @Test
    public void userRegisterTest(){
        Response response=null;
        JsonObject payload=new JsonObject();
        payload.addProperty("username","nkp7");
        payload.addProperty("passwordConfirmation","nkp7");
        payload.addProperty("password","nkp7");

        response=postService(payload,"http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089/register");
        response.prettyPrint();
      int  id=response.then().extract().path("id");
      String msg1=response.then().extract().path("message");
        System.out.println("myid= "+id);
        //hamcest
        assertThat(msg1,is(equalToIgnoringCase("successful")));
        assertThat(response.getStatusCode(),is(equalTo(200)));
        //restassured
        response.then()
                .body(containsString(msg1))
                .statusCode(200);
                //.body(contains(id))
               // .statusCode(200);
        //rest assured
        response.then()
                .body("message",is(equalTo("successful")))
                .statusCode(200);


               response=getService("http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089/register");
               response.prettyPrint();
               response.then().statusCode(200);

        response=getByIdService("http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089/register/",id);
        response.then().statusCode(200);

        response=deleteByIdSerive("http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089/register/",id);
        String msg2=response.then().extract().path("message");
        System.out.println(msg2);
        //hamcrst
        assertThat(msg2,is(containsString("User has been removed")));
        assertThat(response.getStatusCode(),is(equalTo(200)));
        //hamcrest
        assertThat(msg2,equalToIgnoringCase("User has been removed "));
        assertThat(response.getStatusCode(),is(equalTo(200)));

        //rest assured
        response.then()
                .body(containsString(msg2))
                .statusCode(200);
        //rest assured
        response.then().body("message",
                is(equalTo("User has been removed "))).statusCode(200);



    }
}
