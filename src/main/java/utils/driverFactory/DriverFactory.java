package utils.driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initializeDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir=C:\\TestProfile");
            options.addArguments("--lang=en");
            options.addArguments("--accept-lang=en-US,en");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
