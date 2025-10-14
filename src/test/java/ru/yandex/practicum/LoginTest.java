package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.pages.ForgotPasswordPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegistrationPage;
import ru.yandex.practicum.pages.SignInPage;
import ru.yandex.practicum.steps.UserApiSteps;
import ru.yandex.practicum.user.DataGenerator;
import ru.yandex.practicum.user.User;

public class LoginTest extends BaseTest {
    private MainPage mainPage;
    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private String accessToken;
    private UserApiSteps userApiSteps;
    private final User user = DataGenerator.randomUser();

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userApiSteps = new UserApiSteps();
        accessToken = userApiSteps.createUser(user).extract().path("accessToken");
        userApiSteps.createUser(user);
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    @Description("Проверяет вход в личный кабинет по кнопке Войти в аккаунт на главной странице")
    public void loginByEnterButtonOnMainPageTest() {
        mainPage.clickOnEnterAccountButton();
        signInPage.fillInUserDataAndEnterAccount(user);
        mainPage.checkMakeOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверяет вход в аккаунт через кнопку «Личный кабинет» на главной странице")
    public void loginByPersonalAccountButtonOnMainPageTest() {
        mainPage.clickOnPersonalAccountButton();
        signInPage.fillInUserDataAndEnterAccount(user);
        mainPage.checkMakeOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяет вход в аккаунт через кнопку «Войти» на странице регистрации")
    public void loginByEnterButtonOnRegistrationPageTest() {
        mainPage.clickOnPersonalAccountButton();
        signInPage.clickOnSingUpLinkButton();
        registrationPage.clickOnEnterButtonAtRegistrationFormPage();
        signInPage.fillInUserDataAndEnterAccount(user);
        mainPage.checkMakeOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяет вход в аккаунт через кнопку «Войти» на странице восстановления пароля")
    public void loginByEnterButtonOnForgotPasswordPageTest() {
        mainPage.clickOnPersonalAccountButton();
        signInPage.clickOnForgotPasswordLinkButton();
        forgotPasswordPage.clickOnEnterButtonAtForgotPasswordPage();
        signInPage.fillInUserDataAndEnterAccount(user);
        mainPage.checkMakeOrderButton();
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
