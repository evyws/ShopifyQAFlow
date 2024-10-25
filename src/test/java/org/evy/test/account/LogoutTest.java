package org.evy.test.account;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.test.BaseTest;
import org.evy.toolkit.config.ConfigManager;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Test;

@Epic("Account Management")
@Feature("Logout Feature")
public class LogoutTest extends BaseTest {

    @Test
    @Story("User Logout Process")
    @Description("This test verifies the user logout functionality. The user logs in, performs a logout, and the URL is validated to ensure that the user is logged out successfully.")
    public void testUserLogout() {
        String actualUrl = performLogout();
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/logoutSuccess/";
        AssertionUtils.assertEquals(actualUrl, expectedUrl,
                String.format("Validating that the user is redirected to the expected URL (%s) after logout. Actual URL: %s", expectedUrl, actualUrl));
    }

    private String performLogout() {
        return new HomePage()
                .navigateToAccountSection()
                .navigateToLoginPage()
                .login(ConfigManager.getConfig().email(), ConfigManager.getConfig().password(), true, HomePage.class)
                .navigateToAccountSection()
                .navigateToLogoutPage()
                .getUrl();
    }
}
