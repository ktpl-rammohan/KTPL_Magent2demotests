package com.ktpl.headerSection;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.ktpl.framework_Parent.ParentBaseinit;
import com.ktpl.utitlityMethods.UtilityMethods;

public class BaseinitHeadersection extends ParentBaseinit {

	@BeforeSuite
	public void checkTestSuiteExecution() throws IOException{
	
		startup();
        logs.info("System checking execution status  of Header section module.......");

		
		if(!UtilityMethods.checkTestSuiteForExecution(suite, "headerSection", "TestSuite")){
			
			throw new SkipException("Execution mode of the test suite is set to NO");   //TestNG will skip execution of the method
		}

	}
	
	
	
	
}
