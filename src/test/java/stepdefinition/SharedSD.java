package stepdefinition;

import Utils.BasePage;
import Utils.ConfigReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import runnerTest.webPages.ElementUtil;

public class SharedSD {

    // Hook class
    ElementUtil elementUtil = new ElementUtil();
    private static Logger logger =Logger.getLogger(SharedSD.class);

    @Before
    public void setUp(){
        logger.info("***************************************");
        logger.info(" Test setup method is starting .....!");
        ConfigReader.getProperty("browser");
        BasePage.get().get(ConfigReader.getProperty("url"));
        BasePage.get().manage().window().fullscreen();

    }
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            logger.error("Test is failed..!");
            byte [] screenshot = ((TakesScreenshot)BasePage.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
            elementUtil.getScreenShot("pic");
        } else {
            logger.info("Cleanup !");
            logger.info("Test completed ...!");
        }
        logger.info("****************************************");
        BasePage.closeBrowser();
    }

}
