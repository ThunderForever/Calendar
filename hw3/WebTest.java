import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class WebTest {
    private WebDriver driver;
    private String baseUrl = "https://maoyan.com/";
    private StringBuffer verificationErrors = new StringBuffer();


    @BeforeMethod
    public void setUp() throws Exception{
        String browserDriverUrl = "G:\\software-testing\\chromeDriver\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", browserDriverUrl);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }


    @AfterMethod
    public void tearDown() throws Exception{
        driver.quit();

        String verificationErrorString= verificationErrors.toString();

        if(!"".equals(verificationErrorString)){
            System.out.println(verificationErrorString);
        }
    }

    // support function
    private void login(){
        WebElement info = driver.findElement(By.className("user-info"));
        info.click();

        driver.findElement(By.id("login-email")).sendKeys("15857959140");
        driver.findElement(By.id("login-password")).sendKeys("981002");
        driver.findElement(By.name("commit")).click();
    }

    private void logout(){
        WebElement logout = driver.findElement(By.xpath(("/html/body/div[1]/div/div[3]/div/ul/li[3]/a")));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",logout);
    }

    @Test
    public void testLogin() throws Exception{
        WebElement info = driver.findElement(By.className("user-info"));
        info.click();

        driver.findElement(By.id("login-email")).sendKeys("15857959140");
        driver.findElement(By.id("login-password")).sendKeys("981002");
        driver.findElement(By.name("commit")).click();

        Thread.sleep(1000);

        WebElement login = driver.findElement(By.className("user-info"));

        login.click();


        List<WebElement> ele= driver.findElements(By.xpath("//input[@value='qfH821713590']"));

        Assert.assertEquals(1,ele.size());

    }

    @Test
    public void testLogout() throws Exception{
        login();

        WebElement logout = driver.findElement(By.xpath(("/html/body/div[1]/div/div[3]/div/ul/li[3]/a")));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",logout);

        WebElement login = driver.findElement(By.className("user-info"));

        List<WebElement> ele = login.findElements(By.className("text"));

        Assert.assertEquals(0,ele.size());
    }

}
