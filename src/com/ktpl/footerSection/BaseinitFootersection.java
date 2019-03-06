package com.ktpl.footerSection;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.ktpl.framework_Parent.ParentBaseinit;
import com.ktpl.utitlityMethods.UtilityMethods;

public class BaseinitFootersection extends ParentBaseinit {
	@BeforeSuite
	public void checkTestSuiteExecution() throws IOException{
			
		startup();		
        logs.info("System checking execution status  of Footer section module.......");
		if(!UtilityMethods.checkTestSuiteForExecution(suite, "footerSection", "TestSuite")){			
			throw new SkipException("Execution mode of the test suite is set to NO");   //TestNG will skip execution of the method
		}
	}	
}
