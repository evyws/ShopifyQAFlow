package org.evy.toolkit.pages.checkout;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShippingAddressPage extends BasePage {

    @FindBy(css = "input[name='firstname']")
    private WebElement firstname;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastname;

    @FindBy(css = "input[name='street[0]']")
    private WebElement address;

    @FindBy(css = "input[name='city']")
    private WebElement city;

    @FindBy(css = "input[name='postcode']")
    private WebElement postalCode;

    @FindBy(css = "select[name='country_id']")
    private WebElement country;

    @FindBy(css = "input[name='telephone']")
    private WebElement phone;

    @FindBy(css = "input[value='flatrate_flatrate']")
    private WebElement flatRateShippingMethod;

    @FindBy(css = "button.continue")
    private WebElement nextPageButton;

    public ShippingAddressPage setBillingAddressFirstname(String firstname){
        sendKeys(this.firstname,firstname,"billingAddress firstname");
        return this;
    }

    public ShippingAddressPage setBillingAddressLastname(String lastname){
        sendKeys(this.lastname,lastname,"billingAddress lastname");
        return this;
    }

    public ShippingAddressPage setBillingAddressHomeAddress(String address){
        sendKeys(this.address,address,"billingAddress home address");
        return this;
    }

    public ShippingAddressPage setBillingAddressCity(String city){
        sendKeys(this.city,city,"billingAddress city");
        return this;
    }

    public ShippingAddressPage setBillingAddressCountry(String country){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        String currentUrl = driver.getCurrentUrl();
        selectByVisibleText(this.country,country,"country");
        try {
            wait.until(ExpectedConditions.jsReturnsValue("return jQuery.active == 0"));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)),
                    ExpectedConditions.stalenessOf(this.country),
                    ExpectedConditions.visibilityOf(this.country)
            ));
        } catch (TimeoutException e) {
            LoggerUtils.warn(getClass(), "Page did not reload as expected after country selection. Continuing...");
        }
        PageFactory.initElements(driver, this);
        return this;

    }

    public ShippingAddressPage setBillingAddressPostalCode(String postalCode){
        sendKeys(this.postalCode,postalCode,"billingAddress postalCode");
        return this;
    }

    public ShippingAddressPage setBillingAddressPhoneNumber(String phone){
        sendKeys(this.phone,phone,"billingAddress phone number");
        return this;
    }

    public ShippingAddressPage setFlatRateShippingMethod(){
        click(this.flatRateShippingMethod,"billingAddress flatRate shipping method ");
        return this;
    }

    public PaymentPage clickNextPageButton(){
       try {
           click(this.nextPageButton,"nextPage button");
           waitForUrl("payment");
       }catch (Exception e){
           LoggerUtils.error(getClass(),"Failed to navigate to PaymentPage",e);
           throw e;
       }
       LoggerUtils.info(getClass(),"Navigate to PaymentPage");
        return new PaymentPage();

    }

}
