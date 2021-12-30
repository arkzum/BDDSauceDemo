# BDDAutomationFramework

## Configuration related to automation design

This is a hybrid framework built using different component - Selenium with Java Binding , TestNG , Cucumber , Page Object Model , Singleton and factory design pattern with extent report

1. Property files in src/test/resources/ path
	- Feature file is maintained in features folder
	- RegressionTest xml file is maintained in testNGXML folder
	- Application url is maintained in GlobalSettings.properties file
	- Extent , cucumber reporting configs maintained in corresponding property files

2. Execution Mode and Browser parameters are maintained in TestNG XMLS. 

3. Design Framework hierarchy (multi level inheritance) is maintained as below

	FrameworkUtils --> pageLocators --> pageActions --> businessComponents --> stepDefinitions 

6. CukeHooks class is used to manage the @Before and @After Scenario functions related to cucumber scenario logic

7. TestNG listener is the core driving component used to control the flow of execution

8. WebDriverManager Used for picking the driver file automatically

9. Can be easily integrated with Jenkins / Azure or any pipeline using the maven command provided in step 1 after git clone (parameterising can also be done).

## Steps to execute the Test

1. From command line   : mvn clean test -PrunRegressionTest    ( make sure Java and maven are installed in the machine)

2. Using an IDE (Eclipse or Intellij )    : Run from the TestNG file in the path src/test/resources/TestNGXMLs/ (use RegressionTest.xml for regression tests and can be extended for smoke test by creating SmokeTest.xml and SmokeTestRunner.java file)

3. Parallel execution can be achieved by modifying the data-provider-thread-count value in the testing XML file 

## Reports and Screenshot

1. Extent HTML and PDF reports are configured and will be available in Result folder
2. HTML and JSON reports are configured and will be available in target folder







