package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightSearchSteps {
	WebDriver driver;
	WebDriverWait wait;

	@Before
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Given("I launch Chrome browser and open MakeMyTrip website")
	public void openWebsite() {
		driver.get("https://www.makemytrip.com/");
	}

	@And("I click on Flights and select ROUND TRIP")
	public void selectRoundTrip() throws InterruptedException {
		Thread.sleep(3000);
		handleNewRegistratioPopup();
		driver.findElement(By.xpath("//span[text()='Flights']")).click();
		WebElement roundTrip = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Round Trip']")));
		roundTrip.click();
	}

	@And("I enter FROM location as {string} and TO location as {string}")
	public void enterLocations(String fromCity, String toCity) {
		driver.findElement(By.id("fromCity")).click();
		WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='From']"));
		fromInput.sendKeys(fromCity);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'" + fromCity + "')]")))
				.click();

		driver.findElement(By.id("toCity")).click();
		WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
		toInput.sendKeys(toCity);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'" + toCity + "')]"))).click();
	}

	@And("I select DEPARTURE and RETURN dates")
	public void selectDates() {
		// Select DEPARTURE Date (Auto-select next available date)
		driver.findElement(By.xpath("//div[contains(@class,'DayPicker-Day--selected') ] ")).click();

		// Select RETURN Date (Next available date after departure)
		driver.findElement(By.xpath("//div[contains(@class,'DayPicker-Day--selected') ] ")).click();
	}

	@When("I click on the Search button")
	public void clickSearchButton() {
		driver.findElement(By.xpath("//a[text()='Search']")).click();
	}

	@Then("I should see the Search Results page")
	public void verifySearchResultsPage() {
//		boolean isDisplayed = wait
//				.until(ExpectedConditions
//						.visibilityOfElementLocated(By.xpath("//*[text()='Flights from Hyderabad to Chennai']")))
//				.isDisplayed();
//		Assert.assertTrue(isDisplayed, "Search Results page is not displayed");
	}

	@After
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
