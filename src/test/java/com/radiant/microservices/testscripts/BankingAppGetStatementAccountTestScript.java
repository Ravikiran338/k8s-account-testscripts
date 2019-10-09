/**
 * @author Subbarao 
 */
package com.radiant.microservices.testscripts;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.radiant.microservices.db.TAFDBManagerHelper;
import com.radiant.microservices.db.TestCaseDetails;
import com.radiant.microservices.db.TestSuiteDetails;
import com.radiant.microservices.exceptions.TAFException;
import com.radiant.microservices.model.WebElementDataDetails;
import com.radiant.microservices.model.WebElementDetails;
import com.radiant.microservices.pageobjects.BankingAppAccount;

import com.radiant.microservices.util.AppUtil;
import com.radiant.microservices.util.JExcelParser;
import com.radiant.microservices.util.JWebDriver;
import com.radiant.microservices.util.JXMLParser;

@SuppressWarnings("deprecation")
public class BankingAppGetStatementAccountTestScript {

	protected transient final Log log = LogFactory.getLog(getClass());
	private List<WebElementDetails> bankingAppGetStatementAccountWebElementList = null;
	private List<WebElementDataDetails> webElementsData = null;
	private TestCaseDetails testCaseDetails = null;
	BankingAppAccount bankingAppGetStatementAccount;
	AppUtil apt = new AppUtil();
	TestSuiteDetails suiteDetails;

	// ==========================================================================

	public BankingAppGetStatementAccountTestScript(TestSuiteDetails testSuiteDetails) {

		this.suiteDetails = testSuiteDetails;
		testCaseDetails = new TestCaseDetails();
		testCaseDetails.setTestSuiteDetailsId(testSuiteDetails.getTestSuiteDetailsId());
	}


	// ==========================================================================

	@BeforeTest
	public void beforeTest() {
		log.info("START of the method beforeTest");
		log.info("END of the method beforeTest");
	}

	// ==========================================================================

	private void setPrerequisites() throws InterruptedException {
		testCaseDetails.setTestCaseName(BankingAppGetStatementAccountTestScript.class.getSimpleName());
		if (bankingAppGetStatementAccountWebElementList == null) {
			bankingAppGetStatementAccountWebElementList = JXMLParser.getInstance().getWebElements(BankingAppAccount.class.getSimpleName());
		}

		if (webElementsData == null) {
		      webElementsData = JExcelParser.getInstance().getDataSet(BankingAppAccount.class.getSimpleName(), BankingAppGetStatementAccountTestScript.class.getSimpleName());
		   }
		
	}

	// ==========================================================================

	@Test(description = "Adding new Group time cycle record")
	public void GetStatementBankingAccount() {
		log.info("START of the method addNewbankingAppGetStatementAccountRecord");
		bankingAppGetStatementAccount = new BankingAppAccount();
		String customMessage = null;
		WebDriver driver = null;
		
		try {
			testCaseDetails.setMethodName(AppUtil.getMethodName());
			setPrerequisites();
			driver = JWebDriver.getInstance().getWebDriver();
			ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
			driver.get(resourceBundle.getString("application.url"));
			customMessage = "Click on Add button";
			WebElementDetails userMenuobj = bankingAppGetStatementAccountWebElementList.get(0);
			bankingAppGetStatementAccount.userMenu(userMenuobj).click();
			Thread.sleep(6000);

			if (bankingAppGetStatementAccountWebElementList != null && bankingAppGetStatementAccountWebElementList.size() > 0) {
				if (webElementsData!=null) {
					for (WebElementDataDetails webElementDataDetails : webElementsData) {
						if (webElementDataDetails.isExecute()) {
							List<String> dataSet = webElementDataDetails.getDataSet();
							if (dataSet != null && dataSet.size() > 0) {
									Actions act = new Actions(driver);
									Thread.sleep(10000);
									JavascriptExecutor js = (JavascriptExecutor) driver;
									js.executeScript("window.scrollBy(0,3000)");
									
									customMessage = "Dialog box GetStatement";
									WebElementDetails accountNumObj = bankingAppGetStatementAccountWebElementList.get(20);
									bankingAppGetStatementAccount.GetStatementAccountBtn(accountNumObj).click();
									bankingAppGetStatementAccount.GetStatementAccountBtn(accountNumObj).clear();
									bankingAppGetStatementAccount.GetStatementAccountBtn(accountNumObj).sendKeys(webElementDataDetails.getDataSet().get(0));
									Thread.sleep(1000);
									 
						 		    customMessage = "Dialog box GetStatement";
									WebElementDetails deletConfirmobj = bankingAppGetStatementAccountWebElementList.get(21);
									bankingAppGetStatementAccount.GetStatementAccountBtn(deletConfirmobj).click();
									Thread.sleep(4000);
																		
									}
						   }	
						}

					} else {
						log.info(" Unable to execute the script Test data is empty");
					}
					
				} else {
					log.info(" Unable to execute the script as some or all the mandatory objects or values are null");
				}
			} catch (java.lang.AssertionError e) {
				suiteDetails.setTestStatusSuccess(false);
				testCaseDetails = new TAFException().handleException(e, testCaseDetails, customMessage);
			} catch (Exception e) {
				suiteDetails.setTestStatusSuccess(false);
				testCaseDetails = new TAFException().handleException(e, testCaseDetails, customMessage);
			} finally {
				TAFDBManagerHelper.getInstance().saveTestCaseDetails(testCaseDetails);
			}
			log.info("END of the method login");
		}

	// ==========================================================================

	@AfterTest
	public void afterTest() {
		log.info("START of the method afterTest");
		log.info("END of the method afterTest");
	}

	
	// ==========================================================================

	public String handleNavigationPageState() {
		
		String customMessage = "PASS";
				
		return customMessage;
		
	}

	// ==========================================================================

}
