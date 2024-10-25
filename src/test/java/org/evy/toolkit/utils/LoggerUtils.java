package org.evy.toolkit.utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtils {

    private LoggerUtils(){}


    private static Logger getLogger(Class<?>cls){
        return LogManager.getLogger(cls);
    }

    public static void info(Class<?>cls,String msg){
        getLogger(cls).info(msg);
        Allure.step(msg);
    }

    public static void warn(Class<?>cls,String msg){
        getLogger(cls).warn(msg);
        Allure.step(msg);
    }

    public static void error(Class<?>cls,String msg,Exception e){
        getLogger(cls).error(msg,e);
        Allure.step(msg);
    }


}
