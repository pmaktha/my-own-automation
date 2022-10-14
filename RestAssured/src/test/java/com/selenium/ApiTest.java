package com.selenium;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    @Test(priority = 0,invocationCount = 2)
    public void getTest() {
        //Response response=RestAssured.get("https://reqres.in/api/users/2");
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println("Response: " + response.asPrettyString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("Header: " + response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test(priority = 1)
    public void postTest() {
        //Map<String,Object> map = new HashMap<String,Object>();
        JSONObject request = new JSONObject();
        request.put("Test", 123);
        request.put("Develop", 15.98);
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.given().contentType(ContentType.JSON).
                body(request.toJSONString())
                .when().post("/api/users/")
                .then().log().all().assertThat().statusCode(201);

    }

    @Test(priority = 2)
    public void putTest() {
        RestAssured.baseURI = "https://reqres.in";
        /*Map<String,String> map = new HashMap<String,String>();
        map.put("name","Prashu");
        map.put("job","Tester");*/
        JSONObject request=new JSONObject();
        request.put("name","Prashu");
        request.put("job","Tester");
        RestAssured.given().body(request.toJSONString())
                .when().put("/api/users/2")
                .then()
                .log().all().assertThat().statusCode(200);
    }

    @Test(priority = 3)
    public void patchTest(){
        RestAssured.baseURI="https://reqres.in";
        JSONObject request= new JSONObject();
        request.put("name","Prashanth");
        request.put("job","Automation Tester");
        RestAssured.given().contentType(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("/api/users/2")
                .then()
                .log().all().assertThat().statusCode(200);
    }
    @Test(priority = 4)
    public void deleteTest(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.when()
                .delete("/api/users/2")
                .then()
                .log().all().assertThat().statusCode(204);
    }
}

