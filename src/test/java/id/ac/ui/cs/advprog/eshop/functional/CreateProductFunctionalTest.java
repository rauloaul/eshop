package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String createProductUrl;
    private String productListUrl;

    @BeforeEach
    void setupTest() {
        createProductUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        productListUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        // input product
        String productName = "Sorry Ye";
        int productQuantity = 5;

        // accessing chrome url and do createProduct
        driver.get(createProductUrl);

        WebElement inputName = driver.findElement(By.id("nameInput"));
        inputName.clear();
        inputName.sendKeys(productName);

        WebElement inputQuantity = driver.findElement(By.id("quantityInput"));
        inputQuantity.clear();
        inputQuantity.sendKeys(String.valueOf(productQuantity));

        WebElement submit = driver.findElement(By.className("btn"));
        submit.click();

        assertEquals(productListUrl, driver.getCurrentUrl());

        driver.get(productListUrl);

        // test
        WebElement testProductName = driver.findElement(By.xpath("//td[contains(text(), '" + productName + "')]"));
        WebElement testProductQuantity = driver.findElement(By.xpath("//td[contains(text(), '" + productQuantity + "')]"));
        assertTrue(testProductName.isDisplayed());
        assertTrue(testProductQuantity.isDisplayed());
        assertEquals(testProductName.getText(), productName);
        assertEquals(testProductQuantity.getText(), String.valueOf(productQuantity));
    }
}
