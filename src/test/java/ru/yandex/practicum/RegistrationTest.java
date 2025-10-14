package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegistrationPage;
import ru.yandex.practicum.pages.SignInPage;
import ru.yandex.practicum.steps.UserApiSteps;
import ru.yandex.practicum.user.DataGenerator;
import ru.yandex.practicum.user.User;

public class RegistrationTest extends BaseTest {
    private MainPage mainPage;
    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private UserApiSteps userApiSteps;
    private String accessToken;

    private final User user = DataGenerator.randomUser();
    private final User userInvalidPassword = DataGenerator.userInvalidPassword();

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
        registrationPage = new RegistrationPage(driver);
        userApiSteps = new UserApiSteps();
        accessToken = userApiSteps.createUser(user).extract().path("accessToken");

    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Проверяет успешную регистрацию пользователя")
    public void signUpPositiveTest() {

        mainPage.openMainPage();
        mainPage.clickOnPersonalAccountButton();
        signInPage.clickOnSingUpLinkButton();
        registrationPage.fillInRegistrationDataFields(user);
        userApiSteps.successfulUserLoginCheck(user);
    }

    @Test
    @DisplayName("Ошибка при некорректном вводе пароля")
    @Description("Проверяет, что при некорректном вводе пароля (не более 5 символов), получаем ошибку")
    public void signUpWithInvalidPasswordTest() {
        mainPage.openMainPage();
        mainPage.clickOnPersonalAccountButton();
        signInPage.clickOnSingUpLinkButton();
        registrationPage.fillInRegistrationDataFields(userInvalidPassword);
        registrationPage.getErrorMessageText();
    }

    @After
    public void clearTestData() {
        if (accessToken != null && user.getAccessToken() != null) {
            try {
                userApiSteps.deleteUser(user);
            } catch (Exception e) {
                // Логирование ошибки
                System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
            }
        }
    }
}
