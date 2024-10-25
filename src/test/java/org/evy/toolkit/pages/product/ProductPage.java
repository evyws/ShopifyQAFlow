package org.evy.toolkit.pages.product;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "#qty")
    private WebElement productQuantity;

    @FindBy(css = "button[title='Add to Cart']")
    private WebElement addProductToCartBtn;

    @FindBy(css = ".success.message>div")
    private WebElement addToCartSuccessMsg;

    @FindBy(css = "#qty-error")
    private WebElement addToCartFailedMsg;

    @FindBy(css = ".action.showcart")
    private WebElement cartDropdownBtn;

    @FindBy(css = ".action.viewcart")
    private WebElement cartBtn;


    public ProductPage setProductSize(String productSize) {
        try {
            click(getProductSizeElement(productSize), productSize + " size");
        } catch (Exception e) {
            LoggerUtils.error(getClass(), String.format("Failed to select product size: '%s'", productSize), e);
            throw e;
        }
        return this;
    }

    public ProductPage setProductColor(String productColor) {
        try {
            click(getProductColorElement(productColor), productColor + " color");
        } catch (Exception e) {
            LoggerUtils.error(getClass(), String.format("Failed to select product color: '%s'", productColor), e);
            throw e;
        }
        return this;
    }

    public ProductPage setProductQuantity(String productQuantity) {
        try {
            sendKeys(this.productQuantity, productQuantity, "product quantity");
        } catch (Exception e) {
            LoggerUtils.error(getClass(), String.format("Failed to set product quantity: '%s'", productQuantity), e);
            throw e;
        }
        return this;
    }

    public ProductPage clickAddProductToCart() {
        try {
            click(this.addProductToCartBtn, "add product to cart button");
            LoggerUtils.info(getClass(), "Successfully clicked 'Add to Cart' button");
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Failed to click 'Add to Cart' button", e);
            throw e;
        }
        return this;
    }

    public String getAddProductToCartMsg(String operation) {
        String result = "";
        try {
            if ("valid".equalsIgnoreCase(operation)) {
                result = getText(this.addToCartSuccessMsg, "add product to cart success message");
                LoggerUtils.info(getClass(), "Retrieved success message after adding product to cart");
            } else if ("invalid".equalsIgnoreCase(operation)) {
                result = getText(this.addToCartFailedMsg, "add to cart fail message");
                LoggerUtils.info(getClass(), "Retrieved failure message for adding product to cart");
            }
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Failed to retrieve add-to-cart message", e);
            throw e;
        }
        return result;
    }

    public CartPage navigateToCartPage(){
        try {
           if(this.addToCartSuccessMsg.isEnabled()){
               click(this.cartDropdownBtn,"cart dropdown button");
               click(this.cartBtn,"cart button");
               waitForTitle("Shopping Cart");
            }

        }catch (Exception e){
            LoggerUtils.error(getClass(),"Failed to navigate to CartPage",e);
            throw e;
        }
        LoggerUtils.info(getClass(),"Navigate to CartPage");
        return new CartPage();
    }





    private WebElement getProductSizeElement(String productSize){
        String value=String.format("//div[@class='swatch-option text'][@aria-label='%s']",productSize);
        return driver.findElement(By.xpath(value));
    }

    private WebElement getProductColorElement(String productColor){
        String value=String.format("//div[@class='swatch-option color'][@aria-label='%s']",productColor);
        return driver.findElement(By.xpath(value));
    }
}
