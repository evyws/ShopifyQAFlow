package org.evy.toolkit.pages.account;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSection extends BasePage {

    @FindBy(css = ".page-header a[href*='create']")
    private WebElement registrationBtn;

    @FindBy(css = ".page-header a[href*='login']")
    private WebElement loginBtn;

    @FindBy(css = ".page-header .action.switch")
    private WebElement accountDropdownBtn;

    @FindBy(css = ".customer-welcome.active a[href*='logout']")
    private WebElement logoutBtn;


    public RegistrationPage navigateToRegistrationPage(){
        try {
            click(this.registrationBtn,"registration button");
            waitForTitle("Create New Customer Account");

        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to navigate to RegistrationPage",e);
            throw e;
        }
        LoggerUtils.info(getClass(),"Navigate to RegistrationPage");
        return new RegistrationPage();
    }

    public LoginPage navigateToLoginPage(){
        try {
            click(this.loginBtn,"login button");
            waitForTitle("Customer Login");

        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to navigate to LoginPage",e);
            throw e;
        }
        LoggerUtils.info(getClass(),"Navigate to LoginPage");
        return new LoginPage();
    }

    public AccountSection navigateToLogoutPage(){
        try {
            click(this.accountDropdownBtn,"account dropdown");
            click(this.logoutBtn,"logout button");
            waitForUrl("logoutSuccess");
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to navigate to LogoutPage",e);
            throw e;
        }
        return this;
    }
}
