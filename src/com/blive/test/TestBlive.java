package com.blive.test;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


public class TestBlive {
	
	RemoteWebDriver driver;
	//public AndroidDriver<MobileElement> driver;
	//Dimension size;
	public static DesiredCapabilities capabilities;

//	@SuppressWarnings("rawtypes")
	@BeforeMethod
	@Parameters({"port","device"})
	 public void TestsetUp(String port, String device) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		 File app = new File("C:\\Users\\Admin\\Downloads", "barclaysliveandroid-qa.apk");
		 
		if(device.contains("iPhone"))
		{
		String xcodeConfigFile = "/Users/kevin/node_modules/webdriveragent/WebDriverAgent/Config.xcconfig";
		
		System.out.println("port="+port+":device="+device);
		 capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", device);
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("xcodeConfigFile",xcodeConfigFile);
		capabilities.setCapability("platformVersion", "10.2");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("udid", "fdjsbfjbsbk65774757fvjvvhjvhvhjvj");
		//capabilities.setCapability("browserName", "Safari");
		
		driver = new IOSDriver(new URL("http://192.168.0.104:"+port+"/wd/hub"), capabilities);
		
		}
		if(device.contains("eebfcc690004"))
		{
		
		System.out.println("port="+port+":device="+device);
		 capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("deviceName", device);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		 capabilities.setCapability("platformVersion", "7.0");
		  capabilities.setCapability("platformName", "Android");
		  capabilities.setCapability("appPackage", "com.barclays.live.qa");
		  System.out.println("Setcaps");
		
		driver = new RemoteWebDriver(new URL("http://192.168.0.104:"+port+"/wd/hub"), capabilities);
		  Thread.sleep(6000);
		 System.out.println("android driver new");
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		}
	

	@Test(priority = 1)
	@Parameters({"platformname"})
	public void searchPubs() throws Exception{
		Login();
		
		  System.out.println("Click on search icon");
		  Thread.sleep(6000);
		driver.findElement(By.id("com.barclays.live.qa:id/action_search")).click();
		
		
		  System.out.println("Send pubid");
		Thread.sleep(2000);
		//List<WebElement>  textFieldsList2 = driver.findElements(By.className("android.widget.EditText"));

		driver.findElement(By.id("com.barclays.live.qa:id/searchEditText")).sendKeys("pubId:2364344");
		// driver.(AndroidKeyCode.ENTER);
		//Press Enter key from Keyboard using any of the below methods
				//((AndroidDriver<MobileElement>) driver).pressKeyCode(66);
	//( driver).pressKeyCode(AndroidKeyCode.ENTER);
		Thread.sleep(1000);
		//driver.navigate().forward();
		Thread.sleep(5000);
		//textFieldsList2.get(2).sendKeys("pubId:2364344");
		//2364344/62
		
		 System.out.println("Open pubid");
		//Open the publication
		//List<WebElement>  textFieldsList3 = driver.findElements(By.className("android.widget.TextView"));
		
		driver.findElement(By.id("com.barclays.live.qa:id/listItemMagazineTitleTextView")).click();
		Thread.sleep(5000);
		
		String title= driver.findElement(By.id("android:id/action_bar_title")).getText();
	/*	String author;
		author=driver.findElement(By.className("android.view.View")).getText();
		*/
		System.out.println("Title is" +title);
	}
	

	public void Login() throws Exception
	  {
		  System.out.println("App launched");
		  Thread.sleep(2000);
		   if(driver.findElement(By.id("com.barclays.live.qa:id/employeeButton")).isEnabled()==false)
		  {
		 	 driver.findElement(By.id("com.barclays.live.qa:id/employeeButton")).click();
		  }
		  	  
		  driver.findElement(By.id("com.barclays.live.qa:id/usernameEditText")).click();
		  Thread.sleep(3000);
		 // textFieldsList.get(0).sendKeys("patijaih"); //Give your account username
		  List<WebElement>  textFieldsList = driver.findElements(By.className("android.widget.EditText"));
		  System.out.println("username"+textFieldsList.get(0) );
		  textFieldsList.get(0).sendKeys("patijaih"); //Give your account username
		  Thread.sleep(1000);
		  driver.findElement(By.id("com.barclays.live.qa:id/passwordText")).click();
		  textFieldsList.get(1).sendKeys("Mar_12345"); //Give your account password
		  driver.navigate().back();
		  Thread.sleep(2000);
		  driver.findElement(By.id("com.barclays.live.qa:id/signinButton")).click();
		  Thread.sleep(5000);
		 
		  

	  System.out.println("Login successfully");
	  Thread.sleep(1000);
	  
	    }
	 
	
	@AfterMethod
	 public void tearDown() {
			
		 driver.quit();
		 
	 }
	
	}
