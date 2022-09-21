package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderHistoryPage {
	public WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By Orders = By.cssSelector("[routerlink*='myorders']");
	By Products = By.xpath("//tr/td[2]");
	
	public void goToOrders()
	{
		driver.findElement(Orders).click();
	}
	
	public Boolean orderValidation() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		List<WebElement> productHistory = driver.findElements(Products);
		Boolean match = productHistory.stream().anyMatch(product-> product.getText().equalsIgnoreCase(prop.getProperty("ProductName")));
		return match;
	}

}
