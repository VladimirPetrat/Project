package prom_ua_page_objects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SignInFormPage {

    @Step("SignInFormPage: set login {login}")
    public SignInFormPage setLogin(String login) {
        $x("//input[@class='input__field--zuc14']").val(login);

        return this;
    }

    @Step("SignInFormPage: submit login")
    public SignInFormPage submitLogin() {
        $x("//button[@id='phoneEmailConfirmButton']").click();

        return this;
    }

    @Step("SignInFormPage: set password {password}")
    public SignInFormPage setPassword(String password) {
        $x("//input[@id='enterPassword']").val(password);

        return this;
    }

    @Step("SignInFormPage: submit password")
    public UserOrdersPage submitPassword() {
        $x("//button[@id='enterPasswordConfirmButton']").click();

        return new UserOrdersPage();
    }
}
