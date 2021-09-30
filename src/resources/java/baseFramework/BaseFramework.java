package baseFramework;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public abstract class BaseFramework {
	protected static ExtentReports report;
	protected static int newInstancesCount = 0;
	@SuppressWarnings("rawtypes")
	private static HashMap<String, HashMap<WebDriver, Object>> instanceMap = new HashMap<>();
	private static Map<Integer, ExtentTest> extentNodeMap = new HashMap<>();
	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	protected ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	
	@SuppressWarnings("unchecked")
	public synchronized <TPage extends BasePage> TPage GetInstance(Class<TPage> page, WebDriver driver) {
        try {
            if (driver == null) {
                System.out.println("null");
                return null;
            }
            Object instanceToReturn;
            if (instanceMap.containsKey(page.getName())) {
                if (instanceMap.get(page.getName()).containsKey(driver)) {
                    instanceToReturn = instanceMap.get(page.getName()).get(driver);
                } else {
                    newInstancesCount++;
                    instanceToReturn = page.getDeclaredConstructor(WebDriver.class).newInstance(driver);
                    instanceMap.get(page.getName()).put(driver, instanceToReturn);
                }
            } else {
                newInstancesCount++;
                instanceToReturn = page.getDeclaredConstructor(WebDriver.class).newInstance(driver);
                HashMap<WebDriver, Object> driverMap = new HashMap<>();
                driverMap.put(driver, instanceToReturn);
                instanceMap.put(page.getName(), driverMap);
            }
            return (TPage) instanceToReturn;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/*
	 * public static synchronized ExtentTest logger() { return
	 * extentNodeMap.get((int) Thread.currentThread().getId()); }
	 * 
	 * public static synchronized ExtentTest startTest(String className, String
	 * description) { ExtentTest test = report.createTest(className, description);
	 * extentTestMap.put((int) (Thread.currentThread().getId()), test); return test;
	 * }
	 * 
	 * public static synchronized ExtentTest addNode(String testName, String
	 * description) { var test = extentTestMap.get((int)
	 * Thread.currentThread().getId()).createNode(testName, description);
	 * extentNodeMap.put((int) (Thread.currentThread().getId()), test); return test;
	 * }
	 * 
	 * public static synchronized boolean isTestAlreadyLogged(String testName) {
	 * return extentNodeMap.values().stream().anyMatch(x ->
	 * x.getModel().getName().toLowerCase().contains(testName.toLowerCase())); }
	 * 
	 * public synchronized void deleteInstances(WebDriver driver) {
	 * instanceMap.forEach((key, value) -> value.remove(driver)); }
	 * 
	 * public synchronized int getPomInstanceInScopeCount() { return
	 * instanceMap.values().stream().mapToInt(driverObjectMap ->
	 * driverObjectMap.entrySet().size()).sum(); }
	 */

    }
