package com.ktpl.productlistingpage;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.ktpl.framework_Parent.ParentBaseinit;
import com.ktpl.utitlityMethods.UtilityMethods;

public class Baseinitproductlistingpage extends ParentBaseinit {
	
	@BeforeSuite
	public void checkTestSuiteExecution() throws IOException{
	
		startup();
		
        /*logs.info("System checking execution status  of Product Listing section.......");

		if(!UtilityMethods.checkTestSuiteForExecution(suite, "Product", "TestSuite")){
			
			throw new SkipException("Execution mode of the test suite is set to NO");*/   //TestNG will skip execution of the method
		//}

	}

}
