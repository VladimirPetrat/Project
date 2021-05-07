import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import prom_ua_page_objects.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.containsIgnoreCase;
import static org.testng.Assert.*;

public class PromUaTest extends TestRunner {

    private String userLogin;
    private String userPassword;
    private HomePage homePage;

    @BeforeClass
    public void setUpTestData() throws IOException {
        var properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/testData.properties"));
        userLogin = properties.getProperty("user.login");
        userPassword = properties.getProperty("user.password");
    }

    @BeforeMethod
    public void openHomePage() throws IOException {
        homePage = new HomePage().openHomePage();
    }

    @Test
    public void verifyProductPriceInBasket() {
        var productListPage = homePage
                .openHomePage()
                .searchFor("галстук");
        var expectedProductPrice = productListPage.getProductPrice(1);
        var actualProductPrice = productListPage
                .openProduct(1)
                .addProductToBasket()
                .getProductPrice(1);

        assertEquals(expectedProductPrice, actualProductPrice,
                format("Product price in basket should be %d", expectedProductPrice));
    }

    @Test
    public void verifyLogin() {
        var expectedUserOrdersPageName = "Замовлення";
        var actualUserOrdersPageName = homePage
                .openHomePage()
                .openLoginPage()
                .signInAsCustomer()
                .setLogin(userLogin)
                .submitLogin()
                .setPassword(userPassword)
                .submitPassword()
                .getPageName();

        assertEquals(actualUserOrdersPageName, expectedUserOrdersPageName,
                format("Customer orders page name should be %s", expectedUserOrdersPageName));
    }

    @Test
    public void verifyProductSearch() {
        var searchProductName = "піджак";
        var actualProductName = homePage
                .searchFor(searchProductName)
                .openProduct(1)
                .getProductPageName();

        assertTrue(containsIgnoreCase(actualProductName, searchProductName),
                format("Product page name should contain %s", searchProductName));
    }

    @Test
    public void verifyAddProductToBasket() {
        var isBasketEmpty = homePage
                .searchFor("куртка")
                .openProduct(1)
                .addProductToBasket()
                .isBasketEmpty();

        assertFalse(isBasketEmpty,
                "Product should be added to basket");
    }
}
