package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.user.User;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By registratiomFormNameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By registratiomFormEmailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By registrationFormPasswordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registrationFormRegistrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By registrationFormEnterButton = By.cssSelector(".Auth_link__1fOlj");
    private final By errorMessageText = By.xpath("//div[@class='input__container']//p[text() = 'Некорректный пароль']");
    private final String expectedHeader = "Некорректный пароль";

    @Step("Заполнение поля Имя в форме регистрации")
    public void fillInNameField(String name) {
        driver.findElement(registratiomFormNameField).clear();
        driver.findElement(registratiomFormNameField).sendKeys(name);
    }

    @Step("Заполнение поля Email в форме регистрации")
    public void fillInEmailField(String email) {
        driver.findElement(registratiomFormEmailField).clear();
        driver.findElement(registratiomFormEmailField).sendKeys(email);
    }
    @Step("Заполнение поля Пароль в форме регистрации")
    public void fillInPasswordField(String password) {
        driver.findElement(registrationFormPasswordField).clear();
        driver.findElement(registrationFormPasswordField).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registrationFormRegistrationButton).click();
    }

    @Step("Заполнение полей формы Регистрации")
    public void fillInRegistrationDataFields(User user) {
fillInNameField(user.getName());
fillInEmailField(user.getEmail());
fillInPasswordField(user.getPassword());
clickRegisterButton();
    }

    @Step("Проверка текста сообщения об ошибке при некорректном вводе пароля в форме Регистрации")
    public void getErrorMessageText() {
        String actualText = driver.findElement(errorMessageText).getText();
        Assert.assertTrue("Заголовок содержит ожидаемый текст", actualText.contains(expectedHeader));
    }

    @Step("Нажатие кнопки Войти на странице регистрации")
    public void clickOnEnterButtonAtRegistrationFormPage() {
        driver.findElement(registrationFormEnterButton).click();
    }
}
