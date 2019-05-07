package helperClass;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import baseClass.BaseClass;

public class HelperClass extends BaseClass {


	public static String createScreenshot(WebDriver driver, String method_name) throws IOException {
		String imageLocation = "C://FHE-Finance//AutomationSelenium//OnlineShopping//src//test//java//Report//ScreenShots";
		String image_path = imageLocation + method_name + ".png";		
		// generate screenshot as a file object
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(image_path));
		return image_path;

	}
	
	public static void switchWindow() throws InterruptedException {

		// Storing parent window reference into a String Variable
		// Switching from parent window to child window
		for (String Child_Window : driver.getWindowHandles()) {
			driver.switchTo().window(Child_Window);
		}
		// // Switching back to Parent Window
		// driver.switchTo().window(Parent_Window);
		driver.manage().window().maximize();
		Thread.sleep(5000);

	}
}
