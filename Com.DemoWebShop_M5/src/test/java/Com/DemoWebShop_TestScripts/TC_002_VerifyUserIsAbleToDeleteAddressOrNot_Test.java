package Com.DemoWebShop_TestScripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import Com.DemoWebShop_GenericUtility.BaseTest;
import Com.DemoWebShop_POM.AddressesPage;

public class TC_002_VerifyUserIsAbleToDeleteAddressOrNot_Test extends BaseTest {

	@Test
	public void deleteAddress() throws InterruptedException, EncryptedDocumentException, IOException {

		Thread.sleep(2000);
		webDriverUtility.javaScriptScrollToElement(driver, homePage.getAddressesBtn());
		homePage.getAddressesBtn().click();

		AddressesPage addressesPage = new AddressesPage(driver);
		addressesPage.getDeleteBtn().click();

		Thread.sleep(2000);
		webDriverUtility.alertAccept(driver);

	}

}
