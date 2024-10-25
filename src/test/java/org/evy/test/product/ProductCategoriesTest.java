package org.evy.test.product;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.test.BaseTest;
import org.evy.toolkit.config.ConfigManager;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Product Management")
@Feature("Product Categories")
public class ProductCategoriesTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "productCategoriesData")
    @Parameters({"mainCategory", "subCategory", "subSubCategory", "expectedUrl"})
    @Story("Setting Product Categories")
    @Description("This test validates that the user can set product categories correctly and navigates to the expected URL.")
    public void testUserSetProductCategories(String mainCategory, String subCategory, String subSubCategory, String expectedUrl) {
        String actualUrl = performSetProductCategories(mainCategory, subCategory, subSubCategory);
        AssertionUtils.assertEquals(actualUrl, ConfigManager.getConfig().baseUrl() + expectedUrl,
                "Validating URL navigation for categories: " + mainCategory + " > " + subCategory + " > " + subSubCategory);
    }

    private String performSetProductCategories(String mainCategory, String subCategory, String subSubCategory) {
        return new HomePage()
                .navigateToProductSection()
                .setProductCategoriesFromDropdown(mainCategory, subCategory, subSubCategory)
                .getUrl();
    }
}
