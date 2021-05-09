package week4day2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewIncident {

	public static void main(String[] args) throws InterruptedException {
		

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		//1. Launch ServiceNow application
		driver.get("https://dev103117.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		  	// find the frame 
		driver.switchTo().frame("gsft_main");
			// Enter Login Details and click login
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		String title = driver.getTitle();

		if(title.contains("ServiceNow")) 
			
		{
			System.out.println("Welcome to Homepage of" + title);
		}else 
		{
		System.out.println("This is not the Homepage");	
		}
		
		
			//Create Explicit Wait for filter section to appear
		WebElement filter = driver.findElement(By.name("filter"));
		
		WebDriverWait filterwait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
		filterwait.until(ExpectedConditions.visibilityOf(filter));
		
		
			//Enter Incidents in filter field and select incidents in the list
		
		driver.findElement(By.id("filter")).sendKeys("Incidents");
		
		driver.findElement(By.xpath("//div[text()='Incidents']")).click();
		
		driver.switchTo().frame("gsft_main");
		
			//Click New button 
		
		
		WebElement newincident = driver.findElement(By.xpath("//button[text()='New']"));
		
		WebDriverWait incidentwait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
		incidentwait.until(ExpectedConditions.visibilityOf(newincident));
		
		newincident.click();
			// Wait for page to load
		WebElement desc = driver.findElement(By.id("incident.short_description"));
		
		WebDriverWait descwait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
		descwait.until(ExpectedConditions.visibilityOf(desc));
			//Get Auto Populated Incident number and enter desc and Submit
		String incidentnumber = driver.findElement(By.xpath("//input[@data-type='string']")).getAttribute("Value");
		System.out.println(incidentnumber);
		desc.sendKeys("Test Desc");
		Thread.sleep(2000);
		driver.findElement(By.id("sysverb_insert_bottom")).click();	
		
		//driver.switchTo().frame("gsft_main");
		WebElement searcharea = driver.findElement(By.xpath("//input[@class='form-control']"));
		
		WebDriverWait searchareawait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		searchareawait.until(ExpectedConditions.visibilityOf(searcharea));
		
		searcharea.click();
		searcharea.sendKeys(incidentnumber);
		searcharea.sendKeys(Keys.ENTER);
		//driver.switchTo().frame("gsft_main");
		driver.findElementByXPath("//table[@id='incident_table']/tbody[1]/tr[1]/td[3]/a[1]").click();
		String createdincidentnumber = driver.findElement(By.xpath("//input[@data-type='string']")).getAttribute("Value");
		if(incidentnumber.equals(createdincidentnumber))
		{
			System.out.println("Incident created successfully");
		}
	}

}
