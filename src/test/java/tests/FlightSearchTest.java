package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightSearchTest {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void testFlightSearch() throws InterruptedException {
		try {

			// Close popup if appears
			handleNewRegistratioPopup();

			// Click on Flights
			driver.findElement(By.xpath("//span[text()='Flights']")).click();

			// Select ROUND TRIP
			WebElement roundTrip = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Round Trip']")));
			roundTrip.click();
			Thread.sleep(5000);
			// Enter FROM Location (HYD)
			WebElement from = driver.findElement(By.id("fromCity"));
			from.click();
			WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='From']"));
			fromInput.sendKeys("Hyderabad");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Hyderabad')]"))).click();

			// Enter TO Location (MAA)
			WebElement to = driver.findElement(By.id("toCity"));
			to.click();
			WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
			toInput.sendKeys("Chennai");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Chennai')]"))).click();

			// Select DEPARTURE Date (Auto-select next available date)
			driver.findElement(By.xpath("//div[contains(@class,'DayPicker-Day--selected') ] ")).click();

			// Select RETURN Date (Next available date after departure)
			driver.findElement(By.xpath("//div[contains(@class,'DayPicker-Day--selected') ] ")).click();

			// Click on SEARCH button
			driver.findElement(By.xpath("//a[text()='Search']")).click();

			// Verify the Search Results Page
			boolean isDisplayed = wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[text()='Flights from Hyderabad to Chennai']")))
					.isDisplayed();
			Assert.assertTrue(isDisplayed, "Search Results page is not displayed");
		} catch (Exception e) {
			Assert.fail("Test Failed: " + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public void handleNewRegistratioPopup() {
		try {
			WebElement closeButton = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
			if (closeButton.isDisplayed()) {
				closeButton.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
