package problemSet4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cohortExercise9 {
	// Write a test to do the following: Send 20 invalid usernames to Google login form
	// (be innovative about the invalid usernames)
	// then login with your user name and password (i'm not doing this because no password for you)
	
	static String myUserName = "limyaojie93@gmail.com";
	static String myPassword = "noPasswordForYou";
	
	public static void main(String[] args) throws InterruptedException {		

		System.setProperty("webdriver.gecko.driver","/Users/yaojie/Downloads/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://gmail.com/");


		int tries = 20;
		for (int i = 0; i < tries; i++){
			String userName = DictionaryFuzzer.generateWrongEmail();
			// get the user name field of the account page
			WebElement username = driver.findElement(By.name("identifier"));
			username.clear();
			username.sendKeys(userName);
			WebElement nextButton = driver.findElement(By.id("identifierNext"));		
			nextButton.click();
			
			//explicitly wait until the password field is present in the page
			try {
				WebDriverWait wait = new WebDriverWait(driver, 5);
				// wait only until the password element becomes visible
				wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));		
				// now locate the password field in the current page
				WebElement password = driver.findElement(By.name("password"));		
				// send password 
				password.sendKeys(myPassword);
				// login and :)
				nextButton = driver.findElement(By.id("passwordNext"));		
				nextButton.click();
			} catch (Exception NoSuchElementException) {
				System.out.println("login name invalid");
			}
		}
		
		// to login with my own username and password, just do the same as cohort.
	}
}
