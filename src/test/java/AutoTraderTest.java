import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class AutoTraderTest {
    private WebDriver webDriver;
    private static ExtentReports report;
    ScreenShot screenShot = new ScreenShot();
    private ExtentTest test;
    private SpreadSheetReader spreadSheetReader;
    private String url;

    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        String fileName = "MyReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }
    @AfterClass
    public static void cleanUp() {
        report.flush();
    }

    @Before
    public void setUp() {
        spreadSheetReader = new SpreadSheetReader("testData.xlsx");
        List<String> inputList;
        inputList = spreadSheetReader.readRow(1, "Sheet1");
        url = inputList.get(0).toString();
        if (inputList.get(1).equals("Chrome"))
        {
            webDriver = new ChromeDriver();
        } else if (inputList.get(1).equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver","geckodriver.exe");
            webDriver = new FirefoxDriver();
        } else {
            test.log(Status.FATAL,"No Supported Browser Selected");
        }
    }
    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void searchForCars()
    {
        spreadSheetReader = new SpreadSheetReader("testData.xlsx");
        List<String> inputList;
        inputList = spreadSheetReader.readRow(1, "Sheet1");
        ExtentTest test = report.createTest("searchForCars");
        HomePage homepage = PageFactory.initElements(webDriver, HomePage.class);
        test.log(Status.INFO, "Going to url: " + url);
        webDriver.navigate().to(url);
        test.log(Status.INFO, "Entering the postcode: " + inputList.get(2));
        homepage.enterPostcode(inputList.get(2));
        test.log(Status.INFO, "Entering the radius: " + floatToString(inputList.get(3)));
        homepage.enterRadius(floatToString(inputList.get(3)));
        test.log(Status.INFO, "Setting used checked status: " + inputList.get(4));
        if (inputList.get(4).equals("unchecked")) homepage.clickUsedChecked();
        test.log(Status.INFO, "Setting nearly used status: " + inputList.get(5));
        if (inputList.get(5).equals("unchecked")) homepage.clickNearlyUsedChecked();
        test.log(Status.INFO, "Setting new status: " + inputList.get(6));
        if (inputList.get(6).equals("unchecked")) homepage.clickNewChecked();
        mySleep();
        test.log(Status.INFO, "Entering the make: " + inputList.get(7));
        homepage.enterMake(inputList.get(7));
        mySleep();
        test.log(Status.INFO, "Entering the model: " + inputList.get(8));
        homepage.enterModel(inputList.get(8));
        mySleep();
        test.log(Status.INFO, "Entering min price: " + floatToString(inputList.get(9)));
        homepage.enterMinPrice(floatToString(inputList.get(9)));
        mySleep();
        test.log(Status.INFO, "Entering max price: " + floatToString(inputList.get(10)));
        homepage.enterMaxPrice(floatToString(inputList.get(10)));
        test.log(Status.INFO, "Taking screenshot");

        try {
            String path = screenShot.take(webDriver, "image");
            test.addScreenCaptureFromPath(path);
        } catch (IOException e) {
            test.log(Status.FAIL,"Failed taking or adding the screenshot");
        }

        test.log(Status.INFO,"submitting search query; Expected URL:"+inputList.get(11));
        homepage.clickSearch();
        try {
            Assert.assertEquals(inputList.get(11), webDriver.getCurrentUrl());
            test.log(Status.PASS,"Passed url comparison assertion");
        } catch (AssertionError  e) {
            test.log(Status.FAIL,"Failed url comparison assertion");
        }
    }
    @Test
    public void createAd()
    {
        spreadSheetReader = new SpreadSheetReader("testData.xlsx");
        List<String> inputList;
        inputList = spreadSheetReader.readRow(1, "Sheet1");
        ExtentTest test = report.createTest("createAd");
        HomePage homepage = PageFactory.initElements(webDriver, HomePage.class);
        test.log(Status.INFO, "Going to url: " + url);
        webDriver.navigate().to(url);
        test.log(Status.INFO, "Entering Reg Number: " + inputList.get(12));
        homepage.enterRegNo(inputList.get(12));
        test.log(Status.INFO, "Entering miles: " + inputList.get(13));
        homepage.enterMiles(floatToString(inputList.get(13)));
        homepage.clickCreateAd();
        try {
            Assert.assertEquals(inputList.get(14),webDriver.getCurrentUrl());
            test.log(Status.PASS,"Passed url comparison assertion");
        } catch (AssertionError  e) {
            test.log(Status.FAIL,"Failed url comparison assertion");
        }
    }
    @Test
    public void getValuation()
    {
        spreadSheetReader = new SpreadSheetReader("testData.xlsx");
        List<String> inputList;
        inputList = spreadSheetReader.readRow(1, "Sheet1");
        ExtentTest test = report.createTest("createAd");
        HomePage homepage = PageFactory.initElements(webDriver, HomePage.class);
        test.log(Status.INFO, "Going to url: " + url);
        webDriver.navigate().to(url);
    }
    private void mySleep()
    {
        try {
            Thread.sleep(200);
        } catch (Exception e) {}
    }
    private String floatToString(String input)
    {
        return String.valueOf((int) Float.parseFloat(input));
    }
}
