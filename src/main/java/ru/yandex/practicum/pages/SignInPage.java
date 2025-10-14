package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.user.User;

public class SignInPage {
    private final WebDriver driver;

    private final By signUpLinkButton = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    private final By signInFormEmailField = By.xpath(".//label[contains(text(),'Email')]/../input");
    private final By signInFormPasswordField = By.xpath(".//label[contains(text(),'Пароль')]/../input");
    private final By signInFormEnterButton = By.xpath("//button[text()='Войти']");
    private final By forgotPasswordLinkButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по ссылочной кнопке Зарегистрироваться")
    public void clickOnSingUpLinkButton() {
        driver.findElement(signUpLinkButton).click();
    }

    @Step("Очистка содержимого и заполнение полей формы Входа в аккаунт: email, пароль. Клик по кнопке Войти")
    public void fillInUserDataAndEnterAccount(User user) {
        driver.findElement(signInFormEmailField).clear();
        driver.findElement(signInFormEmailField).sendKeys(user.getEmail());
        driver.findElement(signInFormPasswordField).clear();
        driver.findElement(signInFormPasswordField).sendKeys(user.getPassword());
        driver.findElement(signInFormEnterButton).click();
    }

    @Step("Клик по ссылочной кнопке Восстановить пароль")
    public void clickOnForgotPasswordLinkButton() {
        driver.findElement(forgotPasswordLinkButton).click();
    }
}
