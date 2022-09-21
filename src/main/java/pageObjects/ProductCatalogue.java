package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);   //to initialize driver of AbstractComponent
		this.driver=driver;
	}
	
	private By Products = By.cssSelector(".mb-3");
	private By waitText = By.cssSelector("#toast-container");
	private By waitAdding = By.cssSelector(".ng-animating");
	private By cart = By.cssSelector("[routerlink ='/dashboard/cart']");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(Products);
		return driver.findElements(Products);
	}
	
	public void addProductToCart(List<WebElement> products, String ProductName)
	{
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToAppear(waitText);
		waitForElementToDisappear(waitAdding);
	}
	
	public void goToCart()
	{
		driver.findElement(cart).click();
	}
	
			
}
