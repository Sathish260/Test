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
		WebElement brand = driver.findElement(By.xpath("//div[text()='Watches & Accessories']"));
		
		builder.moveToElement(brand).perform();
		driver.findElement(By.xpath("//div[text()='Casio']")).click();

		WebElement sort = driver.findElement(By.xpath("//select[@class='SelectBoxDesktop__hideSelect']"));
		
		Select sortdd = new Select(sort);

		sortdd.selectByVisibleText("Popularity");

		Thread.sleep(2880);
        
		driver.findElement(By.xpath("//div[@class='CheckBox_base'])[1]")).click();
        //Print all price of watches(take only numbers)
		List<WebElement> table = driver.findElements(By.xpath("//div[@class='ProductDescription__priceHolder']//h3"));
		for (int i=0; i<table.size() ; i++) {
		System.out.println(table.get(i).getText());
			
		String parentWindow = driver.getWindowHandle();
		 //click the second loaded result
        driver.findElement(By.xpath("(//div[@class='ProductModule__imageAndDescriptionWrapper'])[2]")).click();
        //Handle the window and Print the price of the watch
        Set<String> childWindow= driver.getWindowHandles();
        System.out.println("childWindow" + childWindow);
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
}
