package Appium.framework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Testcases extends capabilities {

	
	AndroidDriver<AndroidElement> driver;
	
	public AppiumDriverLocalService service;
	@BeforeTest()
	public void bt() throws IOException, InterruptedException{
		//driver =  capability();
		service=startServer();
		driver =  capability(); 
		
	}
	//m1 tc1
		@Test(priority= 1,dependsOnMethods ="searchhistory_1" )
		public void search_1() throws InterruptedException, IOException
		{
			Thread.sleep(5000);
			WebElement ele = driver.findElement(MobileBy.className("android.widget.EditText"));
					ele.sendKeys("a");
					ele.click();
					
					File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(screenshot,new File("C:\\Users\\Admin\\Desktop\\image\\11.png"));
			
		}
		//m1 tc2 add cart
		@Test(priority=2,dependsOnMethods = "search_1")
		public void search_2() throws InterruptedException, IOException
		{
			Thread.sleep(4000);
			
			 driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys("cheese");
			 Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@class='android.widget.Button' and @text ='Go']")).click();
			Thread.sleep(8000);
			//add to cart
			driver.findElement(By.xpath("//android.view.View[@content-desc=\"Kraft Real Grated Parmesan Cheese 227 Grams - Imported From Usa! 227 g (Pack of 1) 4.3 out of 5 stars 305\"]/android.widget.TextView[1]")).click();
			Thread.sleep(5000);
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"See All Buying Options\"))").click();
			Thread.sleep(12000);
			//driver.findElement(By.xpath("//android.view.View/android.widget.Button")).click();
			driver.findElement(By.xpath("//*[@text='Add to Cart from seller M.M.Enterprise. and price ₹700.00']")).click();
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,new File("C:\\Users\\Admin\\Desktop\\image\\12.png"));
			
			service.stop();
			
		}
		
		//m2 tc1
		@Test(priority = 0)
		public void searchhistory_1() throws InterruptedException, IOException
		{
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@text='Sign In ›']")).click();
			Thread.sleep(6000);
			//to enter email
			driver.findElement(MobileBy.className("android.widget.EditText")).click();
			WebElement a=driver.findElement(MobileBy.className("android.widget.EditText"));
			a.sendKeys("IndiaKarnataka18@gmail.com");
			Thread.sleep(3000);
			//clicks continue
			driver.findElement(By.xpath("//*[@text='Continue']")).click();
			Thread.sleep(6000);
			//enter pwd
		WebElement a1=driver.findElement(By.xpath("//*[@resource-id='ap_password']"));
		a1.sendKeys("Tom2jerry!");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@resource-id ='signInSubmit']")).click();
		Thread.sleep(6000);
		
		driver.findElement(By.xpath("//android.view.View[@content-desc='your account']/android.widget.TextView")).click();
	
		Thread.sleep(2000);
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Browsing History\"))").click();
		Thread.sleep(3000);
		driver.longPressKey(new KeyEvent(AndroidKey.BACK));
		
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,new File("C:\\Users\\Admin\\Desktop\\image\\13.png"));
		
		
		}
	
			
	}


