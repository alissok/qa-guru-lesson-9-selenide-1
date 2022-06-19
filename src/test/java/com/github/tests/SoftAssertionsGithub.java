package com.github.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SoftAssertionsGithub {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.holdBrowserOpen = true;
        baseUrl = "https://github.com/selenide/selenide";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void shutDown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Проверка примера кода для JUnit5 в SoftAssertions")
    void checkJunitExamples() {
        String linkText = "Soft assertions";
        SelenideElement junitLocator = $("#user-content-3-using-junit5-extend-test-class");

        step("Открыть репозиторий Selenide на GitHub", () ->
                open(baseUrl)
        );
        step("Перейти в Wiki", () ->
                $("#wiki-tab").click()
        );
        step("Перейти в раздел " + linkText, () ->
                $(linkText(linkText)).click()
        );
        step("Проверить наличие примера для JUnit5", () ->
                junitLocator.shouldBe(visible)
        );
    }
}
