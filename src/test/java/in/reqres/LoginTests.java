package in.reqres;

import data.DataProviderTest;
import helpers.Assert;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static specification.Spec.*;

/**
 * Класс LoginTests тестирует POST запросы авторизации пользователей
 *
 * @author Ермаченкова Анна
 * @version 1.0
 */
@Epic("Авторизация пользователей методом POST")
public class LoginTests {
    LoginBodyModel loginBody = new LoginBodyModel();

    /**
     * Метод проверяет положительный сценарий авторизации пользователя
     *
     * @param email    эл.почта
     * @param password пароль
     */
    @Test(dataProvider = "userLoginPositiveData", dataProviderClass = DataProviderTest.class)
    @Story("Проверка положительного сценария авторизации пользователя")
    public void loginUserPositiveTest(String email, String password) {
        loginBody.setEmail(email);
        loginBody.setPassword(password);

        LoginResponseModel response = given(requestSpec())
                .body(loginBody)
                .when()
                .post("/api/login")
                .then()
                .spec(responseSpec200())
                .extract().body().as(LoginResponseModel.class);

        Assert.assertNotNull(response.getToken(), "Проверяем, что присутствует token");
    }

    /**
     * Метод проверяет негативный сценарий авторизации пользователя
     *
     * @param email    эл.почта
     * @param password пароль
     * @param message  сообщение об ошибке
     */
    @Test(dataProvider = "userLoginNegativeData", dataProviderClass = DataProviderTest.class)
    @Story("Проверка негативного сценария авторизации пользователя")
    public void loginUserNegativeTest(String email, String password, String message) {
        loginBody.setEmail(email);
        loginBody.setPassword(password);

        Response response = given(requestSpec())
                .body(loginBody)
                .when()
                .post("/api/login")
                .then()
                .spec(responseSpec400())
                .extract().response();

        Assert.assertEquals(response.as(LoginResponseModel.class).getError(), message,
                "Ожидали ошибку с сообщением " + message +
                        ", а вышло " + response.as(LoginResponseModel.class).getError());

        Assert.assertEquals(response.getStatusCode(), 400,
                "Ожидали статус код 400, а вышло " + response.getStatusCode());
    }
}
