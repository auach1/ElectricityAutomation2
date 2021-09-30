package testCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import helpers.RelativePaths;
import helpers.TestConfig;
import tests.BaseTest;
import webPages.GoSwiftPage;
import webPages.LandingPage;
import static extentReports.ExtentTestManager.startTest;

public class TC_GoSwiftPage extends BaseTest implements RelativePaths 
{
	static ExtentReports extent;
	static ExtentTest test;
	
	public TC_GoSwiftPage()
	{
		super(); //Call the BaseTest constructor for AssertHelper
	}
	
	/*
	 * @BeforeSuite
	 * 
	 * public void config() { //ExtentReports, ExtentSparkReports String reportPath
	 * String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
	 * ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	 * reporter.config().setReportName("Web Automation Results");
	 * reporter.config().setDocumentTitle("Test Results");
	 * 
	 * extent = new ExtentReports(); extent.attachReporter(reporter);
	 * extent.setSystemInfo("Team Member", "Aurovind Acharya");
	 * extent.createTest("Smoke Test"); }
	 */
	
	
	
	@BeforeMethod
	  public void landingPage(Method method) throws IOException{
		  driver = TestConfig.getInstance();
		  assertHelper.assertTrue(getPage(LandingPage.class).getTexts().equalsIgnoreCase("Home"), "Validation Passed");
		  
	}
	
	@Test
	public void goSwiftPageValidation(Method method){
		startTest(method.getName(), "Invalid Login Scenario with invalid username and password.");

		  getPage(GoSwiftPage.class).navigateTo();
		  assertHelper.assertTrue(getPage(GoSwiftPage.class).getTitle().equalsIgnoreCase("TP Southern Odisha Distribution Limited (TPSODL)"), "Validation Passed");
	}
	
//	@AfterSuite
//		public void closeAll() {
//		extent.flush();
//		driver.quit();
//	}

}
