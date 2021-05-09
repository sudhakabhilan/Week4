package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContacts {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		//Enter Password
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		//Click Login
		driver.findElementByClassName("decorativeSubmit").click();
		
		//Assert Home Page
		String homepagetext = driver.findElementByTagName("h2").getText();
		System.out.println(homepagetext);
		
		Thread.sleep(2000);
		
		
		//Click Hyper Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		Thread.sleep(2000);
		//Click Contact Hyper Link
		driver.findElement(By.linkText("Leads")).click();
		Thread.sleep(2000);
		//Click Create Contact hyper link
		driver.findElement(By.linkText("Merge Leads")).click();
		
		Thread.sleep(2000);
		
		
		// From Lead 
		driver.findElementByXPath("//span[text()='From Lead']/following::img").click();
		Set <String> str = driver.getWindowHandles();
		List<String> listwindows = new ArrayList<String>(str);
		
		driver.switchTo().window(listwindows.get(1));
		
		driver.findElement(By.xpath("//input[contains(@class,'x-form-text x-form-field')]")).sendKeys("10024");
		driver.findElement(By.xpath("//button[@class='x-btn-text']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='linktext']")).click();
		//Switch to Primary Window
		
		driver.switchTo().window(listwindows.get(0));
		//To Lead
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='To Lead']/following::img").click();
		str = driver.getWindowHandles();
		listwindows = new ArrayList<String>(str);
		
		driver.switchTo().window(listwindows.get(1));
		
		
		
		driver.findElement(By.xpath("//input[contains(@class,'x-form-text x-form-field')]")).sendKeys("10025");
		driver.findElement(By.xpath("//button[@class='x-btn-text']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='linktext']")).click();
		
		driver.switchTo().window(listwindows.get(0));
		
		driver.findElement(By.linkText("Merge")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		String title = driver.getTitle();
		System.out.println(title);
	}

}
