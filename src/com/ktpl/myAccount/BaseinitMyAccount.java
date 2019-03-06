package com.ktpl.myAccount;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.ktpl.framework_Parent.ParentBaseinit;
import com.ktpl.utitlityMethods.UtilityMethods;

public class BaseinitMyAccount extends ParentBaseinit {
	
	@BeforeSuite
	public void MAcheckTestSuiteExecution() throws IOException{
	
		startup();
		if(!UtilityMethods.checkTestSuiteForExecution(suite, "myAccount", "TestSuite")){
			
			throw new SkipException("Execution mode of the test suite is set to NO");   //TestNG will skip execution of the method
		}

	}
	
}

	


