package runnerTest.webPages;

import Utils.BasePage;
import cucumber.api.java.eo.Se;
import cucumber.api.java.ro.Si;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class ElementUtil {

    /**
     *
     * @param locator
     * @return
     */

    public static WebElement webAction (final By locator){

        Wait<WebDriver> wait = new FluentWait<WebDriver>(BasePage.get())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .withMessage(
                        "Webdriver waited for 15 second still could not find the element therefore TimeOutException has been thrown"
                );
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(locator);
            }
        });
    }

    /**
     * click on method
     * @param locator
     */

    public void clickOn(By locator){
        webAction(locator).click();
    }

    /**
     * Send key method
     * @param locator
     * @param value
     */

    public void sendValue(By locator, String value){
        webAction(locator).sendKeys(value);
    }

    /**
     * Get Text method
     * @param locator
     * @return
     */
    public String getTextFromElement (By locator){
        return webAction(locator).getText();
    }

    /**
     * clear Method
     * @param locator
     */

    public void clear(By locator){
        webAction(locator).clear();
    }

    /**
     * is Displayed
     * @param locator
     * @return
     */
    public boolean isElementDisplayed (By locator){
        return webAction(locator).isDisplayed();
    }

    /**
     * is Selected
     * @param locator
     * @return
     */
    public boolean isElementSelected (By locator){
        return webAction(locator).isSelected();
    }

    /**
     * is Enabled
     * @param locator
     * @return
     */
    public boolean isElementEnabled (By locator){
        return webAction(locator).isEnabled();
    }

    public List<WebElement> webelements(By locator){
        return BasePage.get().findElements(locator);
    }

    /**
     * selectedFromDropDownText
     * @param locator
     * @param dropDownText
     */

    public  void selectedFromDropDownText(By locator,String dropDownText){
        WebElement text = webAction(locator);
        Select selectText = new Select(text);
        selectText.selectByVisibleText(dropDownText);
    }
    public  void selectedFromDropDownIndex(By locator,int dropDownIndex){
        WebElement text = webAction(locator);
        Select selectText = new Select(text);
        selectText.selectByIndex(dropDownIndex);
    }

    public void scrollByElement(By locator){
        WebElement scrollElement = webAction(locator);
        JavascriptExecutor js = (JavascriptExecutor)BasePage.get();
        js.executeScript("argument[0].scrollIntoView;",scrollElement);

    }

    /**
     * Scroll down by pixel
     * @param xPixel
     * @param yPixel
     */
    public void scrollByPixel(String xPixel, String yPixel){
        String str = "window.scroll("+xPixel +","+ yPixel +")";
        JavascriptExecutor js =(JavascriptExecutor) BasePage.get();
        js.executeScript(str);
    }
    public void moveToElement (By locator){
        Actions actions = new Actions(BasePage.get());
        actions.moveToElement(webAction(locator)).build().perform();
        }

    /**
     * getScreenShot
     * @param name
     * @return
     */
    public String getScreenShot(String name){
        SimpleDateFormat df = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
        String date = df.format(new Date());
        TakesScreenshot ts = (TakesScreenshot)BasePage.get();
       File source = ts.getScreenshotAs((OutputType.FILE));
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/"+ name + date + ".png";
        File finalDestination = new File (target);
        try{
            FileUtils.copyFile(source,finalDestination);

        }catch (IOException e){
            e.printStackTrace();
        }
        return target;



    }

}
