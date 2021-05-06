package prom_ua_page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {

    @Step("Home page: open home page")
    public HomePage openHomePage() {
        open("https://prom.ua/ua/");
        return this;
    }

    @Step("Home page: search for product {productName}")
    public ProductListPage searchFor(String productName) {
        $x("//input[contains(@class, 'searchForm')]").val(productName).pressEnter();
        return new ProductListPage();
    }

    @Step("Home page: open sign in page")
    public SignInPage openLoginPage() {
        $x("//a[@data-qaid='sign-in']").click();
        return new SignInPage();
    }
}
