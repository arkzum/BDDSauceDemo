package com.Test.Arunkumar.Framework;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    /*    Singeleton Pattern to get only single instance of
            driver , test parameters and Scenario context through out the scenario execution
   */

    private DriverManager(){}
    private static DriverManager instance = new DriverManager();
    public static DriverManager getInstance(){
        return instance;
    }
    // Driver and test parameters initialisation as a Thread Local variable to handle multiple threads
    private static ThreadLocal<TestParameters> testParameters = new ThreadLocal<TestParameters>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    // driver and Test parameters getter and setter
    public void setDriver(WebDriver driver){ DriverManager.driver.set(driver);}
    public WebDriver getDriver() { return driver.get(); }
    public void closeDriver(){
        driver.get().close();
        driver.remove();
    }
    public void setParameters(TestParameters parameters){ DriverManager.getInstance().testParameters.set(parameters); }
    public TestParameters getTestParameters() { return DriverManager.getInstance().testParameters.get(); }

    //Scenario Context getter and setter
    private Map<String, Object>scenarioContext ;
    public void ScenarioContext(){
        scenarioContext = new HashMap<>();
    }
    public void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);

    }
    public Object getContext(Context key){
        return scenarioContext.get(key.toString());
    }

}
