package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseFramework.BasePage;

public class webPageUtil extends BasePage {
	private WebDriver driver;

	public webPageUtil(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	private WebElement findAnElement(By locator) {
		return driver.findElement(locator);
	}

	public boolean switchFrameToDefault() {
        try {
            driver.switchTo().defaultContent();
            return true;
        } 
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
	}
	
	public String getTexts() {
		By text = null;
		String textname =  findAnElement(text).getText();
		return textname;
	}
	
	public String getURL() {
		String currentURL =  driver.getCurrentUrl();
		return currentURL;
		
	}

}
