package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaLoreal {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		//1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		//Hover over Brand and Mouse Over Popular
		driver.findElementByXPath("(//a[@href='#'])[2]").click();
		//Actions actions = new Actions(driver);
		//actions.moveToElement(brand).perform();
		driver.findElement(By.xpath("//div[@class='BrandsCategoryHeading']//a[1]")).click();
		//actions.moveToElement(popular).perform();
		driver.findElement(By.xpath("(//div[@id = 'brandCont_Popular']//li)[5]")).click();
		//actions.moveToElement(loreal).click();
		
		Thread.sleep(2000);
		Set <String> str = driver.getWindowHandles();
		List<String> listwindows = new ArrayList<String>(str);
		driver.switchTo().window(listwindows.get(1));
		System.out.println(driver.getTitle());
		Thread.sleep(6000);
		Set <String> nykka = driver.getWindowHandles();
		List<String> nykkalistwindows = new ArrayList<String>(nykka);
		
		driver.switchTo().window(nykkalistwindows.get(1));
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		Thread.sleep(2000);
		String shampooselect = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		System.out.println(shampooselect);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='listingContainer']/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[11]/div[1]/a[1]/div[1]/div[2]/div[1]/h2[1]/span[1]")).click();
		//Navigate to next window
		nykka = driver.getWindowHandles();
		nykkalistwindows = new ArrayList<String>(nykka);
		driver.switchTo().window(nykkalistwindows.get(2));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='175ml']")).click();
		Thread.sleep(2000);
		String price = driver.findElement(By.xpath("//span[@class='post-card__content-price-offer']")).getText();
		System.out.println(price);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,'combo-add-to-btn prdt-des-btn')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='cursor-hand arrowup-tooltip']//div[1]")).click();
		String grandtotal = driver.findElement(By.xpath("//div[@class='first-col']//div[1]")).getText();
		System.out.println(grandtotal);
		
		driver.findElement(By.xpath("//button[@type='button']//span[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[contains(@class,'btn full')])[2]")).click();
		
		String total = driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText();
		if(grandtotal.equals(total))
			System.out.println("The Grand total is same");
		driver.close();
		driver.switchTo().window(nykkalistwindows.get(1)).close();
		driver.switchTo().window(nykkalistwindows.get(0)).close();
	}

}
