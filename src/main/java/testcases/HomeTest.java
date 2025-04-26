package testcases;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.HomePage;

public class HomeTest extends BaseClass{
	
	@Test
	public void t1() {
		logger.info("t1");
		HomePage hp=new HomePage();
		hp.signup(prop.getProperty("username"), prop.getProperty("password"));
		hp.validateTitle();
	}
	

}
