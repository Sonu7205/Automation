package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import resources.Base;

public class StepDefinition extends Base {
	public LandingPage lp;
	public ProductCatalogue pc;
	public CartPage cart;
	public CheckoutPage cp;
	public ConfirmationPage cmp;
	
	@Given("I landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws IOException
	{
		driver = initializeDriver();  //this step initialize driver and go to the landing page
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")   //While using regular expression to that extracts data, the string starts with ^ and ends with $
	public void logged_in_username_and_password(String username, String password)
	{
		lp = new LandingPage(driver);
		lp.Email().sendKeys(username);
		lp.Password().sendKeys(password);
		lp.Login();
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName)
	{
		pc = new ProductCatalogue(driver);
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(products, productName);
		pc.goToCart();
	}
	
	@When("^Checkout (.+) and submit the order$")   //even @And can be used here
	public void checkout_and_submit_order(String productName)
	{
		cart = new CartPage(driver);
		Boolean match = cart.verifyCartProducts(productName);
		Assert.assertTrue(match);
		cart.goToCheckout();
		
		cp = new CheckoutPage(driver);
		cp.addPersonalInformation();
		cp.shippingInformation(prop.getProperty("Country"));
		cp.placeOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_on_ConfirmationPage(String string)
	{
		cmp = new ConfirmationPage(driver);
		String confirmMessage = cmp.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		String errorMessage = lp.errorMessage();
		Assert.assertEquals(errorMessage, string);
	}

}
