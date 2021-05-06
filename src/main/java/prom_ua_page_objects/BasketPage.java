package prom_ua_page_objects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;

public class BasketPage {

    public Integer getProductPrice(int productNumber) {
        return parseInt($x("//div[@data-qaid='product_price']")
                .text()
                .replaceAll("[^0-9]", ""));
    }

    public boolean isBasketEmpty() {
        return !$x("//a[@data-qaid='product-name']").isDisplayed();
    }
}
