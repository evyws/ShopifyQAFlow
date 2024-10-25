package org.evy.toolkit.pages.account;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    @FindBy(css = "#firstname")
    private WebElement firstname;

    @FindBy(css = "#lastname")
    private WebElement lastname;

    @FindBy(css = "#email_address")
    private WebElement email;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy(css = "#password-confirmation")
    private WebElement confirmation;

    @FindBy(css = "button[title='Create an Account']")
    private WebElement createAnAccountBtn;

    @FindBy(css = ".message-success>div")
    private WebElement successRegistrationMsg;

    @FindBy(css = "div[class='mage-error']")
    private WebElement errorRegistrationMsg;

    public <T>T registration(String firstname,String lastname,String email,String password,String confirmation,boolean criteria,Class<T>nextPageClass){
        try {
            sendKeys(this.firstname,firstname,"firstname");
            sendKeys(this.lastname,lastname,"lastname");
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            sendKeys(this.confirmation,confirmation,"confirmation");
            click(this.createAnAccountBtn ,"create an account button");
            if(criteria){
                waitForElementVisibility(this.successRegistrationMsg);
                LoggerUtils.info(getClass(),"Success Registration");
                LoggerUtils.info(getClass(),String.format("Navigate to %s",nextPageClass.getSigners()));
            }

            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to complete registration operation",e);
            throw new RuntimeException("Error during Registration operation");
        }
    }

    public String getRegistrationResponseMsg(String operation){
        return switch (operation){
            case "valid"->getText(this.successRegistrationMsg,"success registration message");
            case "invalid"->getText(this.errorRegistrationMsg,"error registration message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }




}
