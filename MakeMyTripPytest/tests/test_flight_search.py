import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service

@pytest.fixture(scope="class")
def setup(request):
    service = Service("C:/Users/Vijay/Downloads/chromedriver-win64-1/chromedriver-win64/chromedriver.exe")
    options = webdriver.ChromeOptions()

    driver = webdriver.Chrome(service=service, options=options)
    driver.maximize_window()
    request.cls.driver = driver
    yield
    driver.quit()

@pytest.mark.usefixtures("setup")
class TestMakeMyTrip:
    def test_open_website(self):
        self.driver.get("https://www.makemytrip.com/")

    def test_select_round_trip(self):
        self.driver.implicitly_wait(5)
        #self.driver.find_element(By.CSS_SELECTOR, "body").click()
        self.driver.find_element(By.XPATH, "//span[text()='Flights']").click()
        round_trip = WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, "//li[text()='Round Trip']"))
        )
        round_trip.click()

    def test_enter_locations(self):
        self.driver.find_element(By.ID, "fromCity").click()
        from_input = self.driver.find_element(By.XPATH, "//input[@placeholder='From']")
        from_input.send_keys("Hyderabad")
        WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, "//p[contains(text(),'Hyderabad')]"))
        ).click()

        self.driver.find_element(By.ID, "toCity").click()
        to_input = self.driver.find_element(By.XPATH, "//input[@placeholder='To']")
        to_input.send_keys("Chennai")
        WebDriverWait(self.driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, "//p[contains(text(),'Chennai')]"))
        ).click()

    def test_select_dates(self):
        self.driver.find_element(By.XPATH, "//label[@for='departure']").click()
        self.driver.find_element(By.XPATH, "//div[contains(@aria-label, 'Choose')]").click()

        self.driver.find_element(By.XPATH, "//label[@for='return']").click()
        self.driver.find_element(By.XPATH, "//div[contains(@aria-label, 'Choose')]").click()

    def test_click_search_button(self):
        self.driver.find_element(By.XPATH, "//a[text()='Search']").click()

    def test_verify_search_results(self):
        is_displayed = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((By.XPATH, "//p[text()='Flights']"))
        ).is_displayed()
        assert is_displayed, "Search Results page is not displayed"
