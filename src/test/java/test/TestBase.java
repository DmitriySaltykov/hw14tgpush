package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {
//    @BeforeEach
//    void adddListener() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//    }
//    @AfterEach
//    void addAttachments() {
//        Object Attach;
//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//        Attach.browserConsoleLogs();
//        Attach.addVideo();
//
//        closeWebDriver();
//    }
    @BeforeAll
     static void beforeAll() {
        Configuration.baseUrl = "https://www.youtube.com/";
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.remote = System.getProperty("remoteDriverUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC" , true,
                "enableVideo" , true
        ));

        Configuration.browserCapabilities = capabilities;

    }
}
