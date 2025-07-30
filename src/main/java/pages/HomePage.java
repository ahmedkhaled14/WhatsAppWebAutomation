package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.dataReader.PropertiesReader;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static utils.elementActions.ElementActions.*;
import static utils.validations.Validations.assertTextEquals;

public class HomePage {
    WebDriver driver;
    PropertiesReader propertiesReader = new PropertiesReader("src/main/resources/properties/config.properties");

    private final String baseURL = propertiesReader.getProperty("baseURL");
    private final By searchInputLocator = By.xpath("//*[@aria-label='Search input textbox']");
    private final By messageInputLocator = By.xpath("//*[@aria-label='Type a message']");
    private final By emojiLocator = By.xpath("//*[@data-icon='expressions']");
    private final By happyEmojiLocator = By.xpath("(//span[@class='b82 emojik wa '])[1]");
    private final By sendButtonLocator = By.xpath("//*[@aria-label='Send']");
    private final By plusIconLocator = By.xpath("//span[@data-icon='plus-rounded']");
    private final By mediaIconLocator = By.xpath("//span[@data-icon='media-filled-refreshed']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to WhatsApp Web home page")
    public HomePage navigateToHomePage() {
        driver.get(baseURL);
        return this;
    }

    @Step("Search for contact by name: {contactName}")
    public HomePage searchForContactByName(String contactName) {
        type(driver, searchInputLocator, contactName + Keys.ENTER);
        return this;
    }

    @Step("Assert on the searched contact equals: {expectedText}")
    public HomePage assertOnTheSearchedContact(String expectedText) {
        assertTextEquals(driver, searchInputLocator, expectedText);
        return this;
    }

    @Step("Send message: {message}")
    public HomePage sendMessage(String message) {
        type(driver, messageInputLocator, message + generateCurrentDateAndTime() + Keys.ENTER);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Step("Assert that message sent successfully with text: {expectedText}")
    public HomePage assertThatMessageSentSuccessfully(String expectedText) {
        assertTextEquals(driver, getLastByXPath(driver, "copyable-text"), expectedText);
        return this;
    }

    @Step("Click on emoji button")
    public HomePage clickOnEmojiButton() {
        click(driver, emojiLocator);
        return this;
    }

    @Step("Select a happy emoji")
    public HomePage selectEmoji() {
        click(driver, happyEmojiLocator);
        return this;
    }

    @Step("Click on send button")
    public HomePage clickOnSendButton() {
        click(driver, sendButtonLocator);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Step("Upload image from path: {imagePath}")
    public HomePage uploadImage(String imagePath) throws AWTException, InterruptedException {
        click(driver, plusIconLocator);
        click(driver, mediaIconLocator);

        StringSelection filePath = new StringSelection(imagePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        Thread.sleep(1000);
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        pressEnter(driver);
        Thread.sleep(3000);
        return this;
    }
}
