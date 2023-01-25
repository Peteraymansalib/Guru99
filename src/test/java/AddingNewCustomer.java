import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddingNewCustomer {
    WebDriver driver;

    @BeforeTest
    public void prepare(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://www.demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("mngr473471");
        driver.findElement(By.name("password")).sendKeys("uhyvUpY");
        driver.findElement(By.name("btnLogin")).click();
    }
    @AfterTest
    public void close(){
        driver.quit();
    }

    //Adding New Customer with valid data
    @Test (priority = 0)
    public void testCase1(){
        driver.navigate().to("https://www.demo.guru99.com/V4/manager/addcustomerpage.php");
        driver.findElement(By.name("name")).sendKeys("Peter");
        WebElement radio = driver.findElement(By.xpath("//input[@value='m']"));
        radio.click();
        driver.findElement(By.id("dob")).sendKeys("10/01/1997");
        driver.findElement(By.name("addr")).sendKeys("New Cairo");
        driver.findElement(By.name("city")).sendKeys("Cairo");
        driver.findElement(By.name("state")).sendKeys("Egypt");
        driver.findElement(By.name("pinno")).sendKeys("012345");
        driver.findElement(By.name("telephoneno")).sendKeys("0123465789");
        driver.findElement(By.name("emailid")).sendKeys("peteraymans.@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Password");
        driver.findElement(By.name("sub")).click();
        String test1 = driver.findElement(By.cssSelector("p.heading3")).getText();
        if (test1.equals("Customer Registered Successfully!!!")){
            System.out.println("testCase1 Pass");
        }
        else
            System.out.println("testCase1 Fail");
        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
    }

    //Adding New Customer with an existing customer data
    @Test (priority = 1)
    public void testCase2(){
        driver.navigate().to("https://www.demo.guru99.com/V4/manager/addcustomerpage.php");
        driver.findElement(By.name("name")).sendKeys("peter");
        WebElement radio = driver.findElement(By.xpath("//input[@value='m']"));
        radio.click();
        driver.findElement(By.id("dob")).sendKeys("10/01/1997");
        driver.findElement(By.name("addr")).sendKeys("New Cairo");
        driver.findElement(By.name("city")).sendKeys("Cairo");
        driver.findElement(By.name("state")).sendKeys("Egypt");
        driver.findElement(By.name("pinno")).sendKeys("012345");
        driver.findElement(By.name("telephoneno")).sendKeys("0123465789");
        driver.findElement(By.name("emailid")).sendKeys("peter@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Password");
        driver.findElement(By.name("sub")).click();
        String test2 = driver.switchTo().alert().getText();
        if (test2.equals("Email Address Already Exist !!")){
            System.out.println("testCase2 Pass");
        }
        else
            System.out.println("testCase2 Fail");
        driver.switchTo().alert().accept();
    }

    //Adding New Customer with leaving the Customer  name field blank
    @Test (priority = 2)
    public void testCase3(){
        driver.navigate().to("https://www.demo.guru99.com/V4/manager/addcustomerpage.php");
        driver.findElement(By.name("name")).sendKeys("");
        WebElement radio = driver.findElement(By.xpath("//input[@value='m']"));
        radio.click();
        driver.findElement(By.id("dob")).sendKeys("10/01/1997");
        driver.findElement(By.name("addr")).sendKeys("New Cairo");
        driver.findElement(By.name("city")).sendKeys("Cairo");
        driver.findElement(By.name("state")).sendKeys("Egypt");
        driver.findElement(By.name("pinno")).sendKeys("012345");
        driver.findElement(By.name("telephoneno")).sendKeys("0123465789");
        driver.findElement(By.name("emailid")).sendKeys("peter@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Password");
        //driver.findElement(By.name("sub")).click();
        String test3 = driver.findElement(By.xpath("//label[@id='message']")).getText();
        if (test3.equals("Customer name must not be blank")){
            System.out.println("testCase3 Pass");
        }
        else
            System.out.println("testCase3 Fail");
    }

    //Adding New Customer without enter all data
    @Test (priority = 3)
    public void testCase4(){
        driver.navigate().to("https://www.demo.guru99.com/V4/manager/addcustomerpage.php");
        driver.findElement(By.name("name")).sendKeys("peter");
        WebElement radio = driver.findElement(By.xpath("//input[@value='m']"));
        radio.click();
        driver.findElement(By.id("dob")).sendKeys("10/01/1997");
        driver.findElement(By.name("addr")).sendKeys("");
        driver.findElement(By.name("city")).sendKeys("Cairo");
        driver.findElement(By.name("state")).sendKeys("Egypt");
        driver.findElement(By.name("pinno")).sendKeys("");
        driver.findElement(By.name("telephoneno")).sendKeys("0123465789");
        driver.findElement(By.name("emailid")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("Password");
        driver.findElement(By.name("sub")).click();
        String test4 = driver.switchTo().alert().getText();
        if (test4.equals("please fill all fields")){
            System.out.println("testCase4 Pass");
        }
        else
            System.out.println("testCase4 Fail");
        driver.switchTo().alert().accept();
    }

    //Verify the Reset functionality is working good, and it is clears all fields of Adding New Customer
    @Test (priority = 4)
    public void testCase5(){
        driver.navigate().to("https://www.demo.guru99.com/V4/manager/addcustomerpage.php");
        driver.findElement(By.name("name")).sendKeys("Peter");
        WebElement radio = driver.findElement(By.xpath("//input[@value='m']"));
        radio.click();
        driver.findElement(By.id("dob")).sendKeys("10/01/1997");
        driver.findElement(By.name("addr")).sendKeys("New Cairo");
        driver.findElement(By.name("city")).sendKeys("Cairo");
        driver.findElement(By.name("state")).sendKeys("Egypt");
        driver.findElement(By.name("pinno")).sendKeys("012345");
        driver.findElement(By.name("telephoneno")).sendKeys("0123465789");
        driver.findElement(By.name("emailid")).sendKeys("peterAyman.@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Password");
        driver.findElement(By.name("res")).click();
        if (driver.findElement(By.name("name")).getText().equals("") &&
                driver.findElement(By.id("dob")).getText().equals("") &&
                driver.findElement(By.name("addr")).getText().equals("") &&
                driver.findElement(By.name("city")).getText().equals("") &&
                driver.findElement(By.name("state")).getText().equals("") &&
                driver.findElement(By.name("pinno")).getText().equals("") &&
                driver.findElement(By.name("telephoneno")).getText().equals("") &&
                driver.findElement(By.name("emailid")).getText().equals("") &&
                driver.findElement(By.name("password")).getText().equals("")) {
            System.out.println("testCase5 Pass");
        }
        else
            System.out.println("testCase5 Fail");
    }
}
