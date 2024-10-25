package org.evy.test.cart;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.test.BaseTest;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Cart Management")
@Feature("Remove Product From Cart")
public class RemoveProductFromCartTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class,dataProvider = "removeProductFromCartData")
    @Parameters({
            "mainCategory", "subCategory", "subSubCategory",
            "productName", "productSize", "productColor",
            "productQuantity", "expectedMsg"
    })
    @Story("User removes a product from their cart")
    @Description("This test verifies that the user can successfully remove a product from their shopping cart and receives the correct confirmation message.")
    public void testUserRemoveProductFromCart(
            String mainCategory, String subCategory, String subSubCategory, String productName, String productSize, String productColor, String productQuantity, String expectedMsg) {
        String actualMsg = performRemoveProductFromCart(mainCategory, subCategory, subSubCategory, productName, productSize, productColor, productQuantity);

        AssertionUtils.assertEquals(actualMsg, expectedMsg,
                String.format("Verifying confirmation message after removing product '%s' (Size: '%s', Color: '%s', Quantity: '%s') from '%s > %s > %s'.",
                        productName, productSize, productColor, productQuantity, mainCategory, subCategory, subSubCategory));
    }

    private String performRemoveProductFromCart(
            String mainCategory, String subCategory, String subSubCategory, String productName, String productSize, String productColor, String productQuantity) {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesFromDropdown(mainCategory, subCategory, subSubCategory)
                .setProductName(productName)
                .setProductSize(productSize)
                .setProductColor(productColor)
                .setProductQuantity(productQuantity)
                .clickAddProductToCart()
                .navigateToCartPage()
                .clickRemoveProductFromCart()
                .getRemoveProductFromCartMsg();
    }
}
