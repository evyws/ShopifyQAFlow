package org.evy.toolkit.pages.product;

import org.evy.toolkit.pages.BasePage;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductSection extends BasePage {


    public ProductListingPage setProductCategoriesFromDropdown(String mainCategory,String subCategory,String subSubCategory){
        try {
            if(subCategory.isEmpty() && subSubCategory.isEmpty()){
                click(getMainCategoryElement(mainCategory),mainCategory);
                waitForTitle(mainCategory);
            }
            else if(subSubCategory.isEmpty()){
                moveToElement(getMainCategoryElement(mainCategory),mainCategory);
                click(getSubCategoryElement(mainCategory, subCategory),subCategory);
                waitForTitle(subCategory+" - "+mainCategory);
            }
            else{
                moveToElement(getMainCategoryElement(mainCategory),mainCategory);
                moveToElement(getSubCategoryElement(mainCategory, subCategory),subCategory);
                click(getSubSubCategoryElement(mainCategory, subCategory, subSubCategory),subCategory);
                waitForTitle(subSubCategory+" - "+subCategory+" - "+mainCategory);
            }
            LoggerUtils.info(getClass(), String.format("Selected categories: %s -> %s -> %s", mainCategory, subCategory, subSubCategory));
        } catch (Exception e) {
            LoggerUtils.error(getClass(), String.format("Failed to select categories: mainCategory='%s', subCategory='%s', subSubCategory='%s'",
                    mainCategory, subCategory, subSubCategory), e);
            throw e;
        }

        LoggerUtils.info(getClass(), "Navigating to ProductListingPage");
        return new ProductListingPage();
    }



    private WebElement getMainCategoryElement(String mainCategory){
        String value=String.format("//ul[@id]/li/a[normalize-space()='%s']",mainCategory);
        return driver.findElement(By.xpath(value));
    }

    private WebElement getSubCategoryElement(String mainCategory,String subCategory){
        String value=String.format("//ul[@id]/li/a[normalize-space()='%s']/parent::li/ul//a[normalize-space()='%s']",mainCategory,subCategory);
        return driver.findElement(By.xpath(value));
    }

    private WebElement getSubSubCategoryElement(String mainCategory,String subCategory,String subSubCategory){
        String value=String.format("//ul[@id]/li/a[normalize-space()='%s']/parent::li/ul//a[normalize-space()='%s']/parent::li/ul//a[normalize-space()='%s']"
                ,mainCategory,subCategory,subSubCategory);
        return driver.findElement(By.xpath(value));
    }
}
