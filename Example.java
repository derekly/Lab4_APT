import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Example  {
    public static void main(String[] args) throws InterruptedException {
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new HtmlUnitDriver();

        simpleLoginTest(driver);
        temperatureTest(driver);
        radioButtonTest(driver);
        driver.quit();
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
//Testing Radio Buttons
    public static void radioButtonTest(WebDriver driver){
    	System.out.println("===========================================================================================================");
    	System.out.println("Testing radio buttons");
    	System.out.println("Clicking Austin Radio Button and submitting");
    	driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	driver.findElement(By.name("farenheitTemperature")).clear();
        driver.findElement(By.name("farenheitTemperature")).sendKeys("212");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.xpath("(//input[@name='city'])[1]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    	WebElement h2_Austin = driver.findElement(By.tagName("h2"));
    	System.out.println("Result: " + h2_Austin.getText() + "\n");
        
    	System.out.println("Clicking Berkley Radio Button and submitting");
    	driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	driver.findElement(By.name("farenheitTemperature")).clear();
        driver.findElement(By.name("farenheitTemperature")).sendKeys("212");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.xpath("(//input[@name='city'])[2]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    	WebElement h2_Berkley = driver.findElement(By.tagName("h2"));
    	System.out.println("Result: " + h2_Berkley.getText() + "\n");
    	
    	System.out.println("Clicking New York Radio Button and submitting");
    	driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	driver.findElement(By.name("farenheitTemperature")).clear();
        driver.findElement(By.name("farenheitTemperature")).sendKeys("212");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.xpath("(//input[@name='city'])[3]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    	WebElement h2_NewYork = driver.findElement(By.tagName("h2"));
    	System.out.println("Result: " + h2_NewYork.getText() + "\n");
    	
    	
    	System.out.println("===========================================================================================================");
    	System.out.println("END OF TESTS");
    }
    

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
//Testing temperature
    public static void temperatureTest(WebDriver driver){
    	System.out.println("===========================================================================================================");
    	System.out.println("Testing temperature measurement");
    	temperatureCheck("500.2345687564895156",driver);
    	temperatureCheck("212",driver);
    	temperatureCheck("-212",driver);
    	temperatureCheck("1000.47258",driver);
    	temperatureCheck("0",driver);
    	temperatureCheck("-32",driver);
    	
    	//According to the rules, these should not be valid as they use "E" floating point format
    	temperatureCheck(Float.toString(Float.MAX_VALUE),driver);
    	temperatureCheck(Float.toString(Float.MIN_VALUE),driver);
    	temperatureCheck("-"+Float.toString(Float.MAX_VALUE),driver);
    	
    	//These Should throw exception
    	temperatureCheck("EE",driver);
    	temperatureCheck("--32",driver);
    	
    	//Don't know if this passes
    	temperatureCheck("+32",driver);
    }
    
    public static void temperatureCheck(String number, WebDriver driver){
    	System.out.println("Inputting the word \"" + number + "\" as temperature and then clicking the submit button");
    	driver.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	driver.findElement(By.name("farenheitTemperature")).clear();
        driver.findElement(By.name("farenheitTemperature")).sendKeys(number);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        System.out.print("The current URL is "+ driver.getCurrentUrl() + "\n");
    	WebElement h2 = driver.findElement(By.tagName("h2"));
    	System.out.println(h2.getText() + "\n");
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
    
    //Tests to see if login works as intended
    public static void simpleLoginTest(WebDriver driver) throws InterruptedException{
    	//These should pass
    	System.out.println("The following tests should result in the phrase: \"Page title is: Online temperature conversion calculator\"\n");

    	loginCheck("andy   ","  apple ",driver);
    	loginCheck("Bob","bathtub",driver);
    	loginCheck("ChArLeY  ","   china",driver);
    	
    	System.out.println("Waiting for 10 seconds because the website will not allow users to login if they attempted (successfully or unsuccessfully) to do so in the past 10 seconds\n");
    	Thread.sleep(10000);
    	
    	loginCheck("andy","apple",driver);
    	loginCheck("bob","bathtub",driver);
    	loginCheck("charley","china",driver);
    	
    	System.out.println("Waiting for 10 seconds because the website will not allow users to login if they attempted (successfully or unsuccessfully) to do so in the past 10 seconds\n");
    	Thread.sleep(10000); 
    	
    	System.out.println("===========================================================================================================");
    	System.out.println("The following tests should result in the phrase: \"Page title is: Bad Login\"\n");
    	loginCheck("an dy   ","  apple ",driver);
    	loginCheck("andy   ","  hunter2 ",driver);
    	loginCheck("Bob","Bathtub",driver);
    	loginCheck("charley","   chin a",driver);

    }
    
    public static void loginCheck(String user, String password, WebDriver driver){
    	System.out.println("Inputting the word \"" + user + "\" as the username and \"" + password + "\" as the password then clicking the submit button");
    	driver.get("http://apt-public.appspot.com/testing-lab-login.html");
        driver.findElement(By.name("userId")).clear();
        driver.findElement(By.name("userId")).sendKeys(user);
        driver.findElement(By.name("userPassword")).clear();
        driver.findElement(By.name("userPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        System.out.println("Page title is: " + driver.getTitle() + "\n");
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
}


