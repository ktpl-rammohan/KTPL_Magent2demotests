package com.ktpl.shoppingcart;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.ktpl.framework_Parent.ParentBaseinit;
import com.ktpl.utitlityMethods.UtilityMethods;

public class BaseinitShoppingcart extends ParentBaseinit {
	
	@BeforeSuite
	public void checkTestSuiteExecution() throws IOException{
	
		startup();
		if(!UtilityMethods.checkTestSuiteForExecution(suite, "shoppingcart", "TestSuite")){
			
			throw new SkipException("Execution mode of the test suite is set to NO");   //TestNG will skip execution of the method
		}

	}

}
