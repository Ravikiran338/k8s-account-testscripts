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
import org.openqa.selenium.support.ui.Select;
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
public class BankingAppModifyAccountTestScript {

	protected transient final Log log = LogFactory.getLog(getClass());
	private List<WebElementDetails> bankingAppModifyAccountWebElementList = null;
	private List<WebElementDataDetails> webElementsData = null;
	private TestCaseDetails testCaseDetails = null;
	BankingAppAccount bankingAppModifyAccount;
	AppUtil apt = new AppUtil();
	TestSuiteDetails suiteDetails;

	// ==========================================================================

	public BankingAppModifyAccountTestScript(TestSuiteDetails testSuiteDetails) {

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
		testCaseDetails.setTestCaseName(BankingAppModifyAccountTestScript.class.getSimpleName());
		if (bankingAppModifyAccountWebElementList == null) {
			bankingAppModifyAccountWebElementList = JXMLParser.getInstance().getWebElements(BankingAppAccount.class.getSimpleName());
		}

		if (webElementsData == null) {
		      webElementsData = JExcelParser.getInstance().getDataSet(BankingAppAccount.class.getSimpleName(), BankingAppModifyAccountTestScript.class.getSimpleName());
		}
	}

	// ==========================================================================

	@Test(description = "Adding new Group time cycle record")
	public void modifyBankingAccount() {
		log.info("START of the method addNewbankingAppModifyAccountRecord");
		bankingAppModifyAccount = new BankingAppAccount();
		String customMessage = null;
		WebDriver driver = null;
		try {
			testCaseDetails.setMethodName(AppUtil.getMethodName());
			setPrerequisites();
			driver = JWebDriver.getInstance().getWebDriver();
			ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
			driver.get(resourceBundle.getString("application.url"));
			
			customMessage = "Click on Add button";
			WebElementDetails userMenuobj = bankingAppModifyAccountWebElementList.get(0);
			bankingAppModifyAccount.userMenu(userMenuobj).click();
			Thread.sleep(6000);
			
			if (bankingAppModifyAccountWebElementList != null && bankingAppModifyAccountWebElementList.size() > 0) {
				if (webElementsData!=null) {
					for (WebElementDataDetails webElementDataDetails : webElementsData) {
						if (webElementDataDetails.isExecute()) {
							List<String> dataSet = webElementDataDetails.getDataSet();
							if (dataSet != null && dataSet.size() > 0) {
									Actions act = new Actions(driver);
									Thread.sleep(10000);

									customMessage = "select all records from look-up";
									WebElementDetails lookupTableObj = bankingAppModifyAccountWebElementList.get(8);
									WebElement lookupTable = bankingAppModifyAccount.lookup(lookupTableObj); 
									Thread.sleep(1000);
									
									WebElementDetails rowsObj = bankingAppModifyAccountWebElementList.get(9);
									int selctedRecordNo = 0;
									List<WebElement> rows = lookupTable
											.findElements(By.cssSelector(rowsObj.getCssSelector()));
									String DomainId = null;
									JavascriptExecutor js = (JavascriptExecutor) driver;
									for (int i = 1; i < rows.size(); i++) {
										  if ((i % 10) == 0)
										  {
											    js.executeScript("window.scrollBy(0,1000)");
											    rows.get(i).click();
										  }		
										  List<WebElement> cols = rows.get(i).findElements(By.cssSelector("td"));
										  DomainId = cols.get(1).getText();
										  
										  if ((DomainId).trim().equalsIgnoreCase(webElementDataDetails.getDataSet().get(0))) {
												rows.get(i).findElement(By.cssSelector(".btn-primary.btn-xs")).click();
												break;
											}
									}

									customMessage = "Enter Account Type";
									WebElementDetails accountTypeTxtobj = bankingAppModifyAccountWebElementList.get(13);
									Select lst = new Select(bankingAppModifyAccount.accountTypeTxtbox(accountTypeTxtobj));
									lst.selectByVisibleText(webElementDataDetails.getDataSet().get(1));
									log.info(" Last Name");
									Thread.sleep(1000);									
									
									customMessage = "Cr3eate butnm";
									WebElementDetails createBtn = bankingAppModifyAccountWebElementList.get(14);
									bankingAppModifyAccount.createBtn(createBtn).click();
									log.info(" Enter Street ");
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
