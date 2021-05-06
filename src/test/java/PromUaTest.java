import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import prom_ua_page_objects.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

public class PromUaTest extends TestRunner {

    private final List<String> searchRequests = new ArrayList<>();
    private String userLogin;
    private String userPassword;
    private String userCabinetOrdersPageName;
    private HomePage homePage;

    @BeforeClass
    public void setUpTestData() throws IOException {
        var properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/testData.properties"));
        searchRequests.add(properties.getProperty("first.search.request"));
        searchRequests.add(properties.getProperty("second.search.request"));
        searchRequests.add(properties.getProperty("third.search.request"));
        userLogin = properties.getProperty("user.login");
        userPassword = properties.getProperty("user.password");
        userCabinetOrdersPageName = properties.getProperty("user.cabinet.orders.page.name");
    }

    @BeforeMethod
    public void openHomePage() throws IOException {
        homePage = new HomePage().openHomePage();
    }

    @Test
    public void verifyProductPriceInBasket() {
        var productListPage = homePage
                .openHomePage()
                .searchFor(searchRequests.get(0));
        var expectedProductPrice = productListPage.getProductPrice(1);
        var actualProductPrice = productListPage
                .openProduct(1)
                .addProductToBasket()
                .getProductPrice(1);
        Assert.assertEquals(expectedProductPrice, actualProductPrice,
                "Product price in basket is incorrect");
    }

    @Test
    public void verifyLogin() {
        var userOrdersPageName = homePage
                .openHomePage()
                .openLoginPage()
                .signInAsCustomer()
                .setLogin(userLogin)
                .submitLogin()
                .setPassword(userPassword)
                .submitPassword()
                .getPageName();
        Assert.assertEquals(userOrdersPageName, userCabinetOrdersPageName,
                "Customer orders page name is incorrect");
    }

    @Test
    public void verifyProductSearch() {
        var searchProductName = searchRequests.get(1);
        var actualProductName = homePage
                .searchFor(searchProductName)
                .openProduct(1)
                .getProductPageName();
        Assert.assertTrue(containsIgnoreCase(actualProductName,
                searchProductName), "Product page name is incorrect");
    }

    @Test
    public void verifyAddProductToBasket() {
        var isBasketEmpty = homePage
                .searchFor(searchRequests.get(2))
                .openProduct(1)
                .addProductToBasket()
                .isBasketEmpty();
        Assert.assertFalse(isBasketEmpty,
                "Product should be added to basket");
    }
}
