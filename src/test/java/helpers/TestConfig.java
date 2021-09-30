package helpers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import baseFramework.BaseFramework;

public class TestConfig extends BaseFramework implements RelativePaths {
	WebDriver driver = null;

	@SuppressWarnings("deprecation")
	public static WebDriver getInstance() {
		WebDriver driver = null;

		String browserType = DataHandlers.getDataFromPropertyFile("browser");
		String url = DataHandlers.getDataFromPropertyFile("url");

		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty(chromeProperties, chromePath);
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("edge")) {
			System.setProperty(edgeProperties, edgePath);
			driver = new EdgeDriver();

		} else {
			System.out.println("Invalid Browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			HttpsURLConnection conn = (HttpsURLConnection) (new URL(url)).openConnection();
			conn.setUseCaches(false);
			conn.connect();
			int status = conn.getResponseCode();

			if (status == 200) {
				driver.get(url);
				try {
					String parent = driver.getWindowHandle();
					Set<String> windows = driver.getWindowHandles();
					Iterator<String> it = windows.iterator();
					
					while (it.hasNext()) 
					{
						String child_window = it.next();
						if (!parent.equals(child_window)) {
							driver.switchTo().window(child_window);
							driver.close();
						}

					}
					driver.switchTo().window(parent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			else 
			{
				driver.get(url);
				System.out.println("Not getting 200 response from server");
				//logger().info("Not getting 200 response from server");
				JOptionPane.showMessageDialog(null, "Not getting 200 response from server", "Server Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.getRootFrame().dispose();
				driver.quit();
			}
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();

		}

		return driver;
	}

}
