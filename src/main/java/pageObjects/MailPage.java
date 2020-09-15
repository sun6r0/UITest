package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className="bog")
    public List<WebElement> listOfSubjectElements;

    @FindBy(xpath = "//div[@class='aic']/div/div")
    public WebElement writeButton;

    @FindBy(xpath = "//textarea[@name = 'to']")
    public WebElement toTextBox;

    @FindBy(xpath = "//input[@name = 'subjectbox']")
    public WebElement subjectTextBox;

    @FindBy(xpath = "//div[@role = 'textbox']")
    public WebElement messageTextBox;

    @FindBy(xpath = "//div[@class = 'dC']/div[1]")
    public WebElement sendButton;

    public MailPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public long getMailCountBySubject(String subject) {
        return listOfSubjectElements
                .stream()
                .filter(c -> c.getText().startsWith(subject))
                .count();
    }

    public void sendEmail(String email, String subject) throws InterruptedException {
        long mailCount = getMailCountBySubject(subject);
        writeButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(toTextBox));
        toTextBox.sendKeys(email);
        toTextBox.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(subjectTextBox));
        subjectTextBox.sendKeys(subject);
        wait.until(ExpectedConditions.elementToBeClickable(messageTextBox));
        messageTextBox.sendKeys("Найдено " + mailCount + " писем(ма).");
        sendButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Найдено " + mailCount + " писем(ма).')]")));;
    }
}