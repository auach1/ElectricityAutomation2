package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aurigo.masterworks.testframework.utilities.ExceptionHandler;
import com.aurigo.masterworks.testframework.utilities.JavaScriptUtil;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;

public class WaitHelper 
{

	private static final int timeOutIfWaitScenarioFinished = 2;
	private static int waitTimeOut = 60;
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WaitHelper(WebDriver driver)
	{
		this.driver = driver;
		try
		{
			wait = new WebDriverWait(driver, waitTimeOut);
		}
		catch(Exception e)
		{
			wait = new WebDriverWait(driver, waitTimeOut);
		}
	}
	
    /**
     * Waits for Element to be present.
     *
     * @param locator By Locator of element.
     */
	public void waitForElementToBePresent(By locator)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
     * Waits for Element to be present.
     *
     * @param element expected Web element.
     */
    public void waitForElementPresent(WebElement element) 
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * wait for element to be enabled
     *
     * @param locator the element locator to be enabled
     */
    public void waitForElementToBeEnabled(final By locator)
    {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(waitTimeOut));
        fluentWait.until(x -> {return driver.findElement(locator).isEnabled();});
    }
    
    public void waitForPageToLoad(By locator) 
    {
        waitForPageToLoad();
    }
    
    public void waitForPageToLoad() {
        String pageStatusScript = "return document.readyState";
        try {
            var incomingFrame = JavaScriptUtil.getCurrentFrame(driver);
            //This is required to make sure that this method never switches from the frame the call was made from
            if (incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
                driver.switchTo().defaultContent();
            }
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(waitTimeOut));

            //Waiting for the whole page to load. In a perfect world; Selenium internally waits for the page to load but we are not living in a perfect world, so...
            fluentWait.until(ExpectedConditions.jsReturnsValue(pageStatusScript).andThen(x -> x.equals("complete")));

//            //Then we wait for the infamous loading banner to do its thing
//            waitForLoadingBannerDisappears();

            //Then the content frame, which should ideally be loaded along with the loading banner. But then Murphy's law exists for a reason! So we wait!
            fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(String.format(".//iframe[@name='%s']", Constants.MAIN_CONTENT_FRAME_NAME))));

            if (!incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
                driver.switchTo().defaultContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}}
