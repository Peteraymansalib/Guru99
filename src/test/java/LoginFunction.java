import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginFunction {
    WebDriver driver;

    @BeforeTest
    public void prepare(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterTest
    public void close(){
        driver.quit();
    }

    //Verify if a user will be able to log in with a valid username and valid password
    @Test (priority = 0)
        public void testCase1(){
        driver.navigate().to("https://www.demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("mngr473471");
        driver.findElement(By.name("password")).sendKeys("uhyvUpY");
        driver.findElement(By.name("btnLogin")).click();
        String test1 = driver.getTitle();
        if (test1.equals("Guru99 Bank Manager HomePage")) {
            System.out.println("testCase1 Pass");
        }
        else
           System.out.println("testCase1 Fail");
    }

    //Verify if a user cannot log in with a valid username and an invalid password
    @Test (priority = 1)
    public void testCase2(){
        driver.navigate().to("https://www.demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("mngr473471");
        driver.findElement(By.name("password")).sendKeys("13456");
        driver.findElement(By.name("btnLogin")).click();
        String test2 = driver.switchTo().alert().getText();
        if (test2.equals("User or Password is not valid")){
            System.out.println("testCase2 Pass");
        }
        else
            System.out.println("testCase2 Fail");
        driver.switchTo().alert().accept();
    }

    //Verify if a user cannot log in with an invalid username and a valid password
    @Test (priority = 2)
    public void testCase3(){
        driver.navigate().to("https://www.demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("username");
        driver.findElement(By.name("password")).sendKeys("uhyvUpY");
        driver.findElement(By.name("btnLogin")).click();
        String test3 = driver.switchTo().alert().getText();
        if (test3.equals("User or Password is not valid")){
            System.out.println("testCase3 Pass");
        }
        else
            System.out.println("testCase3 Fail");
        driver.switchTo().alert().accept();
    }

    //Verify that an error message appears when leave the ID field is blank
    @Test (priority = 3)
    public void testCase4(){
        driver.navigate().to("https://www.demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        String test4 = driver.findElement(By.cssSelector("#message23")).getText();
        if (test4.equals("User-ID must not be blank")){
            System.out.println("testCase4 Pass");
        }
        else
            System.out.println("testCase4 Fail");
    }

    //Verify that an error message appears when leave the Password field is blank
    @Test (priority = 4)
    public void testCase5(){
        driver.navigate().to("https://www.demo.guru99.com/V4/");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.name("uid")).sendKeys("");
        String test5 = driver.findElement(By.cssSelector("#message18")).getText();
        if (test5.equals("Password must not be blank")){
            System.out.println("testCase5 Pass");
        }
        else
            System.out.println("testCase5 Fail");
    }

    //Verify the Reset functionality is working good, and it is clears the UserID & Password fields
    @Test (priority = 5)
    public void testCase6(){
        driver.navigate().to("https://www.demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("mngr473471");
        driver.findElement(By.name("password")).sendKeys("uhyvUpY");
        driver.findElement(By.name("btnReset")).click();
        if (driver.findElement(By.name("uid")).getText().equals("") && driver.findElement(By.name("password")).getText().equals("")) {
            System.out.println("testCase6 Pass");
        }
        else
            System.out.println("testCase6 Fail");
    }
}
