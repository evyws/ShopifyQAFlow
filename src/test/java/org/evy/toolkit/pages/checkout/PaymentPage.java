package org.evy.toolkit.pages.checkout;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(css = "button[title='Place Order']")
    private WebElement placeOrderBtn;


    public PlaceOrderPage clickPlaceOrderBtn(){
        try {
            click(this.placeOrderBtn,"place order button");
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to navigate to PlaceOrderPage",e);
            throw e;
        }
        waitForUrl("success");
        return new PlaceOrderPage();
    }
}
