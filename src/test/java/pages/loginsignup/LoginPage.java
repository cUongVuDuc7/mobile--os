package pages.loginsignup;

import core.BasePage;
import core.DataBase;
import helpers.LogHelper;
import io.qameta.allure.Step;
import locator.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static constant.Constant.*;

public class LoginPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public DataBase dataBase ;
    public LoginPage() {
    }
    @Step("Hiển thị text ẩn {0}")
    public void checkHiddenText(By locator, String text){
        keyword.clearText(locator);
        keyword.sleep(0.3);
        keyword.assertEqual(locator, text);
    }
    @Step("Hiển thị pass ẩn")
    public void checkHiddenPass(String expect){
        keyword.sleep(0.3);
        keyword.click(Locator.LOGIN_BTN_HIDDEN_PASS);
        keyword.assertEqual(Locator.LOGIN_TXT_USER_NAME, expect);
    }
    @Step("Tài khoản đã logout")
    public void isUserLogout() {
        keyword.click(Locator.HOME_BTN_MENU);
        if (keyword.verifyElementPresent(Locator.PROFILE_BTN_VIEW_USER_INFORM)) {
            viewUserInform();
            logOut("Thành công");
        }
        else {
            keyword.click(Locator.HOME_BTN_HOME);
        }
    }
    public String getPhoneNumber(){
        String number = "0363";
        int ranNum = ThreadLocalRandom.current().nextInt(100000,999999);
        logger.info("Get phone number " + ranNum);
        return number + ranNum;
    }
    @Step("Vào màn hình đăng nhập")
    public void goToLogin() {
        logger.info("goToLogin ");
        keyword.click(Locator.HOME_BTN_MENU);
        keyword.click(Locator.LOGIN_BTN);
    }
    @Step("Click quay lại")
    public void goBack(By by) {
        keyword.click(by);
    }
    @Step("Đăng nhập thành công")
    public void login(String phoneNumber, String passWord) {
        logger.info("loginSuccess ");
        inputUserName(phoneNumber);
        inputPassWord(passWord);
        isMore3Devices();
    }
    @Step("Nhập số điện thoại: {0}")
    public void inputUserName(String name){
        logger.info("inputUserName ");
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_USER_NAME, name);
        if(keyword.verifyElementPresent(Locator.DEVICE_IOS_KEYBOARD_DONE)){
            keyword.click(Locator.DEVICE_IOS_KEYBOARD_DONE);
        }
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Step("Nhập mật khẩu: {0}")
    public void inputPassWord(String pass){
        logger.info("inputPassWord ");
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_PASSWORD,pass);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    public void compareMessLoginSuccess(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_SUCCESS, 10);
        keyword.assertEqual(Locator.LOGIN_TOAST_SUCCESS, MESSAGE_SUCCESS_LOGIN);
    }
    public void compareMessLoginIncorrectPass(String phone){
        keyword.assertEqual(Locator.LOGIN_TOAST_INCORRECT_PASSWORD, MESSAGE_FAIL_LOGIN + phone);
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_INCORRECT_PASSWORD,10);
    }
    public void isMore3Devices(){
        if(keyword.verifyElementPresent(Locator.NOTICE_LBL_MORE_3_DEVICE))
        {
            keyword.assertEqual(Locator.NOTICE_LBL_MORE_3_DEVICE, MESS_NOTICE_MORE_3_DEVICES);
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
        }
    }
    @Step("Xem thông tin tài khoản")
    public void viewUserInform(){
        logger.info("viewUserInform ");
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        keyword.click(Locator.PROFILE_BTN_VIEW_USER_INFORM);
    }
    @Step("Đăng xuất: {0}")
    public void logOut(String flag){
        logger.info("logOut ");
        keyword.click(Locator.LOGOUT_BTN);
        if(flag.equals("Thành công")) {
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_TOAST_SUCCESS, MESSAGE_SUCCESS_LOGOUT);
            keyword.webDriverWaitInvisibleElement(Locator.LOGOUT_TOAST_SUCCESS,15);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
    @Step("Đăng nhập qua smart Tv")
    public void logInSmartTv(String phone, String passWord){
        keyword.click(Locator.HOME_BTN_MENU);
        keyword.click(Locator.MENU_BTN_SMART_TV);
        inputUserName(phone);
        inputPassWord(passWord);
        compareMessLoginSuccess();
    }
    @Step("Hiển thị màn {0}")
    public void checkInformOfScreen(String flag) {
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        switch (flag) {
            case "Smart Tv, Website":
                keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, TITLE_LOGIN_SMART_TV);
                keyword.verifyElementPresent(Locator.LOGIN_SMART_TV_BTN_QR);
                keyword.verifyElementPresent(Locator.LOGIN_SMART_TV_LBL_OTP);
                keyword.verifyElementPresent(Locator.LOGIN_SMART_TV_LBL_INPUT_CODE);
                break;
            case "Quản lý thiết bị":
                keyword.assertEqual(Locator.LOGIN_LBL_DEVICE_MANAGE, TITLE_DEVICE_MANAGE);
                keyword.verifyElementPresent(Locator.DEVICE_MANAGE_LBL_3_DEVICE);
                keyword.verifyElementPresent(Locator.DEVICE_MANAGE_BTN_LOGOUT_DEVICE);
                keyword.verifyElementPresent(Locator.DEVICE_MANAGE_BTN_LAYOUT_DEVICE);
                break;
            case "Thông báo":
                keyword.assertEqual(Locator.SET_INFORM_LBL_TITLE, TITLE_INFORM_SETTING);
                keyword.verifyElementPresent(Locator.SET_INFORM_BTN_COMPLETE);
                break;
        }
        goBack(Locator.LOGIN_SMART_TV_BTN_BACK);
    }
    @Step("Nhập mã otp: {0} ")
    public void inputOtp(String otp){
        System.out.println("OTP " + otp);
        List<WebElement> weblist = keyword.getListElement(Locator.SIGN_UP_TXT_EDIT_OPT);
        System.out.println(weblist.size());
        for (int i = 0; i < weblist.size(); i++) {
            weblist.get(i).sendKeys(otp.split("")[i]);
        }
    }
    @Step("Nhấn tiếp tục xác thực otp: {0} ")
    public void continueOtp(String flag){
        if(flag.equals("hợp lệ")){
            keyword.webDriverWaitForElementPresent(Locator.LOGIN_TXT_PASSWORD,10);
        }
        else {
            keyword.assertEqual(Locator.SIGN_UP_TOAST_INCORRECT_OTP, MESSAGE_INVALID_OTP);
        }
    }
    @Step("Xóa otp")
    public void deleteOtp(){
        List<WebElement> weblist = keyword.getListElement(Locator.SIGN_UP_TXT_EDIT_OPT);
        if(!weblist.get(0).getText().equals("")) {
            for (int i = 0; i < weblist.size(); i++) {
                weblist.get(i).clear();
            }
        }
    }
    @Step("Đăng xuất khỏi thiết bị: {0}")
    public void logOutDevice(String option){
        logger.info("logOutDevice ");
        keyword.click(Locator.DEVICE_MANAGE_BTN_LAYOUT_DEVICE);
        keyword.click(Locator.DEVICE_MANAGE_BTN_LOGOUT_DEVICE);
        if (option.equals("Thành công")){
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_DEVICE_TOAST_SUCCESS, MESSAGE_LOGOUT_SUCCESS_DEVICE);
            keyword.webDriverWaitInvisibleElement(Locator.LOGOUT_DEVICE_TOAST_SUCCESS,10);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
    @Step("Đợi đến khi otp hết hạn")
    public void waitTimeOtp(){
        while (true){
            keyword.sleep(5);
            if(keyword.verifyElementPresent(Locator.SIGN_UP_BTN_RESEND_OTP)){
                break;
            }
            keyword.click(Locator.LBL_TIME_LIVE_EXPIRED_OTP);
        }
        keyword.assertEqual(Locator.LBL_TIME_EXPIRED_OTP, MESSAGE_EXPIRED_OTP);
    }
    @Step("Gửi lại mã opt")
    public void resendOtp(){
        keyword.click(Locator.SIGN_UP_BTN_RESEND_OTP);
    }
}

