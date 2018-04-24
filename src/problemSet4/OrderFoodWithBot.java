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

public class OrderFoodWithBot {

	// I don't want to show you my user credentials so I'm just going to the main page
	// and search by postal code.
	//	static String myUserName = "sudiptacasual@gmail.com";
	//	static String myPassword = "thisisnotmypassword";
	
	public static void main(String[] args) throws InterruptedException {		

		System.setProperty("webdriver.gecko.driver","/Users/yaojie/Downloads/geckodriver");
		WebDriver driver = new FirefoxDriver();
		// driver.get("https://deliveroo.com.sg/login");
		driver.get("https://deliveroo.com.sg/?change_location");
				
		WebElement postCode = driver.findElement(By.name("postcode"));
		postCode.sendKeys("510233");
		postCode.submit();
		Thread.sleep(5000);
		// get all the links
		java.util.List<WebElement> links = driver.findElements(By.tagName("h3"));
		System.out.println(links.size());
		
		for (int i = 0; i < links.size() - 4; i++) {
			System.out.println(i + " " + links.get(i).getText());
		}		
		
//		// get the user name field of the account page
//		WebElement username = driver.findElement(By.name("login_email"));
//		WebElement password = driver.findElement(By.name("login_password"));		
//		
//		// send my user name to fill up the box
//		username.sendKeys(myUserName);
//		password.sendKeys(myPassword);
//		username.submit();
//		password.submit();
		
	}
}
