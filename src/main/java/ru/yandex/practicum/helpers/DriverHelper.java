package ru.yandex.practicum.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

public class DriverHelper {
    protected WebDriver driver;

    public WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        System.out.println("browserProperty = " + browserProperty);
        BrowserType browserType = BrowserType.valueOf(browserProperty);
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\Denis\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

                driver = new ChromeDriver(options);
                break;
            default:
                throw new RemoteException("Browser undefined");
        }
        return driver;
    }
}
