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
public class BankingAppDeleteAccountTestScript {

	protected transient final Log log = LogFactory.getLog(getClass());
	private List<WebElementDetails> bankingAppDeleteAccountWebElementList = null;
	private List<WebElementDataDetails> webElementsData = null;
	private TestCaseDetails testCaseDetails = null;
	BankingAppAccount bankingAppDeleteAccount;
	AppUtil apt = new AppUtil();
	TestSuiteDetails suiteDetails;

	// ==========================================================================

	public BankingAppDeleteAccountTestScript(TestSuiteDetails testSuiteDetails) {

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
		testCaseDetails.setTestCaseName(BankingAppDeleteAccountTestScript.class.getSimpleName());
		if (bankingAppDeleteAccountWebElementList == null) {
			bankingAppDeleteAccountWebElementList = JXMLParser.getInstance().getWebElements(BankingAppAccount.class.getSimpleName());
		}

		if (webElementsData == null) {
		      webElementsData = JExcelParser.getInstance().getDataSet(BankingAppAccount.class.getSimpleName(), BankingAppDeleteAccountTestScript.class.getSimpleName());
		   }
		
	}

	// ==========================================================================

	@Test(description = "Adding new Group time cycle record")
	public void deleteBankingAccount() {
		log.info("START of the method addNewbankingAppDeleteAccountRecord");
		bankingAppDeleteAccount = new BankingAppAccount();
		String customMessage = null;
		WebDriver driver = null;
		
		try {
			testCaseDetails.setMethodName(AppUtil.getMethodName());
			setPrerequisites();
			driver = JWebDriver.getInstance().getWebDriver();
			ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
			driver.get(resourceBundle.getString("application.url"));
			customMessage = "Click on Add button";
			WebElementDetails userMenuobj = bankingAppDeleteAccountWebElementList.get(0);
			bankingAppDeleteAccount.userMenu(userMenuobj).click();
			Thread.sleep(6000);

			if (bankingAppDeleteAccountWebElementList != null && bankingAppDeleteAccountWebElementList.size() > 0) {
				if (webElementsData!=null) {
					for (WebElementDataDetails webElementDataDetails : webElementsData) {
						if (webElementDataDetails.isExecute()) {
							List<String> dataSet = webElementDataDetails.getDataSet();
							if (dataSet != null && dataSet.size() > 0) {
									Actions act = new Actions(driver);
									Thread.sleep(10000);

									customMessage = "select all records from look-up";
									WebElementDetails lookupTableObj = bankingAppDeleteAccountWebElementList.get(8);
									WebElement lookupTable = bankingAppDeleteAccount.lookup(lookupTableObj); 
									Thread.sleep(1000);
									WebElementDetails rowsObj = bankingAppDeleteAccountWebElementList.get(9);
									int selctedRecordNo = 0;
									List<WebElement> rows = lookupTable
											.findElements(By.cssSelector(rowsObj.getCssSelector()));
									String DomainId = null;
									JavascriptExecutor js = (JavascriptExecutor) driver;
									for (int i = 1; i < rows.size(); i++) {
										  if ((i % 10) == 0)
										  {
											    js.executeScript("window.scrollBy(0,1000)");
											   Thread.sleep(3000);
											  //  rows.get(i).click();
										  }		
										  List<WebElement> cols = rows.get(i).findElements(By.cssSelector("td"));
										  DomainId = cols.get(2).getText();
										  if ((DomainId).trim().equalsIgnoreCase(webElementDataDetails.getDataSet().get(0))) {
											/*	rows.get(i).findElement(By.cssSelector(".btn.btn-success.btn-xs")).click();
												Thread.sleep(4000);
												customMessage = "Dialog box Delete";
												WebElementDetails deletConfirmobj = bankingAppDeleteAccountWebElementList.get(38);
												bankingAppDeleteAccount.deleteUsrBtn(deletConfirmobj).click();
												Thread.sleep(4000);*/
												rows.get(i).findElement(By.cssSelector(".btn.btn-danger.btn-xs")).click();
												Thread.sleep(4000);
												customMessage = "Dialog box Delete";
												WebElementDetails deletConfirmobj = bankingAppDeleteAccountWebElementList.get(17);
												 bankingAppDeleteAccount.deleteUsrBtn(deletConfirmobj).click();
												Thread.sleep(4000);
												
												log.info(" Logout ");
												break;
											}
									}
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
