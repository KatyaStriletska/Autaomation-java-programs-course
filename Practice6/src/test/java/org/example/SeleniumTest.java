package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyHeader() {
        driver.get("https://www.netflix.com/ua-en/");
        WebElement element = driver.findElement(By.xpath("//h1[contains(text(), 'Unlimited movies, TV shows, and more')]"));
        assertEquals(element.getText(), "Unlimited movies, TV shows, and more");
    }
    @Test
    public void verifyLoginButtonExists() {
        driver.get("https://www.netflix.com/ua-en/");
        WebElement loginButton = driver.findElement(By.xpath("//a[contains(text(), 'Sign In')]"));
        assertTrue(loginButton.isDisplayed(), "Login button is not displayed");
        loginButton.click();
        String expectedTitle = "Netflix";
        assertEquals(expectedTitle, driver.getTitle(), "Didn't navigate to login page");
    }

    @Test
    public void checkLogInValidation(){
        driver.get("https://www.netflix.com/ua-en/");
        WebElement loginButton = driver.findElement(By.id("signIn"));
        loginButton.click();
        WebElement signInButton = driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]"));
        signInButton.click();
        String [] expectedErrorMessages = {"Please enter a valid email or phone number.", "Your password must contain between 4 and 60 characters."};
        List<WebElement> errorMessageElement = driver.findElements(By.xpath("//div[@class='default-ltr-cache-0 ea3diy30']"));
        String[] actualErrorMessages = new String[expectedErrorMessages.length];
        for (int i = 0; i < expectedErrorMessages.length; i++) {
            actualErrorMessages[i] = errorMessageElement.get(i).getText().trim();
        }
        assertArrayEquals(expectedErrorMessages, actualErrorMessages);


    }


}
