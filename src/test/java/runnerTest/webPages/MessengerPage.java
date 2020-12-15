package runnerTest.webPages;

import org.openqa.selenium.By;

public class MessengerPage extends ElementUtil{
    private By messengerLink = By.linkText("Messenger");
    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signUpButton = By.id("loginbutton");
    private By errorMessage = By.xpath("//div[contains(text(),'Incorrect email or phone number')]");


    public void clickonMessengerLink(){
        clickOn(messengerLink);

    }

    public void enterEmail(String email){
        sendValue(emailField,email);
    }
    public void enterPassword(String password){
        sendValue(passwordField,password);
    }

    public void clickOnSignButton(){
        clickOn(signUpButton);
    }

    public  String getErrorMessage(){
        return getTextFromElement(errorMessage);
    }

}