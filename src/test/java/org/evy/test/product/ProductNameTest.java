package org.evy.test.product;

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

@Epic("Product Management")
@Feature("Product Name")
public class ProductNameTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "productNameData")
    @Parameters({"mainCategory", "subCategory", "subSubCategory", "productName", "expectedTitle"})
    @Story("Setting Product Name")
    @Description("This test validates that the user can set the product name and verify the page title.")
    public void testUserPerformSetProductName(String mainCategory, String subCategory, String subSubCategory, String productName, String expectedTitle) {
        String actualTitle = performSetProductName(mainCategory, subCategory, subSubCategory, productName);
        AssertionUtils.assertEquals(actualTitle, expectedTitle,
                "Validating title in ProductPage after User Select  product name" +
                        ": " + productName + " under " + mainCategory + " > " + subCategory + " > " + subSubCategory);
    }

    private String performSetProductName(String mainCategory, String subCategory, String subSubCategory, String productName) {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesFromDropdown(mainCategory, subCategory, subSubCategory)
                .setProductName(productName)
                .getTitle();
    }
}
