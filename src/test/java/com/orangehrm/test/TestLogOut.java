package com.orangehrm.test;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.orangehrm.pages.LogInPage;
import com.orangehrm.pages.LogOut;
import utility.ReadFromExcel;

public class TestLogOut extends CommonAPI {

    Logger LOG = LogManager.getLogger(TestLogOut.class.getName());
    ReadFromExcel read = new ReadFromExcel("C:\\Users\\munna\\InteliiJ\\abcd-GitHub-test-project\\data\\data.xlsx", "test data");
    String username= read.getCellValueForGivenHeaderAndKey("key","userName");
    String password= read.getCellValueForGivenHeaderAndKey("key","passWord");

    @Test
    public void logOutFromOrangehrmPage() throws InterruptedException {

        LogOut logOut = new LogOut(getDriver());
        LogInPage logInPage = new LogInPage(getDriver());
        String expectedHomePageTitle = "OrangeHRM";
        String actualHomePageTitle = getCurrentTitle();
        Assert.assertEquals(actualHomePageTitle,expectedHomePageTitle);
        LOG.info("land to orangehrm home page successfully");

        logInPage.typeUserName(username);
        logInPage.typePassword(password);
        logInPage.setClickOnLogInBtm();
        Thread.sleep(3000);

        logOut.setClickOnProfileBtn();
        Thread.sleep(3000);
        logOut.setClickOnLogOut();
        Thread.sleep(3000);
        String expectedUrl= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        Assert.assertEquals(getURL(),expectedUrl);
        LOG.info("log out validation success");


    }
}
