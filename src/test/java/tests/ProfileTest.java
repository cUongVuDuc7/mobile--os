package tests;
import core.BaseTest;
import core.DataBase;
import locator.Locator;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProfilePage;
import pages.home.HomePage;
import pages.loginsignup.LoginPage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import static helpers.PathHelper.getNameMethod;
import static utilities.DateTime.getCurrentDateTime;
import static utilities.ReadExcel.*;

public class ProfileTest extends BaseTest {
    public HomePage homePage;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public DataBase dataBase ;
    private HashMap<String, String> dataProfile;
    private Statement stmt ;
    private Connection con ;
    private Sheet sh = null;
    public ProfileTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        homePage = new HomePage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        sh = readSheet(workbook , "Profile");
        con = dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        stmt = dataBase.createStatement(con);
        homePage.skipBanner();
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
//        loginPage.isUserLogout();
//        loginPage.goToLogin();
//        loginPage.login(dataProfile.get("User name") ,dataProfile.get("Pass word"));
    }
    @Test(priority = 1, description = "Kiểm tra cập nhật profile khi nhập các thông tin hợp lệ")
    public void PF_1(){
        loginPage.isUserLogout();
//        loginPage.viewUserInform();
//        profilePage.clickEdit();
//        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
//        profilePage.updateFullInform(dataProfile.get("Name"), dataProfile.get("Email"), dataProfile.get("Gender"));
//        profilePage.saveInform("Thành công");
//        loginPage.viewUserInform();
//        profilePage.checkUserInform(stmt,"PHONE_NUMBER","all");
    }
    @Test(priority = 2, dependsOnMethods = "PF_1", description = "Kiểm tra cập nhật profile nhưng không lưu")
    public void PF_2(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        String userInform = profilePage.getUserInform("all");
        profilePage.updateFullInform(dataProfile.get("Name"),dataProfile.get("Email"),dataProfile.get("Gender"));
        loginPage.goBack(Locator.USER_INFORM_BTN_BACK);
        keyword.assertEqualMultiData(userInform, profilePage.getUserInform("all"));
    }
    @Test(priority = 3, dependsOnMethods = "PF_2", description = "Kiểm tra cập nhật tên hiển thị thành công")
    public void PF_3(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        profilePage.editFullName(dataProfile.get("Name"));
        profilePage.saveInform("Thành công");
        loginPage.viewUserInform();
        profilePage. checkUserInform(stmt,"PHONE_NUMBER","name");
    }
    @Test(priority = 4, dependsOnMethods = "PF_3", description = "Kiểm tra cập nhật tên nhưng không lưu")
    public void PF_5(){
        profilePage.clickEdit();
        String name = profilePage.getUserInform("name");
        profilePage.editFullName(dataProfile.get("Name"));
        loginPage.goBack(Locator.USER_INFORM_BTN_BACK);
        keyword.assertEqualMultiData(name, profilePage.getUserInform("name"));
    }
    @Test(priority = 6, dependsOnMethods = "PF_5",description = "Kiểm tra cập nhật profile khi để trống tên")
    public void PF_4(){
        profilePage.clickEdit();
        profilePage.editFullName("");
        profilePage.saveInform("Tên thất bại");
    }
    @Test(priority = 7, dependsOnMethods = "PF_5",description = "Kiểm tra cập nhật email thành công")
    public void PF_6(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
//        loginPage.goBack(Locator.USER_INFORM_BTN_BACK);
//        loginPage.viewUserInform();
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Thành công");
        loginPage.viewUserInform();
        profilePage.checkUserInform(stmt,"PHONE_NUMBER","email");
    }
    @Test(priority = 8, dependsOnMethods = "PF_6",description = "Kiểm tra khi để trống mail")
    public void PF_8(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        loginPage.viewUserInform();
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 9,dependsOnMethods = "PF_8", description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_9(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 10, dependsOnMethods = "PF_9", description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_10(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 11, dependsOnMethods = "PF_10",description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_11(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 12, dependsOnMethods = "PF_6",description = "Kiểm tra  khi đổi email nhưng không lưu")
    public void PF_7(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        String name = profilePage.getUserInform("name");
        profilePage.editEmail(dataProfile.get("Email"));
        loginPage.goBack(Locator.USER_INFORM_BTN_BACK);
        keyword.assertEqualData(name, profilePage.getUserInform("name"));
    }

    @Test(priority = 13, dependsOnMethods = "PF_7",description = "Kiểm tra cập nhật ngày sinh thành công")
    public void PF_12(){
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"),"oke");
        profilePage.saveInform("Thành công");
        loginPage.viewUserInform();
        profilePage.checkUserInform(stmt,"PHONE_NUMBER","birth day");
    }
    @Test(priority = 14, dependsOnMethods = "PF_12",description = "Kiểm tra cập nhật ngày sinh nhưng không lưu, hủy cập nhật ngày sinh")
    public void PF_13_14(){
        profilePage.clickEdit();
        String birthDay = profilePage.getUserInform("birth day");
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"), "oke");
        loginPage.goBack(Locator.USER_INFORM_BTN_BACK);
        keyword.assertEqualData(birthDay, profilePage.getUserInform("birth day"));
//        profilePage.clickEdit();
//        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"),"cancel");
//        keyword.assertEqualData(birthDay, profilePage.getUserInform("birth day"));
    }
    @Test(priority = 15, dependsOnMethods = "PF_13_14",description = "Kiểm tra cập nhật giới tính thành công")
    public void PF_15(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        profilePage.editGender(dataProfile.get("Gender"));
        profilePage.saveInform("Thành công");
        loginPage.viewUserInform();
        profilePage.checkUserInform(stmt,"PHONE_NUMBER","gender");
    }
    @Test(priority = 16, dependsOnMethods = "PF_15",description = "Kiểm tra cập nhật giới tính nhưng không lưu")
    public void PF_16(){
        String gender = profilePage.getUserInform("gender");
        profilePage.clickEdit();
        profilePage.editGender("nữ");
        loginPage.goBack(Locator.USER_INFORM_BTN_BACK);
        keyword.assertEqualData(gender, profilePage.getUserInform("gender"));
    }
    @Test(priority = 17,dependsOnMethods = "PF_16", description = "Kiểm tra thay đổi avatar thành công")
    public void PF_17(){
        profilePage.clickEdit();
        profilePage.editAvatar();
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 18, description = "Vào xoá tài khoản, xong back")
    public void PF_18(){
//        loginPage.viewUserInform();
        profilePage.clickDeleteAccount();
        profilePage.showDeActiveAccountScreen();
//        loginPage.goBack(Locator.LOGIN_SMART_TV_BTN_BACK);
    }
    @Test(priority = 19,dependsOnMethods = "PF_18", description = "Back từ màn nhập mk khi đang xoá tk")
    public void PF_19(){
//        profilePage.clickDeleteAccount();
        profilePage.confirmDeleteAccount();
        profilePage.showInputPassWord();
//        loginPage.goBack(Locator.LOGIN_SMART_TV_BTN_BACK);
//        profilePage.showDeActiveAccountScreen();
    }
    @Test(priority = 20,dependsOnMethods = "PF_19", description = "Không nhập mk và nhập mk sai")
    public void PF_20_21(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
//        profilePage.confirmDeleteAccount();
//        profilePage.confirmDeleteAccount();
        profilePage.inputPassToDeActive(dataProfile.get("PassWord"));
        profilePage.confirmDeleteAccount();
        profilePage.displayIncorrectPassWord();
    }
    @Test(priority = 20,dependsOnMethods = "PF_20_21", description = "Xoá tk thành công")
    public void PF_22(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickDeleteAgain();
        profilePage.inputPassToDeActive(dataProfile.get("PassWord"));
        profilePage.confirmDeleteAccount();
        profilePage.deleteSuccessAccount();
        homePage.visibleHome();
        homePage.visibleLiveScore();
        homePage.visibleLike();
        homePage.visibleLogo();
    }
    @AfterClass
    public void closeConnect() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
}
