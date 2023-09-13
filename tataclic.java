package Assignement5;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class tataclic {
	public static void main(String[] args) throws InterruptedException, IOException {
		
		EdgeOptions options=new EdgeOptions();
		options.addArguments("--disable-notifications","start-maximized");
		
		EdgeDriver driver=new EdgeDriver(options);
		
		//Lanuch the browser
		driver.get("https://www.tatacliq.com/");
		//maximize the browser
		driver.manage().window().maximize();
		//add implicitly wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(3000);
		//MouseHover on Brands
		WebElement brands = driver.findElement(By.xpath("//div[text()='Brands']"));
		Actions builder =new Actions(driver);
		builder.moveToElement(brands).perform();
		
		//click Watches &Accessories 
		WebElement brand = driver.findElement(By.xpath("//div[text()='Watches & Accessories']"));
		builder.moveToElement(brand).perform();
		
		//Choose the first option from the Brand display
	    driver.findElement(By.xpath("//div[text()='Casio']")).click();
       
		//Select sort by-New Arrivals
		WebElement sort = driver.findElement(By.xpath("//select[@class='SelectBoxDesktop__hideSelect']"));
		Select select = new Select(sort);
		select.selectByVisibleText("New Arrivals");
		Thread.sleep(2000);
		//Click Men check box
		driver.findElement(By.xpath("//div[text()='Men']")).click();
		Thread.sleep(2000);
        
		driver.findElement(By.xpath("(//div[@class='CheckBox__base'])[1]")).click();
        //Print all price of watches(take only numbers)
		List<WebElement> price = driver.findElements(By.xpath("//div[@class='ProductDescription__priceHolder']/h3"));
		for (int i = 0; i < price.size(); i++) {
			System.out.println(price.get(i).getText());
		}
		
		String Price = driver.findElement(By.xpath("(//div[@class='ProductDescription__priceHolder']/h3)[1]")).getText();
		
		//click the second loaded result
		driver.findElement(By.xpath("(//div[@class='ProductModule__dummyDiv'])[2]")).click();
		
		Set<String> childWindow= driver.getWindowHandles();
        System.out.println("childWindow" + childWindow);
        
        String Watchs = driver.findElement(By.xpath("//div[@class='ProductDetailsMainCard__price']/h3")).getText();
		System.out.println(Watchs);
        //Check the bag count
        driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
        //Click the cart bag
        driver.findElement(By.className("DesktopHeader__myBagShow")).click();
        //Take snapshot of result 
        File screenshotAs = driver.getScreenshotAs(OutputType.FILE);

    	File destination=new File("./Images/001.jpg");
    	
    	FileUtils.copyFile(screenshotAs, destination);
    	//Close the open window
    	driver.close();
    	// Close the entire browser session
    	driver.quit();
		}
}

