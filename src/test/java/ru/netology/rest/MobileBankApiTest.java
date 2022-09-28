package ru.netology.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200);
    }

    class MobileBankApiTestV2 {
        @Test
        void shouldReturnDemoAccounts() {
            // Given - When - Then
            // Предусловия
            given()
                    .baseUri("http://localhost:9999/api/v1")
                    // Выполняемые действия
                    .when()
                    .get("/demo/accounts")
                    // Проверки
                    .then()
                    .statusCode(200)
                    // .header("Content-Type", "application/json; charset=UTF-8")
                    // специализированные проверки - лучше
                    .contentType(ContentType.JSON)
            ;
        }

        class MobileBankApiTestV3 {
            @Test
            void shouldReturnDemoAccounts() {
                // Given - When - Then
                // Предусловия
                given()
                        .baseUri("http://localhost:9999/api/v1")
                        // Выполняемые действия
                        .when()
                        .get("/demo/accounts")
                        // Проверки
                        .then()
                        .statusCode(200)
                        // специализированные проверки - лучше
                        .contentType(ContentType.JSON)
                        .body("", hasSize(3))
                        .body("[0].currency", equalTo("RUB"))
                        .body("[0].balance", greaterThanOrEqualTo(0))
                ;
            }

            class MobileBankApiTestV4 {
                @Test
                void shouldReturnDemoAccounts() {
                    // Given - When - Then
                    // Предусловия
                    given()
                            .baseUri("http://localhost:9999/api/v1")
                            // Выполняемые действия
                            .when()
                            .get("/demo/accounts")
                            // Проверки
                            .then()
                            .statusCode(200)
                            .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                    ;
                }

                class MobileBankApiTestV5 {
                    @Test
                    void shouldReturnDemoAccounts() {
                        // Given - When - Then
                        // Предусловия
                        given()
                                .baseUri("http://localhost:9999/api/v1")
                                // Выполняемые действия
                                .when()
                                .get("/demo/accounts")
                                // Проверки
                                .then()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .body("every{ it.balance >= 0 }", is(true))
                        ;
                    }

                    class MobileBankApiTestV6 {
                        private RequestSpecification requestSpec = new RequestSpecBuilder()
                                .setBaseUri("http://localhost")
                                .setBasePath("/api/v1")
                                .setPort(9999)
                                .setAccept(ContentType.JSON)
                                .setContentType(ContentType.JSON)
                                .log(LogDetail.ALL)
                                .build();

                        @Test
                        void shouldReturnDemoAccounts() {
                            // Given - When - Then
                            // Предусловия
                            given()
                                    .spec(requestSpec) // со спецификацией проще (особенно когда много тестов)
                                    // Выполняемые действия
                                    .when()
                                    .get("/demo/accounts")
                                    // Проверки
                                    .then()
                                    .statusCode(200);
                        }
                    }
                }
            }
        }
    }
}