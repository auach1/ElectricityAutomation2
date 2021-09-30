package utilities;

import org.openqa.selenium.WebDriver;

public class BasePage
{
	protected WaitHelper waitHelper;
	private WebDriver driver;
	
	protected BasePage(WebDriver driver)
	{
		this.driver = driver;
		waitHelper = new WaitHelper(driver);
	}

}
