import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;


public class GUITest {
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

    @Test
    public void testButtom1() throws Exception{
        WebElement buttom = driver.findElement(By.xpath("//a[contains(@href, 'films')]"));
        buttom.click();
        String current_url = driver.getCurrentUrl();
        Assert.assertEquals("https://maoyan.com/films", current_url);
    }

    @Test
    public void testButtom2() throws Exception{
        WebElement buttom = driver.findElement(By.xpath("//a[contains(@href, 'cinemas')]"));
        buttom.click();
        String current_url = driver.getCurrentUrl();
        Assert.assertEquals("https://maoyan.com/cinemas", current_url);
        buttom = driver.findElement(By.className("logo"));
        buttom.click();
        current_url = driver.getCurrentUrl();
        Assert.assertEquals("https://maoyan.com/", current_url);
    }

    @Test
    public void testRadio() throws Exception{
        WebElement buttom = driver.findElement(By.xpath("//a[contains(@href, 'films')]"));
        buttom.click();
        WebElement radio1 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[1]/span/span[1]"));
        WebElement radio1_text = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[1]/span/span[2]"));
        WebElement radio2 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[2]/span/span[1]"));
        WebElement radio2_text = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[2]/span/span[2]"));
        WebElement radio3 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[3]/span/span[1]"));
        WebElement radio3_text = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[3]/span/span[2]"));
        Assert.assertEquals("按热门排序", radio1_text.getText());
        Assert.assertEquals("按时间排序", radio2_text.getText());
        Assert.assertEquals("按评价排序", radio3_text.getText());
        Assert.assertEquals("sort-control sort-radio sort-radio-checked", radio1.getAttribute("class"));
        Assert.assertEquals("sort-control sort-radio", radio2.getAttribute("class"));
        Assert.assertEquals("sort-control sort-radio", radio3.getAttribute("class"));

        Thread.sleep(1000);

        radio2.click();
        radio1 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[1]/span/span[1]"));
        radio2 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[2]/span/span[1]"));
        radio3 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[3]/span/span[1]"));
        Assert.assertEquals("sort-control sort-radio", radio1.getAttribute("class"));
        Assert.assertEquals("sort-control sort-radio sort-radio-checked", radio2.getAttribute("class"));
        Assert.assertEquals("sort-control sort-radio", radio3.getAttribute("class"));

        Thread.sleep(1000);

        radio3.click();
        radio1 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[1]/span/span[1]"));
        radio2 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[2]/span/span[1]"));
        radio3 = driver.findElement(By.xpath("//div[@class='cat-sorter']/ul/li[3]/span/span[1]"));
        Assert.assertEquals("sort-control sort-radio", radio1.getAttribute("class"));
        Assert.assertEquals("sort-control sort-radio", radio2.getAttribute("class"));
        Assert.assertEquals("sort-control sort-radio sort-radio-checked", radio3.getAttribute("class"));

        Thread.sleep(1000);
    }

    @Test
    public void testInput() throws Exception{
        WebElement info = driver.findElement(By.className("user-info"));
        info.click();
        WebElement input = driver.findElement(By.id("login-email"));
        Assert.assertEquals("", input.getAttribute("value"));

        Thread.sleep(1000);

        input.sendKeys("13305806767");
        Assert.assertEquals("13305806767", input.getAttribute("value"));

        Thread.sleep(1000);

        input.clear();
        Assert.assertEquals("", input.getAttribute("value"));

        Thread.sleep(1000);
    }

    @Test
    public void testFrom() throws Exception{
        WebElement form = driver.findElement(By.name("kw"));
        Assert.assertEquals("", form.getAttribute("value"));

        Thread.sleep(1000);

        form.sendKeys("大侦探");
        Assert.assertEquals("大侦探", form.getAttribute("value"));

        Thread.sleep(1000);

        form.clear();
        Assert.assertEquals("", form.getAttribute("value"));

        Thread.sleep(1000);
    }

    @Test
    public void testStwitch() throws Exception{
        WebElement buttom = driver.findElement(By.className("more"));
        buttom.click();
        String current = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(current)){
                driver.switchTo().window(handle);
            }
        }
        String current_url = driver.getCurrentUrl();
        Assert.assertEquals("https://piaofang.maoyan.com/dashboard", current_url);
    }

}
