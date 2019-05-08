package mainClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import baseClass.BaseClass;
import objectRepo.LoginPageObjects;

public class LoginPage extends BaseClass {

	public static String uname="ajitbhavle93@gmail.com";
	public static String pass="ajit@1993";
	
	
		@Test
	    public void Login() throws InterruptedException{
	    	
		 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 	LoginPageObjects.userName(driver).sendKeys(uname);
		 	LoginPageObjects.password(driver).sendKeys(pass);
		 	//LoginPageObjects.password(driver).sendKeys(pass);
		 	LoginPageObjects.password(driver).sendKeys(pass);
		 	wait= new WebDriverWait(driver,10);
		 	wait.until(ExpectedConditions.elementToBeClickable(LoginPageObjects.loginBtn(driver))).click();
		 	
		 	wait.until(ExpectedConditions.elementToBeClickable(LoginPageObjects.titleClassName(driver)));
		 	
		 	if(LoginPageObjects.titleClassName(driver).isDisplayed())
		 		logger.log(LogStatus.PASS, "User Login SuccessFully");
		 	else
		 		logger.log(LogStatus.FAIL, "Failed to login");
	        //Assert.assertTrue(false);
	        //System.out.println("This is a TestNG-Maven based test");
	        
	    }
		@Test
	    public void LoginVerification() throws InterruptedException  {
	    	
		 			 	
		 	if(LoginPageObjects.titleClassName(driver).isDisplayed())
		 		logger.log(LogStatus.PASS, "Title Displayed");
		 	else
		 		logger.log(LogStatus.FAIL, "Failed to login");
	        //Assert.assertTrue(false);
	        //System.out.println("This is a TestNG-Maven based test");
	        
	    }
}

