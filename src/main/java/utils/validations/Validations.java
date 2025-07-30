package utils.validations;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utils.elementActions.ElementActions.waitForVisibility;

public class Validations {

    public static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            return waitForVisibility(driver, locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean assertTextEquals(WebDriver driver, By locator, String expectedText) {
        try {
            WebElement element = waitForVisibility(driver, locator);
            String actualText = element.getText().trim();
            return actualText.contains(expectedText.trim());
        } catch (TimeoutException e) {
            return false;
        }
    }
}
