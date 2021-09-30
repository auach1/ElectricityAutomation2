package helpers;

import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentTest;

import baseFramework.BaseFramework;

public class AssertHelper extends BaseFramework {
	
	/**
     * Assert equals.
     *
     * @param actual  first evaluated expression variable.
     * @param expected second evaluated expression variable.
     * @param message        Message to be passed.
     * @param <T>            generic data type.
     */
    public <T> void assertEquals(T actual, T expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult("Validation Failed");
            throw e;
        }
        updatePassResult(message);
    }
    
    /**
     * Log Pass result.
     *
     * @param message Message to be logged.
     */
    private void updatePassResult(String message) {
        var successMessage = "<div style=\"color:green;\">" + message + " : <b>Success</b></div></div>";
        System.out.println(successMessage);
        //logger().pass(successMessage);
        
    }

    /**
     * Log Fail result.
     *
     * @param message Message to be logged.
     */
    private void updateFailResult(String message) {
        String failureMessage = "<div style=\"color:red;\">" + message + " : <b>Failure</b></div></div>";
        System.out.println(failureMessage);
        //logger().fail(failureMessage);
        
    }
    
    public void assertSame(String actual, String expected, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertSame(actual, expected, message);
        } catch (AssertionError e) {
            updateFailResult("Validation Failed");
            throw e;
        }
        updatePassResult(message);
    }
    
    
    /**
     * Assert true with test case id.
     *
     * @param variable   evaluated expression variable.
     * @param message    Message to be passed.
     * @param testCaseId Test case Ids to update.
     */
    public void assertTrue(boolean variable, String message) {
        Assertion assertion = new Assertion();
        try {
            assertion.assertTrue(variable, message);
        } catch (AssertionError e) {
            updateFailResult(message);
            throw e;
        }
        updatePassResult(message);
    }

    }


