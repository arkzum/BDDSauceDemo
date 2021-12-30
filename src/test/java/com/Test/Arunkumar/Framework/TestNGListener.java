package com.Test.Arunkumar.Framework;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    TestParameters parameters = TestParameters.getInstance();

    @Override
    public void onStart(ITestContext context) {

    }
    @Override
    public void onTestStart(ITestResult result) {

        setDefaultTestParameters(result.getTestContext());
        DriverManager.getInstance().ScenarioContext();

        switch (parameters.getExecutionMode()) {

            case LOCAL:
                WebDriver driver = DriverFactory.getWebDriver(parameters.getBrowser());
                DriverManager.getInstance().setDriver(driver);
                break;

            case GRID:

                break;

            case MOBILE:

                break;

            default:
                System.out.println("************ Please provide the Correct Test Parameters ************");

        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    public void setDefaultTestParameters(ITestContext context) {
        ExecutionMode executionMode = ExecutionMode.valueOf(context.getCurrentXmlTest().getParameter("executionMode"));
        Browser browser = Browser.valueOf(context.getCurrentXmlTest().getParameter("browser"));
        parameters.setExecutionMode(executionMode);
        parameters.setBrowser(browser);
        DriverManager.getInstance().setParameters(parameters);
    }

}
