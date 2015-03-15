/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spambot.invisicow;

import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//https://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/interactions/package-summary.html

/**
 *
 * @author robin
 */
public class SeleniumDriver {
    
    public void drive(int opt) throws InterruptedException
    {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://findtheinvisiblecow.com");
        
        WebElement body = driver.findElement(By.tagName("body"));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(body));
        
        //start game     
        WebElement button = driver.findElement(By.xpath("//div[@id='loader']//button"));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(button));
        button.click();
        
        for(int i=0; i< opt-1; i++ )
        {
            //so the user sees something
            Thread.sleep(2500);

            //win game
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver).executeScript("find.gameStop(true);");
            }
            
            //again?
            WebElement congrats = driver.findElement(By.id("modal-congratulations"));
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(congrats));
            Thread.sleep(1500);
            WebElement again = driver.findElement(By.xpath("//div[@id='modal-congratulations']//button"));
            again.click();
        }
        
        Thread.sleep(2500);
        //win game one last time
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("find.gameStop(true);");
        }
        
        Thread.sleep(3000);
        
        JOptionPane.showMessageDialog(null,"Goodbye!");  

        
        driver.quit();
    }
    
    public void driveExample()
    {
            // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new FirefoxDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
        //Close the browser
        driver.quit();
    }
   
    
}
