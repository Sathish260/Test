package org.college;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class MakeMyTripAutomation {
    public static void main(String[] args) throws IOException, InterruptedException {
              EdgeDriver driver = new EdgeDriver();
              driver.manage().window().maximize();
      		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       
            // Step 1: Open the MakeMyTrip website.
            driver.get("https://www.makemytrip.com/");
            
            Thread.sleep(2000);
            driver.findElement(By.className("close")).click();
            // Step 2: Close the Sweet Alert.
            driver.findElement(By.xpath("//li[text()='MyBiz Account']")).click();
		    driver.findElement(By.name("username")).sendKeys("ram26@gmail.com");
		    driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		    driver.findElement(By.xpath("//span[@class='mybizLoginClose']")).click();
		    driver.findElement(By.xpath("//span[@data-cy='BizRequestCallBackForm_164']")).click();
          
            // Step 3: Click on the website logo.
            driver.findElement(By.className("//img[@alt='Make My Trip]'")).click();

            // Step 4: Navigate to the "Holiday Packages" section.
            WebElement holidayPackages = driver.findElement(By.xpath("//span[text()='Holiday Packages']"));
            holidayPackages.click();

            // Step 5: Input "Chennai" as the departure city.
            WebElement departureCity = driver.findElement(By.id("fromCity"));
            departureCity.clear();
            departureCity.sendKeys("Chennai");
            departureCity.sendKeys(Keys.RETURN);

            // Step 6: Select the first suggested city.
            driver.findElement(By.className("appendLeft8 blackText")).click();

            // Step 7: Specify "Goa" as the destination city.
            WebElement destinationCity = driver.findElement(By.id("toCity"));
            destinationCity.clear();
            destinationCity.sendKeys("Goa");
            destinationCity.sendKeys(Keys.RETURN);

            // Step 8: Choose the first city suggestion.
            driver.findElement(By.className("dest-city-container")).click();

            // Step 9: Pick a date from the calendar.
            driver.findElement(By.xpath("(//p[text()='23'])[2]")).click();
        
            // Step 10: Adjust the number of adults to the maximum allowed.
           driver.findElement(By.xpath("//div[@data-testid='adult-increment-counter']")).click();

            // Step 11: Click on the apply button.
            driver.findElement(By.xpath("//button[text()='APPLY']")).click();
            
            driver.findElement(By.xpath("//div[text()='Filters']")).click();
            // Set the trip duration to 3-5 nights.
            driver.findElement(By.className("rc-slider-handle rc-slider-handle-1")).click();
            driver.findElement(By.className("rc-slider-handle rc-slider-handle-2")).click();
            // Step 13: Click on the apply button.
            driver.findElement(By.xpath("//button[text()='APPLY'])")).click();

            // Step 14: Click on the search button.
            driver.findElement(By.xpath("//button[text()='Search']")).click();

            // Step 15: Skip an automated assistance prompt.
            driver.findElement(By.xpath("//button[text()='SKIP']")).click();

            // Step 16: Capture a screenshot of the first vacation package.
            WebElement vacationPackage = driver.findElement(By.className("card-deck"));
            File screenshot = vacationPackage.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("vacation_package.png"));

            // Step 17: Print the current page title.
            System.out.println("Current Page Title: " + driver.getTitle());
      
            driver.quit();
        }
    }

