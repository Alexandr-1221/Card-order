package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void shouldSubmittingAValidForm(){
        open("http://localhost:9999");
        $("[type=\"text\"]").setValue("Иван Иванов");
        $("[type=\"tel\"]").setValue("+71111111111");
        $("[class=\"checkbox__box\"]").click();
        $("button").click();
        $(".paragraph_theme_alfa-on-white").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmittingInvalidFieldAName(){
        open("http://localhost:9999");
        $("[type=\"text\"]").setValue("Ivan Petrov");
        $("[type=\"tel\"]").setValue("+71111111111");
        $("[class=\"checkbox__box\"]").click();
        $("button").click();
        $("[data-test-id=\"name\"] [class=\"input__sub\"]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmittingInvalidFieldAPhone(){
        open("http://localhost:9999");
        $("[type=\"text\"]").setValue("Иван Иванов");
        $("[type=\"tel\"]").setValue("7114611111111");
        $("[class=\"checkbox__box\"]").click();
        $("button").click();
        $("[data-test-id=\"phone\"] [class=\"input__sub\"]").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmittingEmptyFields(){
        open("http://localhost:9999");
        $("[class=\"checkbox__box\"]").click();
        $("button").click();
        $("[data-test-id=\"name\"] [class=\"input__sub\"]").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmittingEmptyFieldAPhone(){
        open("http://localhost:9999");
        $("[type=\"text\"]").setValue("Иван Иванов");
        $("[class=\"checkbox__box\"]").click();
        $("button").click();
        $("[data-test-id=\"phone\"] [class=\"input__sub\"]").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
