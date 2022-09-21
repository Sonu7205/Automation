package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);      //to initialize driver of AbstractComponent
		this.driver=driver;
	}
	
	private By Email = By.id("userEmail");
	private By Password = By.id("userPassword");
	private By Login = By.id("login");
	private By Error = By.cssSelector(".toast-error");
	
	public WebElement Email()
	{
		return driver.findElement(Email);
	}
	
	public WebElement Password()
	{
		return driver.findElement(Password);
	}
	
	public void Login() 
	{
	    driver.findElement(Login).click();
	}
	
	public boolean isCredentialsError()
	{
		waitForElementToAppear(Error);
		return driver.findElement(Error).isDisplayed();
	}
	
	public String errorMessage()
	{
		waitForElementToAppear(Error);
		return driver.findElement(Error).getText();
	}


}
