package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import baseFramework.BasePage;
import helpers.webPageUtil;

public class GoSwiftPage extends webPageUtil {
	private WebDriver driver;
	private By swiftImg;

	public GoSwiftPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		swiftImg = By.xpath("//*[@href='https://investodisha.gov.in/goswift/']");
	}
	
	public void navigateTo() {
        clickOnElement();
    }

	public void clickOnElement() 
	{
		switchFrameToDefault();
		waitHelper.waitForElementToBeClickable(swiftImg);
		elementHelper.doClickUsingActions(swiftImg);
		waitHelper.waitForPageToLoad();
		
	}
	
	public String getTitle()
	{
		String title = JSUtil.getTitleByJS(driver);
		return title;
	}
	
	
}
