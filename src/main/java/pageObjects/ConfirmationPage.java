package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
	
	public WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By ConfirmationMessage = By.xpath("//h1[@class='hero-primary']");
	
	public String getConfirmationMessage()
	{
		return driver.findElement(ConfirmationMessage).getText();
	}

}
