package org.evy.test.account;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.test.BaseTest;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.pages.account.LoginPage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Account Management")
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "loginData")
    @Parameters({"email", "password", "operation", "expectedMsgStatus"})
    @Story("User Login Proccess")
    @Description("Tests the login functionality for valid and invalid users. " +
            "It checks if the login status is as expected based on the input credentials.")
    public void testUserLogin(String email, String password, String operation) {
        boolean actualMsgStatus = performLogin(email, password, operation);
        AssertionUtils.assertTrue(actualMsgStatus,
                String.format("Validating that the actual login status (%b) matches the expected status (%b).",
                        actualMsgStatus, true));
    }

    private boolean performLogin(String email, String password, String operation) {
        return new HomePage()
                .navigateToAccountSection()
                .navigateToLoginPage()
                .login(email, password, false, LoginPage.class)
                .getLoginResponseMsgDisplay(operation);
    }
}
