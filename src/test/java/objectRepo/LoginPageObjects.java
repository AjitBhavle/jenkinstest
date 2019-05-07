package objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import baseClass.BaseClass;

public class LoginPageObjects extends BaseClass {
	
	//public static String Base_Url = "https://www.myntra.com/login";
	
	
	public static WebElement userName(WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.name("email"));
		return element;
	}
	public static WebElement password(WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.name("password"));
		return element;
	}
	public static WebElement loginBtn(WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.className("login-login-button"));
		return element;
	}
	public static WebElement titleClassName(WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.className("desktop-logoContainer"));
		return element;
	}
}
