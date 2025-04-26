package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseClass.BaseClass;

public class HomePage extends BaseClass{

	
	
	
	
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath="//div[@text='tst']") WebElement txtuser;
	@FindBy(xpath="//input[contains(@class, 'text')]") WebElement txtpass ;
	@FindBy(xpath="//div//span[contains(text()='name')]") WebElement sub ;
	@FindBy(xpath="//span[starts-with(@type='name')]") WebElement dropdown;
	@FindBy(xpath="//spec[text()='value']") WebElement radio;
	@FindBy(xpath="//div//span[normalize-space()='man']") WebElement check ;
	@FindBy(xpath="//a//input[@type='name' and @type='name']") WebElement test ;
	@FindBy(xpath="//input[@class='name' or @id='name']") List<WebElement> best;
	//@FindBy(xpath="") WebElement  ;
	
	
	
	
	
	public void signup(String name, String pass) {
		txtuser.sendKeys(name);
		txtpass.sendKeys(pass);
		sub.click();
		
		Select slt=new Select(dropdown);
		slt.selectByVisibleText("indina");
		
		List<WebElement> options=slt.getOptions();
		
		for(int i=0; i<options.size(); i++) {
			String text=options.get(i).getText();
			
			if(text.contains("indina")|| text.contains("asian") && options.get(i).isEnabled()) {
				slt.selectByVisibleText("asian");
			}
		}
		
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf( check));
		wait.until(ExpectedConditions.elementToBeClickable(check));
	check.click();
	}
	
	
	
	public void validateTitle() {
		String title=driver.getTitle();
		Assert.assertEquals(title, "store");
		Assert.assertTrue(test.getText().contains("chinmaya"));
	}
}
