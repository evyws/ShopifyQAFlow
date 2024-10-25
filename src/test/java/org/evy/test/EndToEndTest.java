package org.evy.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Test;

/**
 * Test that verifies a user can successfully complete the entire end-to-end purchase flow.
 * <p>
 * This includes:
 * - Registering a new account
 * - Selecting and customizing a product
 * - Adding the product to the cart
 * - Proceeding through the checkout process
 * - Confirming the order and verifying the success message
 * </p>
 */
 @Epic("End-To-End")
@Feature("User Purchase Flow")
public class EndToEndTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "endToEndData")
    @Story("User successfully completes a purchase")
    @Description("This test verifies that a user can complete the end-to-end purchase process successfully, from registration to order placement.")
    public void testUserEndToEnd(String firstname, String lastname, String email, String password, String confirmation,
                                 String mainCategory, String subCategory, String subSubCategory, String productName,
                                 String productSize, String productColor, String productQuantity,
                                 String billingFirstName, String billingLastName, String billingAddress, String billingCity,
                                 String billingPostalCode, String billingCountry, String billingPhone,String expectedMsg) {

        String actualMsg = performEndToEnd(firstname, lastname, email, password, confirmation,
                mainCategory, subCategory, subSubCategory, productName, productSize,
                productColor, productQuantity, billingFirstName, billingLastName,
                billingAddress, billingCity, billingPostalCode, billingCountry, billingPhone);

        AssertionUtils.assertEquals(actualMsg, expectedMsg, "Verify the success order message after completing the purchase.");
    }

    private String performEndToEnd(String firstname, String lastname, String email, String password, String confirmation,
                                   String mainCategory, String subCategory, String subSubCategory, String productName,
                                   String productSize, String productColor, String productQuantity,
                                   String billingAddressFirstname, String billingAddressLastname, String billingAddressFullAddress,
                                   String billingAddressCity, String billingAddressPostalCode, String billingAddressCountry,
                                   String billingAddressPhoneNumber) {

        return new HomePage()
                .navigateToAccountSection()
                .navigateToRegistrationPage()
                .registration(firstname, lastname, email, password, confirmation, true, HomePage.class)
                .navigateToProductSection()
                .setProductCategoriesFromDropdown(mainCategory, subCategory, subSubCategory)
                .setProductName(productName)
                .setProductSize(productSize)
                .setProductColor(productColor)
                .setProductQuantity(productQuantity)
                .clickAddProductToCart()
                .navigateToCartPage()
                .navigateToCheckoutPage()
                .setBillingAddressFirstname(billingAddressFirstname)
                .setBillingAddressLastname(billingAddressLastname)
                .setBillingAddressHomeAddress(billingAddressFullAddress)
                .setBillingAddressCity(billingAddressCity)
                .setBillingAddressPostalCode(billingAddressPostalCode)
                .setBillingAddressCountry(billingAddressCountry)
                .setBillingAddressPhoneNumber(billingAddressPhoneNumber)
                .setFlatRateShippingMethod()
                .clickNextPageButton()
                .clickPlaceOrderBtn()
                .getSuccessOrderMsg();
    }
}
