package org.evy.toolkit.utils;

import java.util.concurrent.Callable;

/**
 * Utility class to perform actions with retry capability.
 */
public final class ActionUtils {

    private ActionUtils() {}


    public static <T> T performAction(Class<?> cls, Callable<T> callable, String successMsg, String errorMsg) {
        return performRetry(cls, () -> {
            T result = callable.call();
            LoggerUtils.info(cls, successMsg);
            return result;
        }, errorMsg);
    }

    public static void performAction(Class<?> cls, Runnable runnable, String successMsg, String errorMsg) {
        performRetry(cls, () -> {
            runnable.run();
            LoggerUtils.info(cls, successMsg);
            return null;
        }, errorMsg);
    }


    private static  <T> T performRetry(Class<?> cls, Callable<T> action, String errorMsg) {
        int max=3;
        for (int attempt = 0; attempt < max; attempt++) {
            try {
                return action.call();
            } catch (Exception e) {
                if (attempt == max-1) {
                    LoggerUtils.error(cls, errorMsg, e);
                    throw new RuntimeException(errorMsg, e);
                }
                LoggerUtils.warn(cls, String.format("Attempt %d failed, retrying...", attempt + 1));
            }
        }
        throw new RuntimeException("Failed to perform action after retries");
    }
}
