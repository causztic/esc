package problemSet4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cohortExercise7 {
		
	public static void main(String[] args) throws InterruptedException {		
		System.setProperty("webdriver.gecko.driver","/Users/yaojie/Downloads/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://istd.sutd.edu.sg");
		
		driver.manage().window().maximize();
		
		while (true) {
			// get all the links
			java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
			// click all links in a web page
			for(int i = 0; i < links.size(); i++)
			{
				System.out.println("*** Navigating to" + " " + links.get(i).getAttribute("href"));
				if (links.get(i).getAttribute("href") == null)
					continue;
				boolean staleElementLoaded = true;
				while(staleElementLoaded) {
					try {
						driver.navigate().to(links.get(i).getAttribute("href"));
						Thread.sleep(3000);
						driver.navigate().back();
						links = driver.findElements(By.tagName("a"));
						System.out.println("*** Navigated to" + " " + links.get(i).getAttribute("href"));
						staleElementLoaded = false;
					} catch (StaleElementReferenceException e) {
						staleElementLoaded = true;
					}
				}
			}
		}

	}
}
