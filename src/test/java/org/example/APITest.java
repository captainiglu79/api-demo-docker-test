/*
 * Copyright (c) 2025, by znt Zentren fuer Neue Technologien GmbH.
 * All Rights Reserved.
 * znt Zentren fuer Neue Technologien GmbH ("ZNT")
 * Lena-Christ-Str. 2
 * 82031 Gruenwald
 * GERMANY
 * +49 (89) 6418080
 * This software is furnished under a license and may only be used,
 * copied, modified or distributed in accordance with the terms of such
 * license or as otherwise permitted by ZNT or an authorized third party.
 * ZNT's warranty for the software and liability in connection with this
 * software are limited in accordance with the applicable license terms.
 */

package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class APITest
{

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Sample API
    }

    @Test
    public void testGetUser() {
        Response response = RestAssured
            .given()
            .when()
            .get("/users/1")
            .then()
            .statusCode(200)
            .extract().response();

        String username = response.jsonPath().getString("username");
        Assert.assertEquals(username, "Bret");
    }


    @Test
    public void testCreatePost() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .extract().response();

        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }


    @Test
    public void testUpdatePost() {
        String requestBody = "{ \"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }";

        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .put("/posts/1")
            .then()
            .statusCode(200)
            .extract().response();

        Assert.assertEquals(response.jsonPath().getString("title"), "updated title");
    }

    @Test
    public void testDeletePost() {
        RestAssured
            .given()
            .when()
            .delete("/posts/1")
            .then()
            .statusCode(200);
    }

}
