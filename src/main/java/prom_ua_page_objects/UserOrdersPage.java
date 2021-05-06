package prom_ua_page_objects;

import static com.codeborne.selenide.Selenide.$x;

public class UserOrdersPage {

    public String getPageName() {
        return $x("//div[@class='b-content__header-title']").text();
    }
}
