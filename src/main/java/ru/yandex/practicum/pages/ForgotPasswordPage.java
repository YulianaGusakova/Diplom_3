package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final By forgotPasswordEnterButton = By.cssSelector(".Auth_link__1fOlj");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по ссылочной кнопке Войти на странице восстановления пароля")
    public void clickOnEnterButtonAtForgotPasswordPage() {
        driver.findElement(forgotPasswordEnterButton).click();
    }

}
