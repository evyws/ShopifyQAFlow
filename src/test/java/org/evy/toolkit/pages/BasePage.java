package org.evy.toolkit.pages;

import org.evy.toolkit.drivers.DriverManager;
import org.evy.toolkit.utils.ActionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(){
        this.driver= DriverManager.getInstance().getDriver();
        PageFactory.initElements(driver,this);
    }

    protected void sendKeys(WebElement element,String value,String elementName){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement webElement=waitForElementVisibility(element);
                    webElement.clear();
                    webElement.sendKeys(value);
                },
                String.format("Send keys to %s:%s",elementName,value),
                String.format("Failed to send keys to %s",elementName)
        );
    }

    protected void click(WebElement element,String elementName){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement webElement=waitForElementVisibility(element);
                    JavascriptExecutor js=(JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", webElement);
                },
                String.format("Click on %s",elementName),
                String.format("Failed to click on: %s",elementName)
        );
    }

    protected void moveToElement(WebElement element,String elementName){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement webElement=waitForElementVisibility(element);
                    Actions actions=new Actions(driver);
                    actions.moveToElement(webElement).build().perform();
                },
                String.format("Move to %s",elementName),
                String.format("Failed to move to: %s",elementName)
        );
    }

    protected void selectByVisibleText(WebElement element,String value,String elementName){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement webElement=waitForElementVisibility(element);
                    Select select=new Select(webElement);
                    select.selectByVisibleText(value);
                },
                String.format("Selected %s from %s dropdown",value,elementName),
                String.format("Failed to select %s from %s dropdown",value,elementName)
        );
    }

    protected String getText(WebElement element,String elementName){
        return ActionUtils.performAction(
                getClass(),
                ()->{
                    WebElement webElement=waitForElementVisibility(element);
                    return webElement.getText().trim();
                },
                String.format("Retrieve Text from %s",elementName),
                String.format("Failed to retrieve text from %s",elementName)
        );
    }
    protected boolean isDisplayed(WebElement element,String elementName){
        return ActionUtils.performAction(
                getClass(),
                element::isDisplayed,
                String.format("%s id Displayed",elementName),
                String.format("%s is not displayed",elementName)
        );
    }

    protected void waitForTitle(String title){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    getWait().until(ExpectedConditions.titleIs(title));
                },
                String.format("Title is matched to: %s",title),
                String.format("Title is not matched to: %s",title)
        );
    }

    protected void waitForUrl(String url){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    getWait().until(ExpectedConditions.urlContains(url));
                },
                String.format("Url is matched to: %s",url),
                String.format("Url is not matched to: %s",url)
        );
    }

    public String getUrl(){
        return ActionUtils.performAction(
                getClass(),
               driver::getCurrentUrl,"Retrieve current url","Failed to retrieve current url"
        );
    }

    public String getTitle(){
        return ActionUtils.performAction(
                getClass(),
                driver::getTitle,"Retrieve current title","Failed to retrieve current title"
        );
    }



    private FluentWait<WebDriver>getWait(){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class);
    }

    protected WebElement waitForElementVisibility(WebElement element){
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementTextToBe(WebElement element,String text){
        ActionUtils.performAction(
                getClass(),
                ()->{
                    getWait().until(ExpectedConditions.textToBePresentInElement(element,text));
                },
                String.format("Successfully wait for %s to contains %s",element,text),
                String.format("Failed to wait for %s to contain %s ",element,text)
        );
    }
}
