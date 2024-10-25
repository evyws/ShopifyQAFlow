package org.evy.toolkit.drivers;

import org.evy.toolkit.config.ConfigManager;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * Singleton class responsible for managing WebDriver instances for different browser types.
 * This class utilizes the ThreadLocal storage to ensure that each thread has its own WebDriver instance.
 */
public final class DriverManager {

    private static final DriverManager INSTANCE = new DriverManager();

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        return INSTANCE;
    }

    public void initDriver(BrowserType browserType, boolean headlessMode) {
        try {
            WebDriver driver = browserType.getDriver(headlessMode);
            configureDriver(driver);
            THREAD_LOCAL.set(driver);
        } catch (Exception e) {
            LoggerUtils.info(DriverManager.class, "Failed to add driver to the thread local");
            throw e;
        }
    }

    public void quitDriver() {
        try {
            WebDriver driver = THREAD_LOCAL.get();
            if (driver != null) {
                driver.quit();
                THREAD_LOCAL.remove();
                LoggerUtils.info(DriverManager.class, "Quit current driver & removed from ThreadLocal");
            }
        } catch (Exception e) {
            LoggerUtils.error(DriverManager.class, "Failed to quit driver", e);
            throw e;
        }
    }

    public WebDriver getDriver() {
        return THREAD_LOCAL.get();
    }


    private void configureDriver(WebDriver driver) {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.getConfig().pageLoadTime()));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getConfig().implicitTime()));
//driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(ConfigManager.getConfig().baseUrl());
            LoggerUtils.info(DriverManager.class, "Configured driver settings");
        } catch (Exception e) {
            LoggerUtils.error(DriverManager.class, "Failed to configure driver settings", e);
            throw e;
        }
    }


}
