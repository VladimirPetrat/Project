package prom_ua_page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SignInPage {

    @Step("Sign")
    public SignInFormPage signInAsCustomer() {
        $x("//a[@data-qaid='go_sign_in_customer']").click();
        return new SignInFormPage();
    }
}
