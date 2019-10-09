/**
 * @author Jp
 *
 */
package com.radiant.microservices.testsuites;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.radiant.microservices.db.TAFDBManagerHelper;
import com.radiant.microservices.db.TestSuiteDetails;
import com.radiant.microservices.reports.ReportsManager;

import com.radiant.microservices.testscripts.BankingAppModifyAccountTestScript;
import com.radiant.microservices.testscripts.BankingAppAddAccountTestScript;
import com.radiant.microservices.testscripts.BankingAppDeleteAccountTestScript;
import com.radiant.microservices.testscripts.BankingAppGetStatementAccountTestScript;
import com.radiant.microservices.testscripts.LoginTestScript;
import com.radiant.microservices.testscripts.LogoutTestScript;

public class TNCTest {
	protected transient final Log log = LogFactory.getLog(getClass());
	private TestSuiteDetails testSuiteDetails = null;

	
	// ==========================================================================
	@Test
	public void tncTestSuite() throws Exception {
		log.info("START of the method tncTestSuite");
		long testSuiteDetailsId = 0;
		
		LoginTestScript loginTestScript = null;
		
		BankingAppAddAccountTestScript bankingAppAddAccountTestScript = null;
		BankingAppModifyAccountTestScript bankingAppModifyAccountTestScript = null;
		BankingAppDeleteAccountTestScript bankingAppDeleteAccountTestScript = null;
		BankingAppGetStatementAccountTestScript bankingAppGetStatementAccountTestScript = null;
		
		LogoutTestScript logoutTestScript = null;

		
			// Adding test Suite details and getting test suite ID
			testSuiteDetails = TAFDBManagerHelper.getInstance().saveTestSuiteDetails(getClass().getSimpleName());

			if (testSuiteDetails != null && testSuiteDetails.getTestSuiteDetailsId() > 0) {
				testSuiteDetailsId = testSuiteDetails.getTestSuiteDetailsId();
				
			  // Executing the Test script for Login
				 loginTestScript = new LoginTestScript(testSuiteDetails);
				 loginTestScript.login();
		     	
	        //  Executing the Test script for Banking Modify User Test Script
		     	 bankingAppAddAccountTestScript = new BankingAppAddAccountTestScript(testSuiteDetails);
		     	 bankingAppAddAccountTestScript.addBankingAccount();
		     	 
		     //  Executing the Test script for Banking Modify User Test Script
				 bankingAppModifyAccountTestScript = new BankingAppModifyAccountTestScript(testSuiteDetails);
				 bankingAppModifyAccountTestScript.modifyBankingAccount();
				 
		     //  Executing the Test script for Banking Modify User Test Script
				 bankingAppDeleteAccountTestScript = new BankingAppDeleteAccountTestScript(testSuiteDetails);
				 bankingAppDeleteAccountTestScript.deleteBankingAccount();

		     //  Executing the Test script for Banking Modify User Test Script
				 bankingAppGetStatementAccountTestScript = new BankingAppGetStatementAccountTestScript(testSuiteDetails);
				 bankingAppGetStatementAccountTestScript.GetStatementBankingAccount();
				 
			  // Executing the Test script for Logout
				 logoutTestScript = new LogoutTestScript(testSuiteDetails);
				 logoutTestScript.logout();
				 
				 if (!testSuiteDetails.isTestStatusSuccess()) {
						throw new Exception("Test case Failed");
					}

				 
			} 
	}

	// ==========================================================================

	@AfterTest
	public void sendReport() {
		ReportsManager.getInstance().sendTestReport(testSuiteDetails);
	}
	
	// ==========================================================================
}