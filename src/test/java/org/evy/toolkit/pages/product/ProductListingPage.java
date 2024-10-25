package org.evy.toolkit.pages.product;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ProductListingPage extends BasePage {


    public ProductPage setProductName(String productName){
        try {
            click(getProductNameElement(productName),productName);
            LoggerUtils.info(getClass(), String.format("Selected productName: '%s'", productName));
            waitForTitle(productName);

        }catch (Exception e){
            LoggerUtils.error(getClass(), String.format("Failed to select productName: '%s'", productName), e);
            throw e;
        }
        LoggerUtils.info(getClass(),"Navigate to ProductPage");
        return new ProductPage();
    }


    private WebElement getProductNameElement(String productName){
        String value=String.format("//a[@class='product-item-link'][normalize-space()='%s']",productName);
        return driver.findElement(By.xpath(value));
    }




}
