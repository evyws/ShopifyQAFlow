package org.evy.test.account;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.test.BaseTest;
import org.evy.toolkit.data.DataProvider;
import org.evy.toolkit.pages.HomePage;
import org.evy.toolkit.pages.account.RegistrationPage;
import org.evy.toolkit.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Account Management")
@Feature("Registration Feature")
public class RegistrationTest extends BaseTest {

    @Test(dataProviderClass = DataProvider.class, dataProvider = "registrationData")
    @Parameters({"firstname", "lastname", "email", "password", "confirmation", "operation", "expectedMsg"})
    @Story("User Registration Process")
    @Description("This test verifies the user registration functionality by submitting the registration form with different data inputs and comparing the expected and actual messages.")
    public void testUserRegistration(String firstname, String lastname, String email, String password, String confirmation, String operation, String expectedMsg) {
        String actualMsg = performRegistration(firstname, lastname, email, password, confirmation, operation);
        AssertionUtils.assertContains(actualMsg, expectedMsg, "Validating that the actual registration response message contains the expected message.");
    }

    private String performRegistration(String firstname, String lastname, String email, String password, String confirmation, String operation) {
        return new HomePage()
                .navigateToAccountSection()
                .navigateToRegistrationPage()
                .registration(firstname, lastname, email, password, confirmation, false, RegistrationPage.class)
                .getRegistrationResponseMsg(operation);
    }
}
