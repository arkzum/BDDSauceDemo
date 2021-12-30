package com.Test.Arunkumar.TestNGRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features" , glue = "com.Test.Arunkumar.StepDefinitions" , monochrome = true,
        tags = (" @Smoke"), plugin = {"pretty","json:target/JSONReports/report.json",
        		"html:target/HtmlReports/report.html",
        		"junit:target/JSONReports/reports.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class RegressionTestngRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
