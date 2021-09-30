package tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.util.Strings;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;




import baseFramework.BaseFramework;
import baseFramework.BasePage;
import helpers.AssertHelper;
import logs.Log;

public class BaseTest extends BaseFramework 
{
	public static AssertHelper assertHelper;
	public WebDriver driver;
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	public BaseTest() {
		assertHelper = new AssertHelper();
	}
	
	@BeforeClass
	public WebDriver getDriver() {
        return driver;
    }
	
	@AfterClass
    public void teardown() {
        Log.info("Tests are ending!");
        driver.quit();
    }
	 
	public <TPage extends BasePage> TPage getPage(Class<TPage> page) {
	        return GetInstance(page, driver);
	    }

}
