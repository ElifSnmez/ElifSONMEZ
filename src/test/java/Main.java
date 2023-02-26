import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;



public class Main {
    public static void main (String [] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.beymen.com/"); 
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://www.beymen.com/", driver.getCurrentUrl());
        Thread.sleep(2000);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        WebElement cookieAcceptance = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[2]/div/div/div[2]/div/div")));
        cookieAcceptance.click();
        WebElement genderWindowClose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[5]/div[2]/div/div[1]/button")));
        genderWindowClose.click();

        //Searchbox
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div/div[2]/div/div/div/input")));
        searchBox.click();
        searchBox.sendKeys("şort");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        Thread.sleep(2000);
        WebElement searchBoxWrite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div/div[2]/div/div/div/input")));
        searchBoxWrite.click();
        searchBoxWrite.sendKeys("gömlek");
        searchBox.sendKeys(Keys.ENTER);


        //Selection of Item
        WebElement selectShirt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[4]/div/div/div[1]/a/div[1]/div[3]")));
        selectShirt.click();
        String price1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div/ins")).getAttribute("textContent");
        WebElement selectSize = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[1]/div[2]/div[2]/div[3]/div/div/span[2]")));
        selectSize.click();
        WebElement addBasket = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div[1]/div[2]/div[2]/div[4]/button[1]")));
        addBasket.click();
        Thread.sleep(9000);
        WebElement goToBasket = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/a[3]/span")));
        goToBasket.click();
        String price2 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/span")).getAttribute("textContent");

        if (price1.equals(price2)) {
            System.out.println("Prices are equal");
        }
        else {
            System.out.println("Prices are not equal");
        }

        WebElement addShirtCombobox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div[1]/div[2]/ul[2]/li/div[2]/div/select")));
        addShirtCombobox.click();
        WebElement addShirt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[2]/div[1]/div[2]/ul[2]/li/div[2]/div/select/option[2]")));
        addShirt.click();
        Assert.assertEquals("2 adet", addShirt.getText());
        WebElement deleteBasket = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[1]/button")));
        deleteBasket.click();
        WebElement checkBasketIsEmpty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/div/div/strong")));
        Assert.assertEquals("SEPETINIZDE ÜRÜN BULUNMAMAKTADIR", checkBasketIsEmpty.getText());

          driver.quit();
   }
}
