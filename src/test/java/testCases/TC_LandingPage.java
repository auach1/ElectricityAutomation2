package testCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.AssertHelper;
import helpers.RelativePaths;
import helpers.TestConfig;
import tests.BaseTest;
import webPages.EnergyConservationPage;
import webPages.GoSwiftPage;
import webPages.LandingPage;


public class TC_LandingPage extends BaseTest implements RelativePaths 
{
	public TC_LandingPage()
	{
		super(); //Call the BaseTest constructor for AssertHelper
	}
  @BeforeMethod
  public void landingPage(Method method) throws IOException{ 
	  driver = TestConfig.getInstance();
	  assertHelper.assertTrue(getPage(LandingPage.class).getTexts().equalsIgnoreCase("Home"), "Validation Passed");
  }
  
  @Test(priority=1)
  public void energyConservationPageValidation(){
	  getPage(EnergyConservationPage.class).navigateTo();
	  assertHelper.assertTrue(getPage(EnergyConservationPage.class).getTexts().equalsIgnoreCase("Energy Conservation Tips"), "Validation Passed");
	  driver.navigate().back();
	  
  }  

}
