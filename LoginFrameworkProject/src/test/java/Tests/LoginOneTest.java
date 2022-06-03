package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import PageObjects.AccountPage;
import PageObjects.LandingPage;
import PageObjects.LoginPage;

import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginOneTest extends BaseTest{
	// Create first WebDriver reference.
	WebDriver driver;


	@Parameters({ "Browser" })
	@BeforeTest
	public void openBrowser(String browser) {
		if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
	}
	
	@Parameters({ "URL" })
	@BeforeTest(dependsOnMethods = {"openBrowser"})
	public void getURL(String URL) throws InterruptedException {
		driver.get(URL);
		Thread.sleep(500);
	}


	
	
	@Test(dataProvider = "getLoginData")
	public void loginWithCorrectEmailAndPassword(String email, String password, String expectedResult)
			throws InterruptedException {
		test = extent.createTest("Login with valid credentials Test");
		test.log(Status.INFO, "Opening the TutorialsNinja Website");

		Thread.sleep(500);


		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		test.pass("Clicked MyAccount dropdown");
		Thread.sleep(500);
		landingPage.loginOption().click();
		test.pass("Clicked Login Option");
		Thread.sleep(500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextField().sendKeys(email);
		test.pass("Entered email id");
		loginPage.passwordField().sendKeys(password);
		test.pass("Entered password");
		loginPage.loginButton().click();
		test.pass("Clicked Login button");
		Thread.sleep(500);

		AccountPage accountPage = new AccountPage(driver);
		String actualResult = null;
		try {
			if (accountPage.editYourAccountInformation().isDisplayed()) {

				actualResult = "Success";
				test.pass("Login success");

			}

		} catch (Exception e) {

			actualResult = "Failure";
			test.pass("Login Failed");

		}

		Assert.assertEquals(actualResult, expectedResult);


	}

	@AfterMethod
	public void close() {
		driver.close();
		test.pass("Browser closed");
		test.info("Login testcase completed");
		extent.flush();


	}
	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = { {"testseleniumnow@gmail.com", "first@123", "Success" } };

		return data;

	}

}
