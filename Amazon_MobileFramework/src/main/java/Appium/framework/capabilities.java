package Appium.framework;

	import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

	import io.appium.java_client.android.AndroidDriver;
	import io.appium.java_client.android.AndroidElement;
	import io.appium.java_client.remote.AndroidMobileCapabilityType;
	import io.appium.java_client.remote.AutomationName;
	import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

	public class capabilities {

		public static String devicename,platformname,APPPACKAGE,APPACTIVITY;
		
		public AppiumDriverLocalService service;
		public AppiumDriverLocalService startServer()
		{
			boolean flag = chechifserverisRunning(4723);
			if(!flag)
			{
				
			
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
					.withAppiumJS(new File("C://Users//Admin//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
					.withIPAddress("127.0.0.1").usingPort(4723));
			service.start();
			
		}
		return service;
		}
		public static boolean chechifserverisRunning(int port)
		{
			boolean isServerRunning=false;
			ServerSocket serversocket;
			try{
				serversocket = new ServerSocket(port);
				serversocket.close();
			}
			catch(IOException e)
			{
				isServerRunning = true;
			}
			finally {
				serversocket=null;
			}
			return isServerRunning;
		}
	/*	
		public static void StartEmulator() throws IOException, InterruptedException
		{
			Runtime.getRuntime().exec("C:\\Users\\Admin\\eclipse-workspace\\Mobile_Framework\\src\\main\\resources\\emulator.bat");
			Thread.sleep(30000);
		}
		*/
		public static AndroidDriver<AndroidElement> capability() throws IOException, InterruptedException {
			
			FileReader fis = new FileReader("C:\\Users\\Admin\\eclipse-workspace\\Amazon_MobileFramework\\src\\main\\java\\Appium\\framework\\Global.properties");
			Properties pro = new Properties();
			pro.load(fis);
			devicename = pro.getProperty("devicename");
			platformname = pro.getProperty("platformname");
			APPPACKAGE = pro.getProperty("APPPACKAGE");			
			APPACTIVITY = pro.getProperty("APPACTIVITY");
/*	
if(devicename.contains("SM M013F"))
{
	StartEmulator();
	
}
*/
			
			
			//i want to check the capabilities of my remote device im not running on desktop web im running on mobile web
			
			DesiredCapabilities cap = new DesiredCapabilities();
			//name of emulator//is my device name //this is mandatory
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
			//this is alos mandatory//where v talk abt the platform whch v r testing
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformname);
			cap.setCapability(MobileCapabilityType.NO_RESET,true);
			//automation name is not mandatory for android but its mandatory for ios
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "C:\\Users\\Admin\\Downloads\\chromedriver.exe");
			cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,APPPACKAGE);
			cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,APPACTIVITY);
			
			

			//AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(cap);
			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			//i dont want to write this capability again again for all classes so i want to keep this commom and import in otherclasses
			return driver;
		
		
		}


}
