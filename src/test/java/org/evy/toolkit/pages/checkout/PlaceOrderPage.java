package org.evy.toolkit.pages.checkout;

import org.checkerframework.checker.units.qual.C;
import org.evy.toolkit.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderPage extends BasePage {

    @FindBy(css = ".page-title span")
    private WebElement successOrderMsg;

    public String getSuccessOrderMsg(){
        return getText(this.successOrderMsg,"success order message");
    }
}
