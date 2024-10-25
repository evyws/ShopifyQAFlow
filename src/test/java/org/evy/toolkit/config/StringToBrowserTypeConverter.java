package org.evy.toolkit.config;

import org.aeonbits.owner.Converter;
import org.evy.toolkit.drivers.BrowserType;
import org.evy.toolkit.utils.LoggerUtils;

import java.lang.reflect.Method;

public class StringToBrowserTypeConverter implements Converter<BrowserType> {

    @Override
    public BrowserType convert(Method method, String browserType) {
        try {
            BrowserType result = BrowserType.valueOf(browserType.toUpperCase());
            LoggerUtils.info(getClass(), String.format("Successfully converted '%s' to BrowserType: %s", browserType, result));
            return result;
        } catch (Exception e) {
            LoggerUtils.error(getClass(), String.format("An unexpected error occurred while converting '%s'.", browserType), e);
            throw e;
        }
    }

}
