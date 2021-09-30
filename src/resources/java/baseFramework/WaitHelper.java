package baseFramework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseFramework.BaseFramework;


public class WaitHelper extends BaseFramework
{

	private static final int timeOutIfWaitScenarioFinished = 2;
	private static int waitTimeout = 60;
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WaitHelper(WebDriver driver){
		this.driver = driver;
		try
		{
			wait = new WebDriverWait(driver, waitTimeout);
		}
		catch(Exception e)
		{
			wait = new WebDriverWait(driver, waitTimeout);
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
    public void waitForElementToBeEnabled(By locator)
    {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(waitTimeout));
        fluentWait.until(x -> {return driver.findElement(locator).isEnabled();});
    }
    
    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public void waitForElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public void waitForPageToLoad() {
        String pageStatusScript = "return document.readyState";
        try {
            var incomingFrame = JavaScriptUtil.getCurrentFrame(driver);
            //This is required to make sure that this method never switches from the frame the call was made from
//            if (incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
//                driver.switchTo().defaultContent();
//            }
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(waitTimeout));

            //Waiting for the whole page to load. In a perfect world; Selenium internally waits for the page to load but we are not living in a perfect world, so...
            fluentWait.until(ExpectedConditions.jsReturnsValue(pageStatusScript).andThen(x -> x.equals("complete")));


            //Then the content frame, which should ideally be loaded along with the loading banner. But then Murphy's law exists for a reason! So we wait!
//            fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(String.format(".//iframe[@name='%s']", Constants.MAIN_CONTENT_FRAME_NAME))));

//            if (!incomingFrame.equals(Constants.MAIN_CONTENT_FRAME_NAME)) {
//                driver.switchTo().defaultContent();
//            }
        } catch (Exception ex) {
            System.out.println(ex);
            
        }
    }

    
}
