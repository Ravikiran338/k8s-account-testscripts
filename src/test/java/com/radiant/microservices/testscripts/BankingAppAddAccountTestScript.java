/**
 * @author Subbarao 
 */
package com.radiant.microservices.testscripts;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.radiant.microservices.common.Constants;
import com.radiant.microservices.db.TAFDBManagerHelper;
import com.radiant.microservices.db.TestCaseDetails;
import com.radiant.microservices.db.TestSuiteDetails;
import com.radiant.microservices.exceptions.TAFException;
import com.radiant.microservices.model.WebElementDataDetails;
import com.radiant.microservices.model.WebElementDetails;
import com.radiant.microservices.pageobjects.BankingAppAccount;
import com.radiant.microservices.pageobjects.Login;
import com.radiant.microservices.util.AppUtil;
import com.radiant.microservices.util.JExcelParser;
import com.radiant.microservices.util.JWebDriver;
import com.radiant.microservices.util.JXMLParser;

@SuppressWarnings("deprecation")
public class BankingAppAddAccountTestScript {

	protected transient final Log log = LogFactory.getLog(getClass());
	private List<WebElementDetails> bankingAppAddAccountWebElementList = null;
	private List<WebElementDataDetails> webElementsData = null;
	private TestCaseDetails testCaseDetails = null;
	BankingAppAccount bankingAppAddAccount;
	AppUtil apt = new AppUtil();
	TestSuiteDetails suiteDetails;

	// ==========================================================================

	public BankingAppAddAccountTestScript(TestSuiteDetails testSuiteDetails) {

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
		testCaseDetails.setTestCaseName(BankingAppAddAccountTestScript.class.getSimpleName());
		if (bankingAppAddAccountWebElementList == null) {
			bankingAppAddAccountWebElementList = JXMLParser.getInstance().getWebElements(BankingAppAccount.class.getSimpleName());
		}
		if (webElementsData == null) {
		      webElementsData = JExcelParser.getInstance().getDataSet(BankingAppAccount.class.getSimpleName(), BankingAppAddAccountTestScript.class.getSimpleName());
		   }
	}

	// ==========================================================================

	@Test(description = "Adding new Banking Account record")
	public void addBankingAccount() throws Exception {
		log.info("START of the method addNewbankingAppAddAccountRecord");
		bankingAppAddAccount = new BankingAppAccount();
		String customMessage = null;
		WebDriver driver = null;
		
		try {
			testCaseDetails.setMethodName(AppUtil.getMethodName());
			setPrerequisites();
			driver = JWebDriver.getInstance().getWebDriver();
			ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
			driver.get(resourceBundle.getString("application.url"));
			customMessage = "Click on Add button";
			WebElementDetails userMenuobj = bankingAppAddAccountWebElementList.get(0);
			bankingAppAddAccount.userMenu(userMenuobj).click();
			Thread.sleep(6000);
			
			if (bankingAppAddAccountWebElementList != null && bankingAppAddAccountWebElementList.size() > 0) {
				if (webElementsData!=null) {
					for (WebElementDataDetails webElementDataDetails : webElementsData) {
						if (webElementDataDetails.isExecute()) {
							List<String> dataSet = webElementDataDetails.getDataSet();
							if (dataSet != null && dataSet.size() > 0) {
									Actions act = new Actions(driver);
									
									customMessage = "Click on Add button";
									WebElementDetails addBtnobj = bankingAppAddAccountWebElementList.get(1);
									bankingAppAddAccount.addUserbtn(addBtnobj).click();
									Thread.sleep(10000);
									
									customMessage = "Create buttonn";
									WebElementDetails CreateButnobj = bankingAppAddAccountWebElementList.get(6);
									bankingAppAddAccount.createBtn(CreateButnobj).click();
									log.info(" Enter Street ");
									Thread.sleep(1000);
									
									customMessage = "Enter Customer Id";
									WebElementDetails customerIdTxtobj = bankingAppAddAccountWebElementList.get(2);
									bankingAppAddAccount.customerIdTxtbox(customerIdTxtobj).click();
									bankingAppAddAccount.customerIdTxtbox(customerIdTxtobj).sendKeys(webElementDataDetails.getDataSet().get(0));
									log.info(" Enter First Name");
									Thread.sleep(1000);
									
									customMessage = "Enter Account Number";
									WebElementDetails accountNumberTxtobj = bankingAppAddAccountWebElementList.get(3);
									bankingAppAddAccount.accountNumberTxtbox(accountNumberTxtobj).click();
									bankingAppAddAccount.accountNumberTxtbox(accountNumberTxtobj).sendKeys(webElementDataDetails.getDataSet().get(1));
									log.info(" Enter First Name");
									Thread.sleep(1000);
									
									customMessage = "Enter Account Type";
									WebElementDetails accountTypeTxtobj = bankingAppAddAccountWebElementList.get(4);
									bankingAppAddAccount.accountTypeTxtbox(accountTypeTxtobj).click();
									bankingAppAddAccount.accountTypeTxtbox(accountTypeTxtobj).sendKeys(webElementDataDetails.getDataSet().get(2));
									log.info(" Last Name");
									Thread.sleep(1000);
									
									customMessage = "Enter Account Balance";
									WebElementDetails balanceTxtobj = bankingAppAddAccountWebElementList.get(5);
									bankingAppAddAccount.balanceTxtbox(balanceTxtobj).click();
									bankingAppAddAccount.balanceTxtbox(balanceTxtobj).sendKeys(webElementDataDetails.getDataSet().get(3));
									log.info(" Enter Middle Name");
									Thread.sleep(1000);

									customMessage = "Create butnm";
									 CreateButnobj = bankingAppAddAccountWebElementList.get(6);
									 bankingAppAddAccount.createBtn(CreateButnobj).click();
									 
									//
									log.info(" Enter Street ");
									Thread.sleep(1000);
									
									Thread.sleep(10000);
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
				e.printStackTrace();
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
