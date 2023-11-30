package testsuite;

import com.google.common.base.Verify;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

/*
Create a New Java Project with the Name amazon-test create BaseTest and TestSuite
and add all jars.
Automate Following Test.

1. Open the url https://www.amazon.co.uk/
2. Type "Dell Laptop" in the search box and press enter or click on search
   Button.
3. Click on the checkbox brand Dell on the left side.
4. Verify that the  30(May be different) products are displayed on the page.
5. Print all product names in the console.
6. Click on the product name 'Dell XPS 15 9530 15.6" OLED 3.5K 400-Nit Touchscreen Laptop, 13th Gen Intel EVO i7-13700H Processor, 16GB RAM, 1TB SSD, NV...
Dell XPS 15 9530 15.6" OLED 3.5K 400-Nit Touchscreen Laptop, 13th Gen Intel EVO i7-13700H Processor, 16GB RAM, 1TB SSD, NVIDIA RTX 4060, Windows 11, Silver'
7. Varify the Product name 'Dell XPS 15 9530 15.6" OLED 3.5K 400-Nit Touchscreen Laptop, 13th Gen Intel EVO i7-13700H Processor, 16GB RAM, 1TB SSD, NV...
Dell XPS 15 9530 15.6" OLED 3.5K 400-Nit Touchscreen Laptop, 13th Gen Intel EVO i7-13700H Processor, 16GB RAM, 1TB SSD, NVIDIA RTX 4060, Windows 11, Silver'
5. Close the Browser.

*/
public class AmazonTest extends Utility {
    String baseUrl = "https://www.amazon.co.uk/";

    @Before
    public void setUp() {
        openBrowser(baseUrl); // Open browser.
    }

    @Test
    public void findDelLaptop() throws InterruptedException {
        //click on search bar
        clickOnElement(By.xpath("//input[@class='a-button-input celwidget']"));

        //send text "Dell laptop" in search bar.
        sendTextToElement(By.xpath("//input[@id='twotabsearchtextbox']"), "Dell Laptop");

        //click on submit button.
        clickOnElement(By.xpath("//input[@id='nav-search-submit-button']"));

        //click on dell check box
        clickOnElement(By.xpath("//li[@id='p_89/Dell']//i[@class='a-icon a-icon-checkbox']"));
        Thread.sleep(1000);

        //Verify that 24 products are displayed on the page
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2//a"));
        int expectedMessage = 24;
        int actualMessage = products.size();
        Assert.assertEquals(expectedMessage, actualMessage);
        //Print all product names in the console.
        System.out.println("Name of the Products: ");
        for (WebElement productText : products) {
            System.out.println("");
            System.out.println(productText.getText());
        }
        Thread.sleep(1000);
        while (true) {
            WebElement element = driver.findElement(By.xpath("//span[contains(text(),'XPS 15 9530 15.6\" OLED 3.5K 400-Nit Touchscreen La')]"));
            boolean status = element.isDisplayed();
            if (status) {
                // click on required product name
                clickOnElement(By.xpath("//span[contains(text(),'XPS 15 9530 15.6\" OLED 3.5K 400-Nit Touchscreen La')]"));
                break;
            } else {
                //Click on next if required product is not found.
                clickOnElement(By.xpath("//a[normalize-space()='Next']"));
            }
            Thread.sleep(1000);
            //verify the text displayed.
            String expectedMessage1 = "Dell XPS 15 9530 15.6\" OLED 3.5K 400-Nit Touchscreen Laptop";
            String actualMessage1 = getTextFromElement(By.xpath("//span[@id='productTitle']"));
            String[] actMessage = actualMessage1.split(",");
            Assert.assertEquals(expectedMessage1, actMessage[0]);
        }
    }

    @After
    public void tearDown() {
        closeBrowser(); // close browser.
    }
}


