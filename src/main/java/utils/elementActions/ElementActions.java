package utils.elementActions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ElementActions {

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void click(WebDriver driver, By locator) {
        waitForVisibility(driver, locator).click();
    }

    public static void type(WebDriver driver, By locator, String text) {
        WebElement element = waitForVisibility(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void selectByVisibleText(WebDriver driver, By locator, String visibleText) {
        Select dropdown = new Select(waitForVisibility(driver, locator));
        dropdown.selectByVisibleText(visibleText);
    }

    public static void selectByValue(WebDriver driver, By locator, String value) {
        Select dropdown = new Select(waitForVisibility(driver, locator));
        dropdown.selectByValue(value);
    }

    public static void selectByIndex(WebDriver driver, By locator, int index) {
        Select dropdown = new Select(waitForVisibility(driver, locator));
        dropdown.selectByIndex(index);
    }
    public static void selectByText(WebDriver driver, By locator, String text) {
        Select dropdown = new Select(waitForVisibility(driver, locator));
        dropdown.selectByVisibleText(text);
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = waitForVisibility(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    public static String getText(WebDriver driver, By locator) {
        return waitForVisibility(driver, locator).getText();
    }

    public static String generateCurrentDateAndTime() {
        return new SimpleDateFormat("ddMMMMyyyy-HH:mm:ss:SSS").format(new Date());
    }

    public static By getLastByXPath(WebDriver driver, String className) {
        int size = driver.findElements(By.xpath("//*[@class='" + className + "']")).size();
        if (size == 0) {
            throw new NoSuchElementException("No elements found with class: " + className);
        }
        return By.xpath("(//*[@class='" + className + "'])[" + size + "]");
    }

    public static void pressEnter(WebDriver driver) {
        new Actions(driver)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public static void staticWait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
