package prom_ua_page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class ProductPage {

    @Step("ProductPage: add product to basket")
    public BasketPage addProductToBasket() {
        $x("//button[@data-qaid='buy-button']").click();
        switchTo().frame($x("//iframe[@id='shoppingCartIframe']"));

        return new BasketPage();
    }

    public String getProductPageName() {
        return $x("//h1[@data-qaid='product_name']").text();
    }
}
