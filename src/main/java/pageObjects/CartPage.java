package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	
	public WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	private By cartProducts = By.xpath("//*[@class='cartSection']/h3");
	private By checkout = By.cssSelector(".totalRow button");
	
	public Boolean verifyCartProducts(String ProductName)
	{
		List<WebElement> Products = driver.findElements(cartProducts);
		Boolean match = Products.stream().anyMatch(product-> product.getText().equalsIgnoreCase(ProductName));
		return match;
    }
	
	public void goToCheckout()
	{
		driver.findElement(checkout).click();
	}
}
