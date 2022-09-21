package SeleniumAutomation;

import java.io.IOException;
import java.util.List;
import resources.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrderHistoryPage;
import pageObjects.ProductCatalogue;

public class SubmitOrderTest extends Base {
	public WebDriver driver;
	
	@Test
	public void submitOrder() throws IOException {
		
		driver = initializeDriver();
		
		//Going to landing page and logging in using credentials
		LandingPage lp = new LandingPage(driver);
		lp.Email().sendKeys(prop.getProperty("emailId"));
		lp.Password().sendKeys(prop.getProperty("password"));
		lp.Login();
		
		//Adding product to cart and going to cart page
		ProductCatalogue pc = new ProductCatalogue(driver);
		String ProductName = prop.getProperty("ProductName");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(products, ProductName);
		pc.goToCart();
		
		//Verifying cart products and going to checkout page
		CartPage cart = new CartPage(driver);
		Boolean match = cart.verifyCartProducts(ProductName);
		Assert.assertTrue(match);
		cart.goToCheckout();
		
		//Providing details in checkout page and going to confirmation page
		CheckoutPage cp = new CheckoutPage(driver);
		cp.addPersonalInformation();
		cp.shippingInformation(prop.getProperty("Country"));
		cp.placeOrder();
		
		//Verifying confirmation message
		ConfirmationPage cmp = new ConfirmationPage(driver);
		String confirmMessage = cmp.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistory() throws IOException
	{
		OrderHistoryPage op = new OrderHistoryPage(driver);
		op.goToOrders();
		Boolean match = op.orderValidation();
		Assert.assertTrue(match);
		driver.close();
	}

}
