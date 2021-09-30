package baseFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class ElementHelper extends WaitHelper {
	private WebDriver driver;
	private Actions actions;

	public ElementHelper(WebDriver driver) {
		super(driver);
		this.driver = driver;
		actions = new Actions(driver);
	}
	
	 public WebElement getElement(By locator) {
	        waitForElementPresent(locator);
	        WebElement element;
	        element = driver.findElement((By) locator);
	        return element;
	    }
	
	 public void doClickUsingActions(By locator) {
	        try {
	            var element = getElement(locator);
	            actions.click(element).perform();
	        } catch (Exception e) {
	            System.out.println("Locator not found : " + locator);
	            System.out.println("Some exception occurred while clicking on webelement " + e);
	            throw e;
	        }
	    }

}
