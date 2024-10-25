package org.evy.toolkit.pages.product;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.pages.checkout.ShippingAddressPage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(css = ".action.action-delete")
    private WebElement removeProductFromCartBtn;

    @FindBy(css = ".cart-empty>p")
    private WebElement removeProductFromCartMsg;

    @FindBy(css = "button[data-role='proceed-to-checkout']")
    private WebElement checkoutBtn;

    public CartPage clickRemoveProductFromCart(){
        click(this.removeProductFromCartBtn,"remove product from cart button");
        return this;
    }

    public String getRemoveProductFromCartMsg(){
        return getText(this.removeProductFromCartMsg,"remove product from cart message");
    }

    public ShippingAddressPage navigateToCheckoutPage(){
        try {
            click(this.checkoutBtn,"checkout button");
            waitForTitle("Checkout");
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to navigate to CheckoutPage",e);
            throw e;
        }
        LoggerUtils.info(getClass(),"Navigate to CheckoutPage");
        return new ShippingAddressPage();
    }





}
