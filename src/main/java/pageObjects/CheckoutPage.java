package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	public WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}
	
	private By months = By.cssSelector("select[class*='ddl']:nth-child(2)");
	private By dates = By.cssSelector("select[class*='ddl']:nth-child(3)");
	private By country = By.xpath("//*[@placeholder='Select Country']");
	private By countryWait = By.cssSelector(".ta-results");
	private By countrySelect = By.xpath("//button[contains(@class,'ta-item')][2]");
	private By placeOrder = By.cssSelector(".action__submit");
	
	public void addPersonalInformation()
	{
		expireDate(months,"04");
		expireDate(dates, "12");
	}
	
	public void shippingInformation(String Country)
	{
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(country), Country).build().perform();
		waitForElementToAppear(countryWait);
		driver.findElement(countrySelect).click();
	}
	public void placeOrder()
	{
		driver.findElement(placeOrder).click();
	}

}
