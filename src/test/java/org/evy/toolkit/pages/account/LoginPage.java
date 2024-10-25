package org.evy.toolkit.pages.account;

import org.apache.commons.logging.Log;
import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#email")
    private WebElement email;

    @FindBy(css = "#pass")
    private WebElement password;

    @FindBy(css = "#send2")
    private WebElement signInBtn;

    @FindBy(css = ".page-header .logged-in")
    private WebElement successLoginMsg;

    @FindBy(css = ".message-error>div")
    private WebElement errorLoginMsg;

    @FindBy(css = "div.mage-error")
    private WebElement emptyLoginMsg;

    public <T>T login(String email,String password,boolean criteria,Class<T>nextPageClass){
        try {
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            click(this.signInBtn,"sign in button");

            if(criteria){
               waitForElementTextToBe(this.successLoginMsg,"Welcome");
                LoggerUtils.info(getClass(),"Login Success");
                LoggerUtils.info(getClass(),String.format("Navigate to %s",nextPageClass.getSimpleName()));
            }
            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to complete login operation",e);
            throw new RuntimeException("Error during login operation");
        }
    }

    public boolean getLoginResponseMsgDisplay(String operation){
        return switch (operation){
            case "valid"->isDisplayed(this.successLoginMsg,"success login message");
            case "invalid"->isDisplayed(this.errorLoginMsg,"error login message");
            case "empty"->isDisplayed(this.emptyLoginMsg,"empty data login message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }


}
