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
@Feature("Add Product to Cart")
public class AddProductToCartTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "addProductToCartData")
    @Parameters({"mainCategory", "subCategory", "subSubCategory", "productName", "productSize",
            "productColor", "productQuantity", "operation", "expectedMsg"})
    @Story("User can add products to the cart")
    @Description("Verify that a user can add a product to the cart and receive the correct confirmation message.")
    public void testUserAddProductToCart(String mainCategory, String subCategory, String subSubCategory,
                                         String productName, String productSize, String productColor,
                                         String productQuantity, String operation, String expectedMsg) {
        String actualMsg = performAddProductToCart(mainCategory, subCategory, subSubCategory,
                productName, productSize, productColor,
                productQuantity, operation);

        String assertionMessage = String.format("Validating message for operation '%s' with product '%s' (Size: %s, Color: %s, Quantity: %s). " +
                        "Expected confirmation message: '%s'.",
                operation, productName, productSize, productColor, productQuantity, expectedMsg);
        AssertionUtils.assertEquals(actualMsg, expectedMsg, assertionMessage);
    }

    private String performAddProductToCart(String mainCategory, String subCategory, String subSubCategory,
                                           String productName, String productSize, String productColor,
                                           String productQuantity, String operation) {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesFromDropdown(mainCategory, subCategory, subSubCategory)
                .setProductName(productName)
                .setProductSize(productSize)
                .setProductColor(productColor)
                .setProductQuantity(productQuantity)
                .clickAddProductToCart()
                .getAddProductToCartMsg(operation);
    }
}
