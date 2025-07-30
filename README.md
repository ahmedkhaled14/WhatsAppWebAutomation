<div align="center">
  
# ✨ *WhatsApp Web Automation: Java & Selenium* ✨
## 💬 An End-to-End Test Automation Framework for WhatsApp Web 💬


<div align="center">
 <img  src="https://github.com/user-attachments/assets/cd897870-5f47-40d8-9778-25ad8e8b2042" alt="test-light" width="1000" height="250" />
 </div>

</div>

---

This repository houses a robust **Java-based test automation framework** designed for comprehensive interactions with WhatsApp Web. It's built upon industry-standard tools: **Selenium WebDriver** for browser automation, **TestNG** for powerful test orchestration, and **Maven** for efficient project management.

The framework meticulously follows the **Page Object Model (POM)** design pattern & Fluent design approach, ensuring high code reusability, maintainability, and readability. It's further enhanced with **Allure Reporting**, providing rich, interactive, and visually appealing test execution reports.

## 🚀 Key Technologies Used

* **Java:** The foundational programming language for the entire automation suite.
* **Selenium WebDriver:** The primary tool for automating browser interactions with WhatsApp Web.
* **TestNG:** A robust testing framework for structuring, executing.
* **Maven:** Used for dependency management, project compilation, and simplifying the build lifecycle.
* **Allure Report:** Integrated for generating comprehensive, interactive, and aesthetically pleasing test reports with detailed test steps.
* **JSON:** Leveraged for externalizing test data, enabling data-driven testing and flexible scenario management.
* **Properties Files:** Utilized for managing various configuration parameters (e.g., baseURL).

## ✨ Framework Features

* **Page Object Model (POM):** Ensures a clear separation between test logic and UI elements, enhancing maintainability and reusability.
* **Data-Driven Testing:** Test data is externalized in `JSON` files, allowing easy modification of test inputs without altering code.
* **Centralized Driver Management:** `DriverFactory` efficiently handles WebDriver instance initialization and teardown.
* **Reusable Actions & Validations:** Dedicated utility classes (`ElementActions`, `Validations`) encapsulate common UI interactions and assertion logic.
* **Rich Reporting with Allure:** Generates beautiful, interactive report with detailed information about each test run, including step-by-step execution.

## 🔍️ Covered Test Cases in this project:

This framework covers a range of essential WhatsApp Web functionalities, organized with Allure annotations for clear reporting:

#### ✅ Test Case: WhatsApp Search Feature (`testWhatsAppSearch`)
* **Story:** Search for a contact
* **Severity:** Critical
* **Description:** Given I am on WhatsApp Web, When I search for an existing contact, Then I should see the contact in the results.

#### ✅ Test Case: WhatsApp Message Chat Feature (`testWhatsAppSendMessageChat`)
* **Story:** Send a text message
* **Severity:** Blocker
* **Description:** Given I am chatting with a contact, When I send a text message, Then the message should be sent and visible in the chat.

#### ✅ Test Case: WhatsApp Emoji Chat Feature (`testWhatsAppSendEmojiChat`)
* **Story:** Send an emoji
* **Severity:** Normal
* **Description:** Given I am chatting with a contact, When I select and send an emoji, Then the emoji should be sent in the chat.

#### ✅ Test Case: WhatsApp Upload Image Feature (`testWhatsAppUploadImage`)
* **Story:** Send a media file
* **Severity:** Critical
* **Description:** Given I am chatting with a contact, When I upload and send an image, Then the image should be sent successfully.

## 🗃️ Documentation
* **[Selenium WebDriver Docs](https://www.selenium.dev/documentation/webdriver/)**
* **[Maven Docs](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)**
* **[Allure Framework Docs](https://allurereport.org/docs/)**

## 🚧 Requirements

* **Java Development Kit (JDK) 17**
* **Apache Maven 3.6.0 or higher**
* **IntelliJ IDEA / Eclipse** (for code development)

## 🚀 Running Tests

1.  **Clone the repository**
2.  **Configure `config.properties`:**
    Navigate to `src/main/resources/properties/config.properties`:
    * `baseUrl`: Keep as `https://web.whatsapp.com/`.

3.  **Update Test Data:**
    Review and update `src/test/resources/JsonTestData/whatsAppTestData.json` with the contacts, messages, and any other relevant data you wish to use for testing. Ensure `testImage.jpg` exists in `src/test/resources/testImages/`.

4. **Prepare Chrome User Profile for WhatsApp Web Login:**
    To avoid repeated QR code scans, this framework utilizes a pre-configured Chrome user profile. **You need to create and sign in to WhatsApp Web in this profile once manually.**
    * Open your Chrome browser.
    * Go to `chrome://version/` to find your "Profile Path" or "Profile Directory".
    * Navigate to the *parent* directory of your default Chrome profile. For example, if your default profile is `C:\Users\YourUser\AppData\Local\Google\Chrome\User Data\Default`, then the "User Data" folder is what you'll typically copy.
    * **Copy** the entire `User Data` folder (or just the `Default` profile folder within `User Data`) to a new, easily accessible location, for example, `C:\TestProfile`.
    * The code is configured to look for `C:\TestProfile`.
    * **Manual QR Scan:** Launch Chrome using this new profile (e.g., `C:\TestProfile`) and manually scan the QR code to log into WhatsApp Web. Once logged in, close the browser. This session data will be saved in `C:\TestProfile`.

    > **Why `C:\TestProfile`?**
    > The `DriverFactory.initializeDriver()` method uses `options.addArguments("user-data-dir=C:\\TestProfile");`. This argument tells Selenium to launch Chrome using the specified user profile directory. By pre-logging into WhatsApp Web in this profile, your automation runs will bypass the QR code scanning step.

5.  **Set Browser Language to English:**
    The `DriverFactory.initializeDriver()` also includes:
    ```java
    options.addArguments("--lang=en");
    options.addArguments("--accept-lang=en-US,en");
    ```
    > **Why these language arguments?**
    > These arguments are crucial for ensuring the WhatsApp Web interface always loads in English. Without them, if your system's default language is Arabic (or any other language), WhatsApp Web might automatically detect and display the interface in that language. This can cause issues for the automation script, as element locators (like text on buttons) might change. Forcing the language to English provides a consistent and predictable environment for the automation.

6.  **Update Test Data:**
    Review and update `src/test/resources/JsonTestData/whatsAppTestData.json` with the contacts, messages, and any other relevant data you wish to use for testing. Ensure `testImage.jpg` exists in `src/test/resources/testImages/`.


### Run All Tests (Command Line)

Open a terminal in the project root path:

1.  **Clean and build the project (and download dependencies):**
    ```bash
    mvn clean install
    ```
2.  **Run all tests using the TestNG XML suite:**
    ```bash
    mvn clean test
    ```
    This command will execute all tests defined in `testng.xml` and generate Allure results.

3.  **Generate and serve Allure Report:**
    After the tests complete, run the following command to open the interactive Allure report in your browser:
    ```bash
    allure serve
    ```
    *(Note: You might need to install Allure Commandline globally if `allure serve` command is not recognized. See Allure documentation for installation instructions.)*

### 📝 Allure Report Example:

<img width="1920" height="917" alt="WhatsappAllureReport" src="https://github.com/user-attachments/assets/3328a9a2-f73e-44d3-85d2-cb71abd69cdc" />

**Disclaimer:** This project is intended for **educational purposes, learning, and personal automation within ethical boundaries only.**
* **Use Responsibly:** Please use this tool ethically and in compliance with WhatsApp's Terms of Service.
* **Account Suspension Risk:** Excessive or inappropriate automated activity on WhatsApp Web can lead to temporary or permanent account suspension.
* The developers are **not responsible** for any misuse of this software or any consequences arising from its use, including but not limited to account suspension.
