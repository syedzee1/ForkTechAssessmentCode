package commonUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * 
 * @author Syed Zeeshan
 *
 */
public class WebDriverUtility {
	/**
	 * used to maximize the browser
	 * @param driver
	 */
	public void maximizeTheWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	/**
	 * to refresh the page
	 * @param driver
	 */
	public void refershPage(WebDriver driver)
	{
		driver.navigate().refresh();
	}

	/**
	 * to get forward to next page
	 * @param driver
	 */
	public void navigateToNextURL(WebDriver driver,URL url)
	{
		driver.navigate().to(url);
	}
	/**
	 *it will wait for 10 seconds till the page gets load.
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConstantData.implicitlyWaitDuration));
	}

	/**
	 * it will check for the element in the GUI for polling time of 500ms
	 * @param driver
	 * @param element
	 */
	public void waitForElemenetToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(ConstantData.explicitWaitDuration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * this method will wait till element to visible
	 * @param driver
	 * @param element
	 */
	public void waitTillElementToVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConstantData.explicitWaitDuration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	/**
	 * here we can change the polling time from default 500ms to any polling time
	 * @param driver
	 * @param pollingTime
	 * @param element
	 */
	public void waitForElementToClick(WebDriver driver,int pollingTime,WebElement element)
	{
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofSeconds(pollingTime));
		wait.ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * this method is used to switch to window using title
	 * @param driver
	 * @param actualTitle
	 */
	public void switchTowindow(WebDriver driver,String actualTitle)
	{
		Set<String> set = driver.getWindowHandles();
		for (String string : set)
		{
			driver.switchTo().window(string);
			String title = driver.getTitle();
			if(title.contains(actualTitle))
			{
				break;
			}
		}
	}
	/**
	 * this method is used to switch to window by using URL
	 * @param actualURL
	 * @param driver
	 */
	public void switchToWindow(String actualURL,WebDriver driver)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String wId = it.next();
			driver.switchTo().window(wId);
			String url = driver.getCurrentUrl();
			if(url.contains(actualURL))
			{
				break;
			}
		}
	}
	/**
	 * switch from one frame to another frame by using index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * switch from one frame to another frame by using id_attribute
	 * @param driver
	 * @param id_attribute
	 */
	public void switchToFrame(WebDriver driver,String id_attribute)
	{
		driver.switchTo().frame(id_attribute);
	}
	/**
	 * switch from one frame to another frame by using absolute_path
	 * @param driver
	 * @param element
	 */
	public void switchtoFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	/**
	 * its used to switch back from one child frame to parent frame
	 * @param driver
	 */
	public void switchBackToMainPage(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is used to switch alert popUp and accept the popup
	 * @param driver
	 * @param expectedMsg
	 */
	public void switchToAlertPopUpAndAccept(WebDriver driver,String expectedMsg)
	{
		Alert alert = driver.switchTo().alert();
		if(alert.getText().trim().equalsIgnoreCase(expectedMsg.trim()))
		{
			System.out.println("alert msg is verified");
		}else {
			System.out.println("alert msg is not verified");
		}
		alert.accept();
	}
	/**
	 * this method is used to switch to alert popUp and dismiss
	 * @param expectedMsg
	 * @param driver
	 */
	public void switchToAlertPopUpAndDismiss(String expectedMsg,WebDriver driver)
	{
		Alert alert = driver.switchTo().alert();
		if(alert.getText().trim().equalsIgnoreCase(expectedMsg))
		{
			System.out.println("alert msg is verified");
		}else {
			System.out.println("alert msg is not verified");
		}
		alert.dismiss();
	}
	/**
	 * it is used switch to select dropdown by using index
	 * @param element
	 * @param index
	 */
	public void selectDropDown(WebElement element,int index)
	{
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * it is used switch to select dropdown by using value
	 * @param element
	 * @param value
	 */
	public void selectDropDown(WebElement element,String value)
	{
		Select select=new Select(element);
		select.selectByValue(value);
	}
	/**
	 * it is used switch to select dropdown by using visible_text
	 * @param element
	 * @param visible_text
	 */
	public void selectDropDownByVisibletext(WebElement element,String visible_text)
	{
		Select select=new Select(element);
		select.selectByVisibleText(visible_text);
	}
	/**
	 * it is used to mouse over an element
	 * @param driver
	 * @param element
	 */
	public void mouseOverAnElement(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * it is used to right click on element
	 * @param driver
	 * @param element
	 */
	public void rightClickOnElement(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * it is used to take screenShot
	 * @param driver
	 * @param screenShotName
	 * @throws Throwable 
	 * @throws Throwable
	 */
	public void  takeScreenShot(WebDriver driver,String screenShotName) throws Throwable
	{
		TakesScreenshot takeScreenShot = (TakesScreenshot)driver;
		File src = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenShot/"+screenShotName+".PNG");
		FileUtils.copyFile(src, dst);
	}
	/**
	 * it is used to perform scrollBar Actions
	 * @param driver
	 */
	public void scrollBarAction(WebDriver driver)
	{
		JavascriptExecutor javaScript = (JavascriptExecutor)driver;
		javaScript.executeScript("window.scrollBy(0,6000)");
	}
}

