package tests.loginsignup;

import core.BaseTest;
import core.DataBase;
import io.qameta.allure.Severity;
import locator.Locator;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProfilePage;
import pages.home.HomePage;
import pages.loginsignup.LoginPage;
import pages.loginsignup.SignUpPage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import static constant.Constant.*;
import static constant.Query.SPORTS_ID_QUERY_USER;
import static helpers.PathHelper.getNameMethod;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utilities.ReadExcel.*;

public class SignUpTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public SignUpPage signUpPage;
    private HashMap<String, String> dataSignUp;
    public ProfilePage profilePage;
    public HomePage homePage;
    private String phone = "";
    private static ResultSet res ;
    private static Statement stmt ;
    private static Connection con ;
    private static Sheet sh = null;
    public SignUpTest(){
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        dataBase = new DataBase();
        profilePage = new ProfilePage();
        homePage = new HomePage();
    }
    @BeforeClass
    public void firstSteps(){
        sh = readSheet(workbook , "SignUp");
        con = dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        stmt = dataBase.createStatement(con);
        homePage.skipBanner();
        loginPage.isUserLogout();
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra nhập sdt chưa đăng ký - Bỏ trống mã OTP")
    public void SU_1_2(){
        loginPage.goToLogin();
//        int indexRow = getIndexRowFromKey(sh,getNameMethod());
//        phone = loginPage.getPhoneNumber();
//        setCell(workbook, sh, phone, indexRow , getIndexCellFromKey(sh,"User name"));
//        dataSignUp = getTestDataInMap(sh, indexRow);
//        loginPage.inputUserName(dataSignUp.get("User name"));
    }
    @Test(priority = 1, description = "Kiểm tra nhập mã OTP sai" , dependsOnMethods = "SU_1_2")
    public void SU_3(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.inputOtp(dataSignUp.get("OTP"));
        loginPage.continueOtp("không tồn tại");
    }
    @Test(priority = 2, description = "Kiểm tra nhập mã OTP hợp lệ rồi back lại")
    public void SU_4(){
        loginPage.deleteOtp();
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, "SU_1_2"));
        res = dataBase.queryDb(stmt, SPORTS_ID_QUERY_USER.replace("key", dataSignUp.get("User name")));
        HashMap<String, String> dbData = dataBase.getResultDataBase(res);
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("hợp lệ");
        signUpPage.inFormSetPassWord();
        signUpPage.backFromOTPScreen();
        setCell(workbook , sh, dbData.get("otp_code"), getIndexRowFromKey(sh,getNameMethod()) ,getIndexCellFromKey(sh,"OTP"));
    }
    @Test(priority = 3, description = "Kiểm tra nhập mã OTP đã dùng", dependsOnMethods = "SU_4")
    public void SU_5(){
        HashMap<String, String> dbData = dataBase.getResultDataBase(res);
        loginPage.inputUserName(dataSignUp.get("User name"));
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("không tồn tại");
        setCell(workbook , sh, dbData.get("otp_code"), getIndexRowFromKey(sh,getNameMethod()) ,getIndexCellFromKey(sh,"OTP"));
    }
    @Test(priority = 4, description = "Kiểm tra nhập mã OTP đã hết hạn", dependsOnMethods = "SU_5")
    public void SU_6(){
        loginPage.waitTimeOtp();
        loginPage.deleteOtp();
        res = dataBase.queryDb(stmt, SPORTS_ID_QUERY_USER.replace("key", dataSignUp.get("User name")));
        HashMap<String, String> dbData = dataBase.getResultDataBase(res);
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("không tồn tại");
        setCell(workbook, sh, dbData.get("otp_code"), getIndexRowFromKey(sh, getNameMethod()) ,getIndexCellFromKey(sh,"OTP"));
    }
    @Severity(CRITICAL)
    @Test(priority = 5, description = "Kiểm tra gửi lại mã OTP và dùng mã OTP lần 1", dependsOnMethods = "SU_6")
    public void SU_7_8(){
        HashMap<String, String> dbData = dataBase.getResultDataBase(res);
        String oldOtp = dbData.get("otp_code");
        loginPage.resendOtp();
        setCell(workbook, sh, oldOtp, getIndexRowFromKey(sh, getNameMethod()) ,getIndexCellFromKey(sh,"OTP"));
        loginPage.inputOtp(oldOtp);
        loginPage.continueOtp("không tồn tại");
    }
    @Severity(CRITICAL)
    @Test(priority = 6, description = "Kiểm tra nhập mã OTP hợp lệ", dependsOnMethods = "SU_1_2")
    public void SU_9(){
        res = dataBase.queryDb(stmt, SPORTS_ID_QUERY_USER.replace("key", dataSignUp.get("User name")));
        HashMap<String, String> dbData = dataBase.getResultDataBase(res);
        loginPage.deleteOtp();
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("hợp lệ");
        setCell(workbook , sh, dbData.get("otp_code"), getIndexRowFromKey(sh, getNameMethod()) ,getIndexCellFromKey(sh,"OTP"));
    }
    @Test(priority = 7, description = "Kiểm tra nhập pass > 6 ký tự" , dependsOnMethods = "SU_9")
    public void SU_10(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.SET_INFORM_BTN_COMPLETE);
    }
    @Test(priority = 8, description = "Kiểm tra nhập pass = 6 ký tự")
    public void SU_11(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.SET_INFORM_BTN_COMPLETE);
    }
    @Test(priority = 9, description = "Kiểm tra nhập pass < 6 ký tự")
    public void SU_12(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        signUpPage.verifyMessPassWord(Locator.LOGIN_LBL_ERROR, MESSAGE_ERROR_PASS);
    }
    @Test(priority = 10, description = "Kiểm tra nhập pass chỉ gồm chữ hoặc số")
    public void SU_13(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.SET_INFORM_BTN_COMPLETE);
    }
    @Test(priority = 11, description = "Kiểm tra nhập pass chỉ gồm khoảng trắng")
    public void SU_14(){
//        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
//        signUpPage.setPassWord(dataSignUp.get("Pass word"), dataSignUp.get("Pass word"));
    }
    @Test(priority = 12, description = "Kiểm tra nhập pass gồm số, chữ, và ký tự đặc biệt, in hoa thường")
    public void SU_15(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.SET_INFORM_BTN_COMPLETE);
    }
    @Test(priority = 13, description = "Kiểm tra nhập pass khác pass nhập lại")
    public void SU_16(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        signUpPage.setPassWord(dataSignUp.get("Pass word"), dataSignUp.get("Password confirm"));
        signUpPage.verifyMessPassWord(Locator.SIGN_UP_LBL_ERROR, MESS_SIGN_UP_COMPARE_PASS);
    }
    @Test(priority = 14, description = "Kiểm tra nhập pass trùng pass nhập lại")
    public void SU_17(){
        dataSignUp = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        signUpPage.inputConfirmPassWord(dataSignUp.get("Password confirm"));
    }
    @Test(priority = 15, description = "Kiểm tra button show mật khẩu", dependsOnMethods = "SU_17")
    public void SU_18(){
        signUpPage.showPass(dataSignUp.get("Pass word"));
        signUpPage.showAgainPass(dataSignUp.get("Password confirm"));
    }
    @Test(priority = 16, description = "Kiểm tra button xóa mật khẩu", dependsOnMethods = "SU_18")
    public void SU_19(){
        signUpPage.clearPass();
        signUpPage.clearAgainPass();
    }
    @Test(priority = 17, description = "Kiểm tra thông tin user khi đăng ký thành công")
    public void SU_20(){
        signUpPage.setPassWord(dataSignUp.get("Pass word"), dataSignUp.get("Password confirm"));
        keyword.verifyPresentAndClick(Locator.NOTICE_BTN_LATE);
        loginPage.viewUserInform();
        profilePage.checkUserInform(stmt,"name", phone);
    }
}
