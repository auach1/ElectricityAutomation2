package baseFramework;

import org.openqa.selenium.WebDriver;



public class BasePage extends BaseFramework {

	protected ElementHelper elementHelper;
    protected WaitHelper waitHelper;
    protected JavaScriptUtil JSUtil;
    private WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        elementHelper = new ElementHelper(driver);
    }
    
    public <TPage extends BasePage> TPage getPage(Class<TPage> page) {
        return GetInstance(page, driver);
    }
}
