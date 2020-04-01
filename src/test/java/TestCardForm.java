import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestCardForm {

    @Test
    public void registrationForm() {
        LocalDate today = LocalDate.now();
        LocalDate dateToBe = today.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateToUse = dateToBe.format(formatter);
        open("http://localhost:7777");
        $("[data-test-id=city] input").setValue("Биробиджан");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id=date] input").setValue(dateToUse);
        $("[data-test-id=name] input").setValue("Петров Петр");
        $("[data-test-id=phone] input").setValue("+70001112233");
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    public void autoRegistrationCard() {
        LocalDate today = LocalDate.now();
        LocalDate dateToBe = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateToUse2 = dateToBe.format(formatter);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Би");
        $$(".menu-item__control").find(text("Биробиджан")).click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("button").click();
        $("[data-test-id=date] input").setValue(dateToUse2);
        $("[data-test-id=name] input").setValue("Петров Петр");
        $("[data-test-id=phone] input").setValue("+70001112233");
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

}