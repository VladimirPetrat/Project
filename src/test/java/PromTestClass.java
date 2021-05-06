import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import prom_ua_page_objects.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

public class PromTestClass extends TestRunner {

    private static final List<String> searchRequests = new ArrayList<>();
    private static String userLogin;
    private static String userPassword;
    private static String userCabinetOrdersPageName;
    private HomePage homePage;

    @BeforeMethod
    public void openHomePage() throws IOException {
        loadTestData();
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
        String userOrdersPageName = homePage
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
        String searchProductName = searchRequests.get(1);
        String actualProductName = homePage
                .searchFor(searchProductName)
                .openProduct(1)
                .getProductPageName();
        Assert.assertTrue(containsIgnoreCase(actualProductName, searchProductName),
                "Product page name is incorrect");
    }

    @Test
    public void verifyAddProductToBasket() {
        boolean isBasketEmpty = homePage
                .searchFor(searchRequests.get(2))
                .openProduct(1)
                .addProductToBasket()
                .isBasketEmpty();
        Assert.assertFalse(isBasketEmpty,
                "Product should be added to basket");
    }

    private static void loadTestData() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/testData.properties"));
        searchRequests.add(properties.getProperty("firstSearchRequest"));
        searchRequests.add(properties.getProperty("secondSearchRequest"));
        searchRequests.add(properties.getProperty("thirdSearchRequest"));
        userLogin = properties.getProperty("userLogin");
        userPassword = properties.getProperty("userPassword");
        userCabinetOrdersPageName = properties.getProperty("userCabinetOrdersPageName");
    }
}
