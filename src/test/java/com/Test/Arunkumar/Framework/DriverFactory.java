package com.Test.Arunkumar.Framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getWebDriver(Browser browser){

        WebDriver driver = null;
        switch(browser){

            case CHROME:
                WebDriverManager.chromedriver().setup(); // method to get the webdriver dynamically
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--silent");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            case CHROME_HEADLESS:
                WebDriverManager.chromedriver().setup(); // method to get the webdriver dynamically
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--headless");
                driver = new ChromeDriver(chOptions);
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                driver = null;
        }

        return driver;
    }
}
