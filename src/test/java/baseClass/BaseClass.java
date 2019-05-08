package baseClass;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helperClass.HelperClass;

public class BaseClass {
	
public String s="ajit";
public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static WebDriverWait wait;
	@BeforeSuite
    public void openBrowser() 
    {
    	
    	//DesiredCapabilities capabilities=DesiredCapabilities.firefox();
    	//capabilities.setCapability("marionette", true);
		//System.setProperty("webdriver.gecko.driver","C:\\FHE Team A\\SeleniumAutomation\\java\\geckodriver.exe");

		System.setProperty("webdriver.gecko.driver","C:\\FHE Team A\\SeleniumAutomation\\java\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.myntra.com/login");

    }
	
	@BeforeTest
	public void startReport(){
		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		extent = new ExtentReports("C://Users//P10434713//eclipse-workspace//Demo//src//test//java//Demo.html");
		//extent.addSystemInfo("Environment","Environment Name")
		extent
                .addSystemInfo("Host Name", "Demo")
                .addSystemInfo("Environment", "Demo Automation Testing")
                .addSystemInfo("User Name", "Ajit Bhavle");
                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		      
		      extent.loadConfig(new File("C://Users//P10434713//eclipse-workspace//Demo//src//test//java//extent-config.xml"));
		      
	}
	@BeforeMethod
	public void testLogger(Method method)
	{
		logger=extent.startTest(this.getClass().getSimpleName()+ " :: " +method.getName());
		logger.assignAuthor("Ajit Bhavle");
		logger.assignCategory("Smoke Test");
					
	}
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			String imagePath = HelperClass.createScreenshot(driver, "method_name");
			logger.log(LogStatus.FAIL, "ScreenShots Attached is : " +logger.addScreenCapture(imagePath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
			throw new SkipException("Skipping this test-method with exception");
		}else{
			logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
		
	}
		
	@AfterTest
	public void endReport(){
		// writing everything to document
		//flush() - to write or update test information to your report. 
                extent.flush();
                //Call close() at the very end of your session to clear all resources. 
                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
                //Once this method is called, calling any Extent method will throw an error.
                //close() - To close all the operation
                extent.close();
    }
	
	
	 @AfterSuite
	  public void afterClass() {
	     driver.close();
	  }

	
}
