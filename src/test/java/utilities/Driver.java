package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
   private Driver(){

    }
    private static WebDriver driver;
    public static WebDriver getDriver(){


       if (driver==null){  //bu if sayesinde kod calisirken bir kere new keyword ile driver olusturulunca
                            //diger kullanimlarda new devreye girmicek
           switch (ConfigReader.getProperty("browser")){
               case "chrome":
                   WebDriverManager.chromedriver().setup();

                   ChromeOptions co=new ChromeOptions();
                   co.addArguments("--remote-allow-origins=*");

                   driver = new ChromeDriver(co);

                   break;
               case "firefox":
                   WebDriverManager.firefoxdriver().setup();
                   driver=new FirefoxDriver();
                   break;
               case "opera":
                   WebDriverManager.operadriver().setup();
                   driver=new OperaDriver();
                   break;
                   case "safari":
                       WebDriverManager.safaridriver().setup();
                       driver=new SafariDriver();
                       break;
               case "edge":
                   WebDriverManager.edgedriver().setup();
                   driver=new EdgeDriver();
                   break;
               default:
                   WebDriverManager.chromedriver().setup();
                   driver=new ChromeDriver();

           }

       }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
    public static void closeDriver(){
       if(driver!=null){
           driver.quit();
           driver = null;
       }
    }
}
