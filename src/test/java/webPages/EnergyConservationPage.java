package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import baseFramework.BasePage;

public class EnergyConservationPage extends BasePage {
	
	private WebDriver driver;
	private By energyImg;
	private By contentsHeader;

	public EnergyConservationPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		energyImg = By.xpath("//*[@src='Images/images_latest/Energy coservation 1-01.png']");
		contentsHeader = By.xpath("//*[@id='contentsheader2']");
	}
	
	public void navigateTo() {
        clickOnElement();
    }

	public void clickOnElement() 
	{
		switchFrameToDefault();
		waitHelper.waitForElementToBeClickable(energyImg);
		elementHelper.doClickUsingActions(energyImg);
		waitHelper.waitForPageToLoad();
		
	}
	
	public String getTexts()	{
		String textname =  findAnElement(contentsHeader).getText();
		return textname;
	}
	
	private WebElement findAnElement(By locator) {
		return driver.findElement(locator);
	}

	public boolean switchFrameToDefault() {
        try {
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            
            return false;
        }
    


}
}
