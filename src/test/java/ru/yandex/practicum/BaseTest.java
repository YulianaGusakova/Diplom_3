package ru.yandex.practicum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.helpers.DriverHelper;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void startUp() throws IOException {
        DriverHelper driverHelper = new DriverHelper();
        driver = driverHelper.initDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
