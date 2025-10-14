package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.pages.MainPage;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class MenuTabTest extends BaseTest {
    private MainPage mainPage;
    private String fromMenuItem;
    private String toMenuItem;

    public MenuTabTest(String fromMenuItem, String toMenuItem) {
        this.fromMenuItem = fromMenuItem;
        this.toMenuItem = toMenuItem;
    }

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Parameterized.Parameters(name = "Проверка переключения: начальная секция {0} → целевая секция {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Булки", "Соусы"},
                {"Булки", "Начинки"},
                {"Соусы", "Булки"},
                {"Соусы", "Начинки"},
                {"Начинки", "Булки"},
                {"Начинки", "Соусы"}
        });
    }

    @Test
    @DisplayName("Переход к разделам Булки, Соусы, Начинки")
    @Description("Проверяет, что при нажатии на название раздела он активируется")
    public void menuTabSwitchingTest() {
        String currentActiveTab = mainPage.getActiveTab();
        if (!currentActiveTab.equals(fromMenuItem)) {
            mainPage.clickItemMenu(fromMenuItem);
        }
        mainPage.clickItemMenu(toMenuItem);
        Assert.assertTrue("Целевая секция не активна", mainPage.isTabActive(toMenuItem));
    }
}

