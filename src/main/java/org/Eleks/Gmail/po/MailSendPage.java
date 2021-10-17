package org.Eleks.Gmail.po;


import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MailSendPage extends BasePage {
    //Page With email table/List
    public String emailSubject = "";
    private final Actions action = new Actions(webDriver);
    private String emailBodyForCheck = "";
    private Integer emailNumForDelete = 0;
    private String emailSubjectForDelete = "";
    private final String emailXpath = "//tr//td//span[@title]";
    private final String subjectOnEmailPageXpath = "//span[@class='bog']/span";

    private final By emailDataXpath = By.xpath(emailXpath);


    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private static WebElement mailCreateButton;

    @FindBy(xpath = "//*[@class='vO' and @name='to']")
    private WebElement sendToEmail;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement bodyOfMessage;

    @FindBy(xpath = "//input [@name='subjectbox' and @placeholder='Subject']")
    private WebElement subjectOfMessage;

    @FindBy(xpath = "//div[@class='dC']//*[@role='button']")
    private WebElement sendButton;

    @FindBy(xpath = "//table//tr[@role='row'][1]")
    private WebElement lastEmailFromTable;

    @FindBy(xpath = "//div[@role='button' and @data-tooltip='Delete']")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "//tr//td//span[@title]")
    private WebElement emailDataElement;

    @FindBy(xpath = "//span[@class='bog']/span")
    private WebElement subjectOnEmailPage;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement addFile;

    @FindBy(xpath = "//div[@role='button' and @data-tooltip='Download']")
    private WebElement downloadFile;

    public MailSendPage() {
    }

    public WebElement getBodyOfMessage() {
        return bodyOfMessage;
    }

    public WebElement getSendButton() {
        return sendButton;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public WebElement getSubjectOfMessage() {
        return subjectOfMessage;
    }

    public void setEmailBodyForCheck(String emailBodyForCheck) {
        this.emailBodyForCheck = emailBodyForCheck;
    }

    public String getEmailBodyForCheck() {
        return emailBodyForCheck;
    }

    public WebElement getSendToEmail() {
        return sendToEmail;
    }

    public String getEmailSubjectForDeleteText() {
        return emailSubjectForDelete;
    }

    public String getSubjectOnEmailPageXpath() {
        return subjectOnEmailPageXpath;
    }

    public By getEmailDataXpath() {
        return emailDataXpath;
    }

    public WebElement getEmailDataElement() {
        return emailDataElement;
    }

    public static WebElement getMailCreateButtonForCheck() {
        return mailCreateButton;
    }

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(12);
    }

    public String getAbsolutePath(String relativePath) {
        return FileSystems.getDefault().getPath(relativePath).normalize().toAbsolutePath().toString();
    }

    public void downloadFile() {
        waitForElement(downloadFile, 10);
        downloadFile.click();
    }

//    //todo винести в  ВО
//    @Step("Send test email with attachment")
//    //send email with random body and subject and CC
//    public void sendEmail(String testEmailText, List<String> sendToOrCC, String pathToFile) {
//        goToEmailSendForm();
//        waitForElement(sendToEmail, 10);
//        for (String emails : sendToOrCC) {
//            sendToEmail.sendKeys(emails);
//            sendToEmail.sendKeys(Keys.ENTER);
//        }
//        emailSubject = subjectOfMessage.getAttribute("aria-label") + " " + generateRandomString();
//        subjectOfMessage.sendKeys(emailSubject);
//        //for verification of message body
//        emailBodyForCheck = generateRandomString();
//        bodyOfMessage.sendKeys(emailBodyForCheck);
//        attachFile(pathToFile);
//        sendButton.click();
//    }


//    @Step("Send test email")
//    //send email with random body and subject
//    public void sendEmail(String testEmailText, String emailAddress) {
//        goToEmailSendForm();
//        waitForElement(sendToEmail, 10);
//        sendToEmail.sendKeys(emailAddress);
//        emailSubject = subjectOfMessage.getAttribute("aria-label") + " " + generateRandomString();
//        subjectOfMessage.sendKeys(emailSubject);
//        //for verification of message body
//        emailBodyForCheck = generateRandomString();
//        bodyOfMessage.sendKeys(emailBodyForCheck);
//        sendButton.click();
//    }

    //send deff email
//    @Step("Send test email")
//    public void sendEmail(String testEmailText, String emailAddress) {
//        goToEmailSendForm();
//        waitForElement(sendToEmail, 10);
//        sendToEmail.sendKeys(emailAddress);
//        emailSubject = subjectOfMessage.getAttribute("aria-label") + " " + generateRandomString();
//        subjectOfMessage.sendKeys(emailSubject);
//        bodyOfMessage.sendKeys(testEmailText);
//        sendButton.click();
//    }

    @Step("Go to email page")
    public void goToEmailPage() {
        pauseSec(1);
        waitForElement(lastEmailFromTable, 10);
        lastEmailFromTable.click();
        new EmailPage();
    }


    //    @Step("Check email sorting")
//    public void checkEmailOrder() {
//        ArrayList<String> defOrderEmailList = new ArrayList<>();
//        waitForElement(emailDataElement, 10);
//        List<WebElement> emailsList = webDriver.findElements(emailDataXpath);
//        for (WebElement date : emailsList) {
//            defOrderEmailList.add(convertDate(date.getAttribute("title")));
//        }
//        ArrayList<String> sortedList = new ArrayList<>(defOrderEmailList);
//        Collections.sort(sortedList, Collections.reverseOrder());
//        Assert.assertEquals(sortedList, defOrderEmailList,
//                "Email Sorting is incorrect");
//    }

    public static String getPathToFile(String pathToFile) {
        return String.valueOf(Paths.get(pathToFile));
    }

    //    //todo винести в  ВО
//    public static void filesComparing(String pathToFile1, String pathToFile2) {
//        pauseSec(2);
//        Path path1 = getPathToFile(pathToFile1);
//        Path path2 = getPathToFile(pathToFile2);
//        try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(path1.toFile()));
//             BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(path2.toFile()))) {
//
//            if (fis1.equals(fis2)) {
//                LOGGER.info("Files are the same");
//            } else {
//                LOGGER.warn("The files are different");
//                Assert.fail("The files are different");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    //todo винести в  ВО
//    @Step("Check email sorting")
//    public void checkEmailOrder() {
//        ArrayList<LocalDateTime> defOrderEmailList = new ArrayList<>();
//        waitForElement(emailDataElement, 10);
//        List<WebElement> emailsList = webDriver.findElements(emailDataXpath);
//        for (WebElement date : emailsList) {
//            defOrderEmailList.add(DateTimeHelper.parseDateTime(date.getAttribute("title")));
//        }
//        ArrayList<LocalDateTime> sortedList = new ArrayList<>(defOrderEmailList);
//        sortedList.sort(Collections.reverseOrder());
//        Assert.assertEquals(sortedList, defOrderEmailList,
//                "Email Sorting is incorrect");
//
//    }
//
//    //todo винести в  ВО
    @Step("check If Email Is Deleted By Subject")
    public void checkIfEmailIsDeletedBySubject() {
//        Subject nclXsapqDkno
//       Subject uUKTNJMiJKGl
//       Subject cvIGEWwoGmbt
//       Subject sdvbnSLBlWsR
//        Subject VhNjINOkyYzq
        setEmailSubjectForDelete("nclXsapqDkno");
        action.moveToElement(getWebElementByXpath("/html/body"));
        List<WebElement> subjectList = webDriver.findElements(By.xpath(subjectOnEmailPageXpath));
        List<String> subjectTextList = new ArrayList<>();
        waitForElement(emailDataElement, 10);

        for (WebElement element : subjectList) {
            if (element.getText().equals(emailSubjectForDelete)) {
                Assert.fail("Email deleting by SUBJECT failed!!!! \n email didn't DELETED or the email with the similar title is present");
            }
        }
    }

    @Step("Check if email is deleted")
    public void checkIfEmailIsDeleted(String latestEmailTime, String deleteEmailTime) {
        Assert.assertNotEquals(latestEmailTime, deleteEmailTime,
                "The latest email is not deleted");
    }

    public void setEmailSubjectForDelete(String emailSubjectForDelete) {
        this.emailSubjectForDelete = emailSubjectForDelete;
    }

    @Step("Attach test file")
    public void attachFile(String relativePathToFile) {
//        attach test file
//        waitForElement(addFile, 10);
        addFile.sendKeys(getAbsolutePath(relativePathToFile));
    }

    public void goToEmailSendForm() {
        waitForElement(mailCreateButton, 10);
        mailCreateButton.click();
    }

    public void setEmailNumForDelete(Integer emailNumForDelete) {
        this.emailNumForDelete = emailNumForDelete;
    }


    @Step("Delete the selected email")
    public void deleteEmail() {
        action.contextClick(getEmailForDelete()).build().perform();
        waitForElement(deleteEmailButton, 10);
        action.moveToElement(deleteEmailButton).build().perform();
        deleteEmailButton.click();

    }

    @Step("Delete the selected email By Subject")
    public void deleteEmailBySubject() {
        action.contextClick(getEmailSubjectForDelete())
                .build()
                .perform();
        waitForElement(deleteEmailButton, 10);
        action.moveToElement(deleteEmailButton)
                .build()
                .perform();
        deleteEmailButton.click();

    }


    protected WebElement getEmailSubjectForDelete() {
        WebElement emailForDelete = null;
        List<WebElement> subjectList = webDriver.findElements(By.xpath(getSubjectOnEmailPageXpath()));
        waitForElement(emailDataElement, 10);

        for (WebElement element : subjectList) {
            if (element.getText().equals(emailSubjectForDelete)) {
                emailForDelete = element;
            }
        }
        return emailForDelete;
    }

    protected Integer getEmailNumForDelete() {
        return emailNumForDelete;
    }

    protected WebElement getEmailForDelete() {
        //use the selected email num for delete proper email
        String xpathForEmailDeleting = String.valueOf(new StringBuffer("//tr//td//span[@title]").insert(4,
                String.format("[%s]", Integer.toString(getEmailNumForDelete()))));
        return getWebElementByXpath("//tr[3]//td//span[@title]");
    }


}

