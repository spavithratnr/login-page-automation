package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage  {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="//a[@title=\"My Account\"]")
	WebElement myAccountDropdown;
	
	@FindBy(xpath="//a[text()=\"Login\"]")
	WebElement loginOption;
	
	public WebElement myAccountDropdown() {
		
		return myAccountDropdown;
		
	}
	
	public WebElement loginOption() {
		
		return loginOption;
		
	}

}

