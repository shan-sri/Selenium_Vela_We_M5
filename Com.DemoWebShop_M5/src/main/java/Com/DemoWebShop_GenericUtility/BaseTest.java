package Com.DemoWebShop_GenericUtility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Com.DemoWebShop_POM.HomePage;
import Com.DemoWebShop_POM.LoginPage;
import Com.DemoWebShop_POM.WelcomePage;

public class BaseTest {

	public WebDriver driver;
	public static WebDriver sDriver;
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;
	public FileUtility fileUtility = new FileUtility();
	public WelcomePage welcomePage;
	public LoginPage loginPage;
	public HomePage homePage;
	public WebDriverUtility webDriverUtility = new WebDriverUtility();

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("DataBase Connected");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Report Started");

		spark = new ExtentSparkReporter(FrameWorkConstants.reportsPath);
		reports = new ExtentReports();
		reports.attachReporter(spark);
		test = reports.createTest("DemoWebShop");

	}

	@BeforeClass
	public void beforeClass() throws IOException {
		System.out.println("Launch Browser");

//		String browser = fileUtility.readDataFromPropertFile("browserName");
//		String baseUrl = fileUtility.readDataFromPropertFile("url");

		String baseUrl = System.getProperty("url");
		String browser = System.getProperty("browserName");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Enter valid browser name");
		}

		sDriver = driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(baseUrl);

	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.out.println("Login");

//		webDriverUtility.error(driver);
		welcomePage = new WelcomePage(driver);
		welcomePage.getLoginBtn().click();

		loginPage = new LoginPage(driver);
		loginPage.getEmailTextField().sendKeys(fileUtility.readDataFromPropertFile("username"));
		loginPage.getPasswordTextField().sendKeys(fileUtility.readDataFromPropertFile("password"));
		loginPage.getLoginBtn().click();

		homePage = new HomePage(driver);

	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		System.out.println("Logout");

		Thread.sleep(3000);
		homePage.getLogOutBtn().click();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		System.out.println("Close Browser");

		Thread.sleep(3000);
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Reports Ended");
		reports.flush();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("DataBase Disconnected");
	}

}
