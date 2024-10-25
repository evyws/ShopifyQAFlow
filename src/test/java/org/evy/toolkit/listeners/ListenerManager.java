package org.evy.toolkit.listeners;

import io.qameta.allure.Allure;
import org.evy.toolkit.drivers.DriverManager;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.ByteArrayInputStream;

public class ListenerManager implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        LoggerUtils.info(getClass(), "Starting test suite: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        LoggerUtils.info(getClass(), "Finished test suite: " + suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtils.info(getClass(), "Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtils.info(getClass(), "Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtils.error(getClass(), "Test failed: " + result.getMethod().getMethodName(), null);
        attachScreenshotToAllure(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtils.warn(getClass(), "Test skipped: " + result.getMethod().getMethodName());

    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtils.info(getClass(), "Starting test context: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtils.info(getClass(), "Finished test context: " + context.getName());
    }

    private void attachScreenshotToAllure(String methodName) {
        try {
            WebDriver driver = DriverManager.getInstance().getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(methodName, new ByteArrayInputStream(screenshot));
                LoggerUtils.info(getClass(), "Screenshot attached to Allure report.");
            } else {
                LoggerUtils.warn(getClass(), "Driver does not support taking screenshots. Screenshot Error");
            }
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Failed to capture screenshot: " + e.getMessage() + " Screenshot Error", e);
        }
    }
}
