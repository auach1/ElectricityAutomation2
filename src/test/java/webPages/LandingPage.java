package webPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseFramework.BasePage;

public class LandingPage extends BasePage
{	
	@FindBy(xpath= "//a[@href='index.html']")
	private WebElement homeTab;
	
	private WebDriver driver;

	
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTexts()	{
		String textname =  homeTab.getText();
		return textname;
	}
	
	

}
