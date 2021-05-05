package page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

//This page object checks project run
public class GoogleHomePage {

    @Step("Home page: open home page")
    public GoogleHomePage openHomePage() {
        open("https://www.google.com/");
        return this;
    }

    @Step("Home page: search for request {request}")
    public GoogleResultPage searchFor(String request) {
        $x("//input[@class='gLFyf gsfi']").val(request).pressEnter();
        return new GoogleResultPage();
    }
}
