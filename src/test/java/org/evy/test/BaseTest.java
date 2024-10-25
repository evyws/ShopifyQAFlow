package org.evy.test;

import org.evy.toolkit.config.ConfigManager;
import org.evy.toolkit.drivers.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {


    @BeforeMethod
    public void setup(){
        DriverManager.getInstance().initDriver(ConfigManager.getConfig().browserType(),ConfigManager.getConfig().headlessMode());
    }

    @AfterMethod
    public void terminate(){
        DriverManager.getInstance().quitDriver();
    }
}
