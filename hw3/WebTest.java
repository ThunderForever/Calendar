import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

        WebElement login = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/ul/li[2]"));


        Assert.assertEquals("qfH821713590", login.getAttribute("title"));

    }

    @Test
    public void testLogout() throws Exception{
        login();

        WebElement logout = driver.findElement(By.xpath(("/html/body/div[1]/div/div[3]/div/ul/li[3]/a")));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",logout);

        Thread.sleep(100);


        WebElement href = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/ul/li/a"));


        Assert.assertEquals("登录", href.getAttribute("innerHTML"));
    }

    @Test
    public void testCookie1() throws Exception{

        //清空cookie
        driver.manage().deleteAllCookies();
        login();
        Thread.sleep(1000);

        Set<Cookie> cookies=driver.manage().getCookies();


        //判断cookie是否有新的值

        Assert.assertNotEquals(0, cookies.size());

    }


    // 测试刷新对cookie的影响
    @Test
    public void testCookie2() throws Exception{
        driver.manage().deleteAllCookies();
        login();
        Thread.sleep(1000);

        Cookie c1 = driver.manage().getCookieNamed("uuid");

        driver.navigate().refresh();
        Thread.sleep(1000);

        Cookie c2 = driver.manage().getCookieNamed("uuid");
        Assert.assertEquals(c1.getValue(), c2.getValue());


    }

    @Test
    public void testCookie3() throws Exception{
        driver.manage().deleteAllCookies();
        login();
        Thread.sleep(1000);

        Cookie c = driver.manage().getCookieNamed("_lxsdk_s");

        Date time = c.getExpiry();
        Date currenttime=new Date();


        long t = 30 * 60 * 1000;//30 min

        Date checktime = new Date(currenttime.getTime() + t);
        System.out.println(time.toString().substring(0,16));
        System.out.println(checktime.toString().substring(0,16));
        Assert.assertEquals(time.toString().substring(0,16), checktime.toString().substring(0,16));

    }

    @Test
    public void testSearch() throws Exception{

        WebElement search_form = driver.findElement(By.xpath("/html/body/div[1]/div/form"));
        driver.findElement(By.name("kw")).sendKeys("大侦探皮卡丘");
        search_form.submit();

        String currentWindow = driver.getWindowHandle();


        Set<String> handles = driver.getWindowHandles();

        Iterator<String> iterator = handles.iterator();

        while (iterator.hasNext()){
            String handle = iterator.next();
            if(currentWindow.equals(handle)) continue;

            WebDriver window = driver.switchTo().window(handle);

            System.out.println(window.getCurrentUrl());

            List<WebElement> target = window.findElements(By.linkText("大侦探皮卡丘"));


            Assert.assertNotEquals(target.size(),0);

        }

    }

    @Test
    public void testSearch2(){
        WebElement search_form = driver.findElement(By.xpath("/html/body/div[1]/div/form"));
        driver.findElement(By.name("kw")).sendKeys("123456789");
        search_form.submit();

        String currentWindow = driver.getWindowHandle();


        Set<String> handles = driver.getWindowHandles();

        Iterator<String> iterator = handles.iterator();

        while (iterator.hasNext()){
            String handle = iterator.next();
            if(currentWindow.equals(handle)) continue;

            WebDriver window = driver.switchTo().window(handle);

            System.out.println(window.getCurrentUrl());

            WebElement result = window.findElement(By.className("empty-list"));


            Assert.assertFalse(result == null);

        }
    }
}
