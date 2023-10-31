package helpers;

import io.qameta.allure.Step;

/**
 * Класс Assert переопределяет стандартный класс Assert для удобного отчета
 */
public class Assert {
    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertFalse(boolean condition, String message) {
        org.testng.Assert.assertFalse(condition, message);
    }

    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertEquals(Object expected, Object actual, String message) {
        org.testng.Assert.assertEquals(expected, actual, message);
    }

    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertNotNull(Object actual, String message) {
        org.testng.Assert.assertNotNull(actual, message);
    }
}
