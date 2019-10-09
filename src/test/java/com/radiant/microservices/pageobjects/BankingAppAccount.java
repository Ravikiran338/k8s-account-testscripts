 /**
 * @author Subbarao
 */

package com.radiant.microservices.pageobjects;

import org.openqa.selenium.WebElement;

import com.radiant.microservices.model.WebElementDetails;
import com.radiant.microservices.util.JWebElement;

public class BankingAppAccount {

	// ==========================================================================
	// This method creates object for user menu in HTML page
	public WebElement userMenu(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
	}

	
	// ==========================================================================
	// This method creates object for add User button in HTML page
	public WebElement addUserbtn(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
	}

		
	// ==========================================================================
	// This method creates object for customer Id details in HTML page
	public WebElement customerIdTxtbox(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
	}

	// ==========================================================================
	// This method creates object for Account Number details in HTML page
	public WebElement accountNumberTxtbox(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
	}

	// ==========================================================================
	// This method creates object for Account Type details in HTML page
	public WebElement accountTypeTxtbox(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
	}

	
	// ==========================================================================
	// This method creates object for balanceTxtbox details in HTML page
	public WebElement balanceTxtbox(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
	}

		
	// ==========================================================================
	// This method creates object for Create button button in HTML page
	public WebElement createBtn(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
		
	}
	
	// ==========================================================================
	// This method creates object for TestConnection button in HTML page
	public WebElement deleteUsrBtn(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
		
	}
			
	// ==========================================================================
	// This method creates object for TestConnection button in HTML page
	public WebElement closeBtn(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
		
	}
	
	// ==========================================================================
	// This method creates object for TestConnection button in HTML page
	public WebElement lookup(WebElementDetails webElementDetails) {
		return JWebElement.getWebElement(webElementDetails);
		
	}			
	
	
	// ==========================================================================
		// This method creates object for TestConnection button in HTML page
		public WebElement GetStatementAccountBtn(WebElementDetails webElementDetails) {
			return JWebElement.getWebElement(webElementDetails);
			
		}			

}
