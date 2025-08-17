package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.dataReader.JsonFileManager;
import utils.driverFactory.DriverFactory;

import java.awt.*;

@Epic("WhatsApp Web Automation")
@Feature("End-to-End WhatsApp Features")
@Owner("Ahmed Khaled")
public class WhatsAppTest {

    WebDriver driver;
    JsonFileManager testData = new JsonFileManager("src/test/resources/JsonTestData/whatsAppTestData.json");
    String rawPath = System.getProperty("user.dir") + "/src/test/resources/testImages/testImage.jpg";
    String escapedPath = rawPath.replace("/", "\\");

    @BeforeClass(description = "setup")
    public void setUp() {
        driver = DriverFactory.initializeDriver();
    }

    @Test(description = "Test WhatsApp search feature")
    @Story("Search for a contact")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Given I am on WhatsApp Web\nWhen I search for an existing contact\nThen I should see the contact in the results")
    public void testWhatsAppSearch() {
        new HomePage(driver)
                .navigateToHomePage()
                .searchForContactByName(testData.getTestData("contactName"))
                .assertOnTheSearchedContact(testData.getTestData("contactName"));
    }

    @Test(description = "Test WhatsApp message chat feature")
    @Story("Send a text message")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Given I am chatting with a contact\nWhen I send a text message\nThen the message should be sent and visible in the chat")
    public void testWhatsAppSendMessageChat() {
        new HomePage(driver)
                .navigateToHomePage()
                .searchForContactByName(testData.getTestData("contactName"))
                .sendMessage(testData.getTestData("message"))
                .assertThatMessageSentSuccessfully(testData.getTestData("message"));
    }

    @Test(description = "Test WhatsApp emoji chat feature")
    @Story("Send an emoji")
    @Severity(SeverityLevel.NORMAL)
    @Description("Given I am chatting with a contact\nWhen I select and send an emoji\nThen the emoji should be sent in the chat")
    public void testWhatsAppSendEmojiChat() {
        new HomePage(driver)
                .navigateToHomePage()
                .searchForContactByName(testData.getTestData("contactName"))
                .clickOnEmojiButton()
                .selectEmoji()
                .clickOnSendButton();
    }

    @Test(description = "Test WhatsApp upload image feature")
    @Story("Send a media file")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Given I am chatting with a contact\nWhen I upload and send an image\nThen the image should be sent successfully")
    public void testWhatsAppUploadImage() throws AWTException {
        new HomePage(driver)
                .navigateToHomePage()
                .searchForContactByName(testData.getTestData("contactName"))
                .uploadImage(escapedPath);
    }

    @AfterClass(description = "tear down")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
