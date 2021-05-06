package prom_ua_page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class ProductListPage {

    @Step("ProductListPage: open product by number {productNumber}")
    public ProductPage openProduct(int productNumber) {
        $x(format("(//a[@data-qaid='product_link'])[%d]", productNumber)).click();

        return new ProductPage();
    }

    public Integer getProductPrice(int productNumber) {
        return parseInt($x(format("(//span[@data-qaid='product_price']//span)[%d]", productNumber))
                .text()
                .replaceAll("[^0-9]", ""));
    }
}
