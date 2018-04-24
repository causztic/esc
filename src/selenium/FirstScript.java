package selenium;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
 
public class FirstScript {
	
	public static void main(String[] args) {		
		System.setProperty("webdriver.gecko.driver","/Users/yaojie/Downloads/geckodriver");
 
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
	}
}