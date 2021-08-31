package mainPage;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainPage extends Base {

    By sigInButton = By.xpath("//span[@class='icon-with-title header__icon-button']");
    By lcButton = By.xpath("//a[@href='/profile?section=personalData']");
    By login = By.id("authLogin");
    By getPassword = By.xpath("//button/span[text()='Получить код']");
    By authEmail = By.id("authEmail");
    By authName = By.id("authFullName");
    By consentButton = By.xpath("//label[@for='authPersonalDataAgreement']");
    By registerButton = By.xpath("//span[text()='Зарегистрироваться']");
    By authPassword = By.id("authCode");
    By authEmailPassword = By.xpath("//input[@id='authCode']");
    By authPhone = By.id("authPhone");
    By exitButton = By.xpath("//span[text()='Выйти']");
    By phoneFromSite = By.xpath("//span[@class='info-box-number']");
    By phoneFromSite2 = By.xpath("(//span[@class='info-box-number'])[2]");
    By mailFromSite = By.id("email_addr");


    //headers
    By sigInHeader = By.xpath("//a[@href='/profile?section=personalData']/span");
    By incorrectSigInHeader = By.xpath("//p[@class='message popup-auth__message message_error']");
    By incorrectCodeHeader = By.xpath("//p[text()='Необходимо указать код подтверждения']");
    By incorrectEmailHeader = By.xpath("//p[text()='Необходимо указать электронную почту']");
    By incorrectNameHeader = By.xpath("//p[text()='Необходимо указать имя']");
    By noConsentHeader = By.xpath("//p[@class='message popup-auth__message message_error']");
    By incorrectPhoneHeader = By.xpath("//p[text()='Необходимо указать телефон']");
    By sigOutHeader = By.xpath("//h3[text()='Вход или регистрация']");

    public MainPage(WebDriver driver) {
        super(driver);
    }


    //    ---Методы и хедеры--------
    public void clickOnConsentButton() {
        driver.findElement(consentButton).click();
    }

    public String getPhoneFromSite() {
        return driver.findElement(phoneFromSite).getAttribute("textContent");
    }

    public String getPhoneFromSite2() {
        return driver.findElement(phoneFromSite2).getAttribute("textContent");
    }

    public String getMailFromSite() {
        return driver.findElement(mailFromSite).getAttribute("textContent");
    }

    public void clickOnExitButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
    }

    public void clickOnSigInButton() {
        driver.findElement(sigInButton).click();
    }

    public void clickOnLcInButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(lcButton));
    }

    public void typeLogin(String phone) {
        driver.findElement(login).sendKeys(phone);
    }

    public void typeEmail(String email) {
        driver.findElement(authEmail).sendKeys(email);
    }

    public void typePhone(String phone) {
        driver.findElement(authPhone).sendKeys(phone);
    }

    public void typeName(String name) {
        driver.findElement(authName).sendKeys(name);
    }

    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void typePassword(String password) {
        driver.findElement(authPassword).sendKeys(password);
    }

    public void typeEmailPassword(String password) {
        driver.findElement(authEmailPassword).sendKeys(password);
    }

    public void clickOnGetPasswordButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(getPassword));
        driver.findElement(getPassword).click();
    }

    public MainPage sigInWithPhoneOrEmail(String phoneOrEmail) {
        this.clickOnSigInButton();
        this.typeLogin(phoneOrEmail);
        this.clickOnGetPasswordButton();
        return new MainPage(driver);
    }


    public void sigInWithPassword(String password) {
        this.typePassword(password);
        new MainPage(driver);
    }

    public MainPage registerWithPhoneNumber(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }

    public MainPage registerWithEmail(String password, String phone, String name) {
        this.typeEmailPassword(password);
        this.typePhone(phone);
        this.typeName(name);
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }

    public MainPage registerWithoutConsent(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnConsentButton();
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }

    public MainPage emailRegisterWithoutConsent(String password, String phone, String name) {
        this.typeEmailPassword(password);
        this.typePhone(phone);
        this.typeName(name);
        this.clickOnConsentButton();
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }


    public String getIncorrectSigInHeader() {
        return driver.findElement(incorrectSigInHeader).getText();
    }

    public String getSigInHeader() {
        return driver.findElement(sigInHeader).getAttribute("textContent");
    }

    public String getIncorrectEmailHeader() {
        return driver.findElement(incorrectEmailHeader).getText();
    }

    public String getIncorrectPhoneHeader() {
        return driver.findElement(incorrectPhoneHeader).getText();
    }

    public String getSigOutHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sigOutHeader));
        return driver.findElement(sigOutHeader).getText();
    }

    public String getIncorrectNameHeader() {
        return driver.findElement(incorrectNameHeader).getText();
    }

    public String getIncorrectCodeHeader() {
        return driver.findElement(incorrectCodeHeader).getText();
    }

    public String getNoConsentHeader() {
        return driver.findElement(noConsentHeader).getText();
    }


    //    ------------SQL---------------------
    public void deletePhone() {
        String query = "delete from user where login=+79501978905";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteEmail() {
        String query = "delete from user where login=+79500000000";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getPhonePassword() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = null;
        String query = "select code from user_authentication_code where id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String getPhonePasswordForLC() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = null;
        String query = "select code from user_authentication_code where user_id = 157982 and id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }


    public String getEmailPassword() {
        String code = null;
        String query = "select code from user_authentication_code where id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }


    public static void main(String[] args) {
        String query = "select code from user_authentication_code where phone=+79501978905 and id=(SELECT MAX(id) FROM user_authentication_code)";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                System.out.println(code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

    }

}
