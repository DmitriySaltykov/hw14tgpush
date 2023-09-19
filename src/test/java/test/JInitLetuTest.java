package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class JInitLetuTest {
        public void testYtSearch()
            {
                SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        }

    @Tags({
            @Tag("web"),
            @Tag("search")
    })
    @DisplayName("Проверка наличия категорий на странице youtoobe")
    @ValueSource(strings = {"Главная" , "Shorts", "Подписки"})
    @ParameterizedTest(name = "Youtube home page should have \"{0}\" categories.")
    void wikipediaHomePageShouldHaveLanguageTest(String testData) {

        open("https://www.youtube.com/");
        $(".style-scope ytd-guide-renderer").shouldHave(text(testData));

    }
    @Tags({
            @Tag("web"),
            @Tag("search")
    })
    @DisplayName("Проверка наличия описания на странице канала")

    @CsvSource(value = {
            "QA GURU, Школа инженеров по автоматизации тестирования",
            "Academeg, Итак ....  Меня зовут Костик, и я имею свой субъективный взгляд на автомобили"
    })
    @ParameterizedTest(name = "Youtube chanel  \"{0}\" should have text \"{1}\" in descreption.")
    void wikipediaShouldHaveTextInArticleTest(String testData, String expectedResult) {

        open("https://www.youtube.com/");
        $("#search-input #search").val(testData).pressEnter();
        $(".style-scope ytd-channel-renderer").click();
        $(".style-scope ytd-channel-tagline-renderer").click();
        $("#description-container").shouldHave(text(expectedResult));

    }
    @Tags({
            @Tag("web"),
            @Tag("search")
    })
    @DisplayName("Проверка наличия табов странице канала")
    static Stream<Arguments> YTJavaProvider() {
        return Stream.of(
                Arguments.of("QA GURU", List.of("Главная", "Видео", "Плейлисты", "Сообщество")),
                Arguments.of("Academeg", List.of("Главная", "Видео", "Трансляции", "Плейлисты", "Сообщество"))
        );
    }
    @MethodSource("YTJavaProvider")
    @ParameterizedTest(name = "Youtube chanel  \\\"{0}\\\" should have link \\\"{1}\\\" on Links descreption.\"")
    void YTJavaProvider(String java, List<String> characteristic) {
        Configuration.pageLoadStrategy = "eager";
        open("https://www.youtube.com/");


        $("#search-input #search").val(java).pressEnter();
        $(".style-scope ytd-channel-renderer").click();

        $("#tabsContainer").shouldHave(text(String.join(" ", characteristic)));
    }





}