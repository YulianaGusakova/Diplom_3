package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.practicum.util.Constants.BASE_URL;
import static ru.yandex.practicum.util.Constants.EXPLICITY_TIMEOUT;

public class MainPage {
    private final WebDriver driver;

    private final By personalAccountButton = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    private final By enterAccountButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Войти в аккаунт']");
    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By activeTab = By.cssSelector(".tab_tab__1SPyG.tab_tab_type_current__2BEPc");
    private final By bunsMenuItemButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Булки']/parent::div");
    private final By saucesMenuItemButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Соусы']/parent::div");
    private final By fillingsMenuItemButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']/parent::div");
    private final By menuContainer = By.cssSelector("BurgerIngredients_ingredients__menuContainer__Xu3Mo");
    private final By tabText = By.cssSelector("span.text_type_main-default");
    private final String activeTabClassText = "tab_tab_type_current__2BEPc";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get(BASE_URL);
    }

    @Step("Нажатие на кнопку Личный кабинет в хэдере")
    public void clickOnPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на кнопку «Войти в аккаунт» на главной")
    public void clickOnEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    @Step("Проверка появления кнопки Сделать заказ")
    public void checkMakeOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
        Assert.assertTrue(driver.findElement(makeOrderButton).isDisplayed());
    }

    @Step("Клик по переключателям разделов меню Конструктора")
    public void clickItemMenu(String itemMenuName) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
        switch (itemMenuName) {
            case "Булки":
                element = driver.findElement(bunsMenuItemButton);
                break;
            case "Соусы":
                element = driver.findElement(saucesMenuItemButton);
                break;
            case "Начинки":
                element = driver.findElement(fillingsMenuItemButton);
                break;
            default:
                throw new IllegalArgumentException("Неизвестный раздел меню: " + itemMenuName);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();


        wait.until(driver -> isTabActive(itemMenuName));
    }

    @Step("Проверка активности переключателя раздела меню")
    public String getActiveTab() {
        WebElement activeElement = driver.findElement(activeTab);
        return activeElement.getText();
    }

    @Step("Проверка активности конкретного раздела меню")
    public boolean isTabActive(String itemMenuName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
        WebElement activeTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        // Проверяем, что элемент действительно активный (содержит нужный класс)
        String classAttribute = activeTabElement.getAttribute("class");
        if (!classAttribute.contains(activeTabClassText)) {
            return false;
        }

        // Ищем вложенный span с текстом внутри активного таба
        WebElement spanElement = activeTabElement.findElement(tabText);
        String displayedText = spanElement.getText().trim();

        return displayedText.equals(itemMenuName);
    }
}
