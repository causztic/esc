package problemSet4;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class cohortExercise10 {
	
	@Test
	public void testDirectTitles(){
		System.setProperty("webdriver.gecko.driver","/Users/yaojie/Downloads/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://motherfuckingwebsite.com/"); // don't worry it's safe for work
		
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		// click all links in a web page
		for(int i = 0; i < links.size(); i++)
		{
			String link = links.get(i).getAttribute("href");
			if (link == null)
				continue;
			boolean staleElementLoaded = true;
			while(staleElementLoaded) {
				try {
					driver.navigate().to(link);
					Thread.sleep(1000);
					System.out.println("navigating to " + link);
					String title = driver.getTitle();
					if (title == null || title.length() == 0)
						assert(false);
					driver.navigate().back();
					links = driver.findElements(By.tagName("a"));
					staleElementLoaded = false;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					staleElementLoaded = true;
				}
			}
		}
		assert(true);
	}
}
