package SeleniumAutomation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import resources.Base;

public class ErrorValidationTest extends Base {
	public WebDriver driver;
	
	@Test
	public void loginErrorValidation() throws IOException
	{
		driver = initializeDriver();
		
		//Going to landing page and logging in using credentials
		LandingPage lp = new LandingPage(driver);
		lp.Email().sendKeys(prop.getProperty("incorrectEmail"));
		lp.Password().sendKeys(prop.getProperty("incorrectPassword"));
		lp.Login();
		String errorMessage = lp.errorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
	
	@Test
	public void productErrorValidation() throws IOException
	{
		driver = initializeDriver();
		
		//Going to landing page and logging in using credentials
		LandingPage lp = new LandingPage(driver);
		lp.Email().sendKeys(prop.getProperty("emailId"));
		lp.Password().sendKeys(prop.getProperty("password"));
		lp.Login();
		
		//Adding product to cart and going to cart page
		ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(products, prop.getProperty("ProductName"));
		pc.goToCart();
		
		//Verifying cart products
		CartPage cp = new CartPage(driver);
		Boolean match = cp.verifyCartProducts("ZARA 3");
		Assert.assertFalse(match);
	}

}
