package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='identifierId']")
    public WebElement identifierTextBox;

    @FindBy(id = "identifierNext")
    public WebElement identifierNextButton;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordTextBox;

    @FindBy(id = "passwordNext")
    public WebElement passwordNextButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void login(String identifier, String password){
        wait.until(ExpectedConditions.elementToBeClickable(identifierTextBox));
        identifierTextBox.sendKeys(identifier);
        identifierNextButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(passwordTextBox));
        passwordTextBox.sendKeys(password);
        passwordNextButton.click();
    }
}

