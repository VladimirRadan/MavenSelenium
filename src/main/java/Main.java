import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class Main {

    static WebDriver driver;

    static public WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    static void typeIn(By locator, String text){
        getElement(locator).sendKeys(text);
    }

    public static void main(String[] args) throws InterruptedException {

        By usernameFieldLocator = By.id("username");
        By passwordFieldLocator = By.id("password");
        By loginButtonLocator = By.cssSelector(".fa");


        driver = new ChromeDriver();//otvara browser

        //WebDriver driver = new FirefoxDriver();
        SoftAssert softAssert = new SoftAssert();

        Thread.sleep(3000);

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/login");

        //getElement(usernameFieldLocator).sendKeys("tomsmith");
        typeIn(usernameFieldLocator, "tomsmith");

        //getElement(passwordFieldLocator).sendKeys("SuperSecretPassword!");
        typeIn(passwordFieldLocator, "SuperSecretPassword!");

        getElement(loginButtonLocator).click();

        WebElement loggedInUserText = driver.findElement(By.cssSelector("div[class='flash success']"));

        String splitedActual[] = loggedInUserText.getText().split("(?<=!)");

        //String actual = loggedInUserText.getText().substring(0, loggedInUserText.getText().length()-1);

        String expectedText = "You logged into a secure area!";

        String actualText = loggedInUserText.getText();// = You logged into a secure area!×
        String attr = loggedInUserText.getAttribute("");

        //System.out.println(actual);

        String expectedColor = "rgba(93, 164, 35, 1)";

        String actualColor = loggedInUserText.getCssValue("background-color");

        System.out.println("Actual: " + actualColor);

        softAssert.assertEquals(actualColor,expectedColor, "Colors not matched!");
        softAssert.assertEquals(splitedActual[0], expectedText, "Text not matched!");
        softAssert.assertAll();


//        if (expectedText.equals(splitedActual[0].trim())){
//            System.out.println("Verified!");
//        }else {
//            System.out.println("Error!");
//        }

        //driver.close();
        driver.quit();
    }



}
