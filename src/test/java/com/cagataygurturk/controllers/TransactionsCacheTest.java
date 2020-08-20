package com.cagataygurturk.controllers;

import com.timplant.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=12347")
public class TransactionsCacheTest {

    protected static String BASE_STRING = "http://localhost:12347/transactionservice";

    @Test
    public void getNotFoundTransaction() throws Exception {
        get(BASE_STRING + "/transactions/112313")
                .then()
                .statusCode(404);
    }

    @Test
    public void saveNotFoundTransaction() throws Exception {
        given()
                .contentType("application/json")
                .body("{\"amount\": 20.8,\"type\": \"cars\" }")
                .expect()
                .statusCode(201)
                .when()
                .post(BASE_STRING + "/transactions");
    }


    @Test
    public void getNotFoundTransaction2() throws Exception {
        get(BASE_STRING + "/transactions/112313")
                .then()
                .statusCode(404);
    }


    @Test
    public void getNotFoundTransaction3() throws Exception {
        get(BASE_STRING + "/transactions/112313")
                .then()
                .statusCode(404);
    }


    @Test
    public void getNotFoundTransaction4() throws Exception {
        get(BASE_STRING + "/transactions/112313")
                .then()
                .statusCode(404);
    }
}
