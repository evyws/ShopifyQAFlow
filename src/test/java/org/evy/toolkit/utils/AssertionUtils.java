package org.evy.toolkit.utils;

import org.assertj.core.api.Assertions;

public class AssertionUtils {

    public static <T> void assertEquals(T actual, T expected, String message) {
        try {
            Assertions.assertThat(actual)
                    .as(message)
                    .isEqualTo(expected);
            LoggerUtils.info(AssertionUtils.class, String.format("Assertion passed: %s | Actual: %s, Expected: %s", message, actual, expected));
        } catch (Exception e) {
            LoggerUtils.error(AssertionUtils.class, String.format("Assertion failed: %s | Actual: %s, Expected: %s", message, actual, expected), e);
            throw e;
        }
    }

    public static void assertContains(String actual, String expected, String message) {
        try {
            Assertions.assertThat(actual)
                    .as(message)
                    .contains(expected);
            LoggerUtils.info(AssertionUtils.class, String.format("Assertion passed: %s | Actual contains: %s, Expected to contain: %s", message, actual, expected));
        } catch (Exception e) {
            LoggerUtils.error(AssertionUtils.class, String.format("Assertion failed: %s | Actual contains: %s, Expected to contain: %s", message, actual, expected), e);
            throw e;
        }
    }

    public static void assertTrue(boolean condition, String message) {
        try {
            Assertions.assertThat(condition)
                    .as(message)
                    .isTrue();
            LoggerUtils.info(AssertionUtils.class, "Assertion passed: " + message);
        } catch (Exception e) {
            LoggerUtils.error(AssertionUtils.class, "Assertion failed: " + message, e);
            throw e;
        }
    }
}
