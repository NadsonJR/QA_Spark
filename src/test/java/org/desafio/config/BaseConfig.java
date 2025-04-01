package org.desafio.config;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

public class BaseConfig {
    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String API_BASE_URL = "https://reqres.in/api/users/";

    public static void setupRestAssured() {
        RestAssured.config = RestAssured.config()
                .sslConfig(SSLConfig.sslConfig()
                        .relaxedHTTPSValidation());
    }
}
