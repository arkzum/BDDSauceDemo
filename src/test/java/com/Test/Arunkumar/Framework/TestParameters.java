package com.Test.Arunkumar.Framework;

import io.cucumber.java.Scenario;

public class TestParameters {

    private TestParameters(){}
    private static TestParameters instance = new TestParameters();
    public static TestParameters getInstance(){
        return instance;
    }

    private ExecutionMode executionMode;
    private Browser browser;
    private Scenario scenario;

    public ExecutionMode getExecutionMode() { return this.executionMode; }
    public Browser getBrowser() { return this.browser; }
    public Scenario getScenario(){ return this.scenario; }
    public void setExecutionMode(ExecutionMode executionMode) { this.executionMode = executionMode;}
    public void setBrowser(Browser browser) { this.browser = browser;}
    public void setScenario(Scenario scenario){this.scenario = scenario;}

}
