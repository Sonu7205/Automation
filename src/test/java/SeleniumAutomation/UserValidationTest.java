package SeleniumAutomation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import resources.Base;

public class UserValidationTest extends Base {
	public WebDriver driver;
	
	@Test(dataProvider="getData", retryAnalyzer=Retry.class)
	public void userLogin(HashMap<String,String> input) throws IOException
	{
		driver = initializeDriver();
		
		//Going to landing page and logging in using credentials
		LandingPage lp = new LandingPage(driver);
		lp.Email().sendKeys(input.get("email"));
		lp.Password().sendKeys(input.get("password"));
		lp.Login();
		
		//Checking if credentials are correct
		if(lp.isCredentialsError())
		{
			Assert.fail("Incorrect email or password.");
		}
		
		//Adding product to cart and going to cart page
		ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(products, input.get("ProductName"));
		driver.close();
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "sonuprasad@gmail.com");
		map.put("password", "Sonu@7205");
		map.put("ProductName", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "new@gmail.com");
		map1.put("password", "newPassword");
		map1.put("ProductName", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};
	}

}
