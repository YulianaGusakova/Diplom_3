package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import ru.yandex.practicum.user.User;
import ru.yandex.practicum.util.ApiEndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static ru.yandex.practicum.util.Constants.BASE_URL;
import static java.net.HttpURLConnection.*;

public class UserApiSteps {

    @Before
    public void startUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .build();

        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig
                        .logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails());

    }

    @Step("Регистрация пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL + ApiEndPoints.USER_CREATE_POST)
                .then();
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL + ApiEndPoints.USER_LOGIN_POST)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .headers("Authorization", user.getAccessToken())
                .when()
                .delete(BASE_URL + ApiEndPoints.USER_DELETE_DEL)
                .then();
    }

    @Step("Проверка успешной авторизации пользователя")
    public void successfulUserLoginCheck(User user) {
        loginUser(user)
                .statusCode(HTTP_OK)
                .body("success", is(true));
    }

}
