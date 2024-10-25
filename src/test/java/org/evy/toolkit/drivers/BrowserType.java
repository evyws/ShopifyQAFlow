package org.evy.toolkit.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.evy.toolkit.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.safari.SafariDriver;

/**
 * Enum representing different types of web browsers.
 * This enum supplies a specific WebDriver implementation based on the selected browser type.
 */
public enum BrowserType {

    CHROME {
        @Override
        public WebDriver getDriver(boolean headlessMode) {
            try {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headlessMode) {
                    options.addArguments("--headless");
                }
                LoggerUtils.info(BrowserType.class, "Initialize chrome driver");
                return new ChromeDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize chrome driver", e);
                throw e;
            }
        }
    },
    FIREFOX {
        @Override
        public WebDriver getDriver(boolean headlessMode) {
            try {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (headlessMode) {
                    options.addArguments("--headless");
                }
                LoggerUtils.info(BrowserType.class, "Initialize firefox driver");
                return new FirefoxDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize firefox driver", e);
                throw e;
            }
        }
    },
    EDGE {
        @Override
        public WebDriver getDriver(boolean headlessMode) {
            try {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                if (headlessMode) {
                    options.addArguments("--headless");
                }
                LoggerUtils.info(BrowserType.class, "Initialize edge driver");
                return new EdgeDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize edge driver", e);
                throw e;
            }
        }
    },
    EXPLORER {
        @Override
        public WebDriver getDriver(boolean headlessMode) {
            try {
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize explorer driver", e);
                throw e;
            }
        }
    },
    SAFARI {
        @Override
        public WebDriver getDriver(boolean headlessMode) {
            try {
                WebDriverManager.safaridriver().setup();
                LoggerUtils.info(BrowserType.class, "Initialize safari driver");
                return new SafariDriver();
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize safari driver", e);
                throw e;
            }
        }
    },
    OPERA {
        @Override
        public WebDriver getDriver(boolean headlessMode) {
            try {
                WebDriverManager.operadriver().setup();
                ChromeOptions options=new ChromeOptions();
                if (headlessMode) {
                    options.addArguments("--headless");
                }
                LoggerUtils.info(BrowserType.class, "Initialize opera driver");
                return new ChromeDriver(options);
            } catch (Exception e) {
                LoggerUtils.error(BrowserType.class, "Failed to initialize opera driver", e);
                throw e;
            }
        }
    };

    /**
     * Abstract method to be implemented for each browser type
     * @param headlessMode boolean flag indicating whether to run in headless mode.
     * @return WebDriver instance for the specified browser type.
     */
    public abstract WebDriver getDriver(boolean headlessMode);
}
