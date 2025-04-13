package locator;

import helpers.PropertiesFile;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class Locator {

    public static final By DEVICE_IOS_KEYBOARD_DONE = By.name("Done");
    public static final By LOGIN_LBL_BANNER = new AppiumBy.ByAccessibilityId("Đăng nhập/Đăng ký ONplus và trải nghiệm tiện ích");
    public static final By HOME_BTN_MENU = new AppiumBy.ByAccessibilityId("Menu");
    public static final By HOME_BTN_HOME = new AppiumBy.ByAccessibilityId("Trang chủ");
    public static final By HOME_BTN_SKIP_BANNER = new By.ByXPath("//XCUIElementTypeButton[@name=\"Bỏ qua\"]");
    public static final By HOME_BTN_LIVE_SCORE = new AppiumBy.ByAccessibilityId("Livescore");
    public static final By HOME_BTN_FOLLOWING = new AppiumBy.ByAccessibilityId("Yêu thích");
    public static final By PROFILE_BTN_VIEW_USER_INFORM = new AppiumBy.ByAccessibilityId("Xem thông tin tài khoản");
    public static final By LOGIN_SMART_TV_BTN_BACK = By.xpath("//XCUIElementTypeButton[@name=\"ic back\"]");
    public static final By USER_INFORM_BTN_BACK = new AppiumBy.ByAccessibilityId("ic_back");
    public static final By LOGIN_BTN = By.name("Đăng nhập");
    public static final By LOGIN_TXT_USER_NAME = By.className("XCUIElementTypeTextField");
    public static final By LOGIN_TXT_PASSWORD = By.className("XCUIElementTypeSecureTextField");
    public static final By LOGIN_BTN_HIDDEN_PASS = new AppiumBy.ByAccessibilityId("hidePass");
    public static final By LOGIN_BTN_CONTINUE = By.name("Tiếp tục");
    public static final By LOGIN_TOAST_SUCCESS = new AppiumBy.ByAccessibilityId("Đăng nhập thành công");
    public static final By LOGIN_TOAST_INCORRECT_PASSWORD = By.xpath("//XCUIElementTypeApplication[@name=\"ON Plus Dev\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText");
    public static final By SIGN_UP_TOAST_INCORRECT_OTP = new AppiumBy.ByAccessibilityId("Mã otp sai hoặc đã hết hạn");
    public static final By NOTICE_LBL_MORE_3_DEVICE = By.id("");
    public static final By LOGOUT_BTN = new AppiumBy.ByAccessibilityId("Đăng xuất");
    public static final By LOGOUT_BTN_CONFIRM = By.name("Đăng xuất");
    public static final By LOGOUT_BTN_CANCEL = By.name("Huỷ");
    public static final By LOGOUT_TOAST_SUCCESS = new AppiumBy.ByAccessibilityId("Đăng xuất thành công");
    public static final By MENU_BTN_SMART_TV = new AppiumBy.ByAccessibilityId("Đăng nhập Smart TV, Website");
    public static final By MENU_BTN_QUAN_LY = new AppiumBy.ByAccessibilityId("Quản lý thiết bị");
    public static final By MENU_BTN_THONG_BAO = new AppiumBy.ByAccessibilityId("Thông báo");
    public static final By LOGIN_SMART_TV_LBL = new AppiumBy.ByAccessibilityId("Đăng nhập Smart TV, Website");
    public static final By LOGIN_LBL_DEVICE_MANAGE = new AppiumBy.ByAccessibilityId("Quản lý thiết bị");
    public static final By LOGIN_SMART_TV_BTN_QR = new AppiumBy.ByAccessibilityId("QUÉT QR CODE");
    public static final By LOGIN_SMART_TV_LBL_OTP = new AppiumBy.ByAccessibilityId("Nhập mã code gồm 6 ký tự");
    public static final By DEVICE_MANAGE_LBL_3_DEVICE = new AppiumBy.ByAccessibilityId("Bạn được sử dụng tối đa 3 thiết bị");
    public static final By LOGIN_SMART_TV_LBL_INPUT_CODE = new AppiumBy.ByAccessibilityId("Nhập Mã code hoặc quét QR code trên màn Smart TV, Website để liên kết tài khoản");
    public static final By DEVICE_MANAGE_BTN_LOGOUT_DEVICE = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton");
    public static final By DEVICE_MANAGE_BTN_LAYOUT_DEVICE = By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]");
    public static final By SET_INFORM_LBL_TITLE = new AppiumBy.ByAccessibilityId("Cài đặt thông báo");
    public static final By SET_INFORM_BTN_COMPLETE = By.xpath("//XCUIElementTypeButton[@name=\"Hoàn tất\"]");
    public static final By USER_INFORM_BTN_EDIT = By.name("Sửa");
    public static final By USER_INFORM_BTN_SAVE = By.name("Lưu");
    public static final By USER_INFORM_BTN_EDIT_FULL_NAME = By.xpath("(//XCUIElementTypeStaticText[@name=\"Tên hiển thị\"]//following::XCUIElementTypeImage[@name=\"edit\"])[1]");
    public static final By USER_INFORM_TXT_FULL_NAME = By.xpath("(//XCUIElementTypeStaticText[@name=\"Tên hiển thị\"]//following::XCUIElementTypeTextField)[1]");
    public static final By USER_INFORM_LBL_FULL_NAME = By.xpath("(//XCUIElementTypeStaticText[@name=\"Tên hiển thị\"]//following::XCUIElementTypeStaticText)[1]");

    public static final By USER_INFORM_BTN_EDIT_EMAIL = By.xpath("(//XCUIElementTypeStaticText[@name=\"Tên hiển thị\"]//following::XCUIElementTypeImage[@name=\"edit\"])[2]");
    public static final By USER_INFORM_TOAST_UPDATE_SUCCESS = new AppiumBy.ByAccessibilityId("Lưu thành công");
    public static final By USER_INFORM_TXT_EMAIL = By.xpath("(//XCUIElementTypeStaticText[@name=\"Email\"]//following::XCUIElementTypeTextField)[1]");
    public static final By USER_INFORM_LBL_EMAIL = By.xpath("(//XCUIElementTypeStaticText[@name=\"Email\"]//following::XCUIElementTypeStaticText)[1]");

    public static final By USER_INFORM_BTN_EDIT_BIRTH_DAY = new AppiumBy.ByAccessibilityId("calendar solid");
    public static final By USER_INFORM_BTN_GENDER = new AppiumBy.ByAccessibilityId("chevron-down");
    public static final By USER_INFORM_LBL_BIRTH_DAY = By.xpath("(//XCUIElementTypeStaticText[@name=\"Ngày sinh\"]//following::XCUIElementTypeStaticText)[1]");
    public static final By USER_INFORM_LBL_GENDER = By.xpath("(//XCUIElementTypeStaticText[@name=\"Giới tính\"]//following::XCUIElementTypeStaticText)[1]");
    public static final By USER_INFORM_BTN_EDIT_AVT = By.xpath("(//XCUIElementTypeImage[@name=\"edit\"])[1]");
    public static final By USER_INFORM_BTN_DELETE_ACCOUNT = By.name("Xóa tài khoản");
    public static final By DE_ACTIVE_ACCOUNT_LBL = new AppiumBy.ByAccessibilityId("VÔ HIỆU HÓA TÀI KHOẢN");
    public static final By DE_ACTIVE_ACCOUNT_LBL_CONTENT = By.xpath("(//XCUIElementTypeStaticText[@name=\"VÔ HIỆU HÓA TÀI KHOẢN\"]//following::XCUIElementTypeStaticText)[1]");
    public static final By DE_ACTIVE_ACCOUNT_BTN_CONFIRM = By.xpath("//XCUIElementTypeButton[@name=\"Xác nhận\"]");
    public static final By DE_ACTIVE_ACCOUNT_LBL_INPUT_PASS = new AppiumBy.ByAccessibilityId("NHẬP MẬT KHẨU");
    public static final By DE_ACTIVE_ACCOUNT_LBL_NOTICE = By.xpath("//XCUIElementTypeStaticText[@name=\"Vì lý do bảo mật vui lòng nhập mật khẩu để tiếp tục.\"]");
    public static final By DE_ACTIVE_ACCOUNT_TXT_PASS = By.className("XCUIElementTypeSecureTextField");
    public static final By DE_ACTIVE_ACCOUNT_NOTICE_POPUP = new AppiumBy.ByAccessibilityId("Bạn đã nhập sai mật khẩu. Hãy thử lại.");
    public static final By DE_ACTIVE_ACCOUNT_BTN_AGAIN = By.xpath("//XCUIElementTypeButton[@name=\"Thử lại\"]");
    public static final By DE_ACTIVE_ACCOUNT_NOTICE_SUCCESS= new AppiumBy.ByAccessibilityId("Thực hiện xoá tài khoản thành công");
    public static final By DE_ACTIVE_ACCOUNT_BTN_DONE = By.name("Xong");
    public static final By HOME_BTN_SEARCH = new AppiumBy.ByAccessibilityId("search");
    public static final By HOME_IMAGES_LOGO = new AppiumBy.ByAccessibilityId("mainLogo");
    public static final By USER_INFORM_LBL_PHONE = By.xpath("(//XCUIElementTypeStaticText[@name=\"Số điện thoại\"]//following::XCUIElementTypeStaticText)[1]");
    public static final By USER_INFORM_BTN_MALE_GENDER = By.name("Nam");
    public static final By USER_INFORM_BTN_OTHER_GENDER = By.name("Khác");
    public static final By USER_INFORM_BTN_FEMALE_GENDER = By.name("Nữ");
    public static final By USER_INFORM_BTN_CHOOSE_AVATAR = By.name("Chọn ảnh từ thư viện");
    public static final By USER_INFORM_BTN_CANCEL_AVATAR  = By.name("Hủy");
    public static final By DEVICE_BTN_SELECT_IMAGES  = By.name("Chọn");
    public static final By USER_INFORM_BTN_AVT = By.xpath("(//XCUIElementTypeImage)[1]");
    public static final By SET_PASSWORD_LBL_PHONE = By.xpath("//XCUIElementTypeStaticText[@name=\"Xin chào,\"]");
    public static final By SET_PASSWORD_LBL_PHONE2 = By.xpath("//XCUIElementTypeStaticText[@name=\"Vui lòng tạo mật khẩu từ 6 ký tự trở lên\"]");
    public static final By INPUT_OTP_BTN_BACK = By.xpath("//XCUIElementTypeButton[@name=\"ic back\"]");
    public static final By LOGIN_LBL_ERROR = By.xpath("//XCUIElementTypeStaticText[@name=\"Mật khẩu phải từ 6 ký tự trở lên\"]");
    public static final By SIGN_UP_LBL_ERROR = By.xpath("//XCUIElementTypeStaticText[@name=\"Mật khẩu chưa trùng khớp\"]");

    public static final By LOGIN_TXT_CONFIRM_PASSWORD = By.xpath("(//XCUIElementTypeButton[@name=\"Hoàn tất\"]//preceding::XCUIElementTypeSecureTextField)[1]");
    public static final By SIGN_UP_TXT_CONFIRM_PASSWORD = By.xpath("(//XCUIElementTypeButton[@name=\"Hoàn tất\"]//preceding::XCUIElementTypeSecureTextField)[2]");
    public static final By SIGN_UP_TXT_SHOW_PASSWORD = By.xpath("(//XCUIElementTypeButton[@name=\"Hoàn tất\"]//preceding::XCUIElementTypeTextField)[1]");
    public static final By SIGN_UP_TXT_SHOW_PASSWORD_CONFIRM = By.xpath("(//XCUIElementTypeButton[@name=\"Hoàn tất\"]//preceding::XCUIElementTypeTextField)[2]");

    public static final By SIGN_UP_BTN_SHOW_PASS = By.xpath("(//XCUIElementTypeButton[@name=\"hidePass\"])[1]");
    public static final By SIGN_UP_BTN_SHOW_AGAIN_PASS = By.xpath("(//XCUIElementTypeButton[@name=\"hidePass\"])[2]");

    public static final By SIGN_UP_BTN_CLEAR_PASS = By.xpath("//XCUIElementTypeButton[@name=\"XCircle\"]");

    public static final By SIGN_UP_BTN_CLEAR_AGAIN_PASS = By.xpath("(//XCUIElementTypeButton[@name=\"XCircle\"])[2]");

    public static final By NOTICE_BTN_LATE = By.xpath("(//XCUIElementTypeButton[@name=\"Để sau\"])");

    public static final By LOGIN_BTN_SHOW_PASS = By.xpath("(//XCUIElementTypeButton[@name=\"showPass\"])[1]");
    public static final By LOGIN_BTN_ACCEPT = By.id("com.vtvcab.onsports.dev:id/btnOk");
    public static final By SIGN_UP_TXT_EDIT_OPT = By.xpath("//XCUIElementTypeImage[@name=\"bg-otp\"]//following::XCUIElementTypeTextField");
    public static final By LBL_TIME_LIVE_EXPIRED_OTP = By.xpath("(//XCUIElementTypeStaticText[@name=\"Mã OTP sẽ hết hạn trong\"]//following::XCUIElementTypeStaticText)[1]");
    public static final By LBL_TIME_EXPIRED_OTP = By.xpath("//XCUIElementTypeStaticText[@name=\"Mã OTP của bạn đã hết hạn \"]");
    public static final By SIGN_UP_BTN_RESEND_OTP = By.xpath("//XCUIElementTypeStaticText[@name=\"Gửi lại OTP\"]");
    public static final By LOGOUT_DEVICE_TOAST_SUCCESS = By.id("com.vtvcab.onsports.dev:id/tv_message");

    public static final By USER_INFORM_TOAST_UPDATE_AVATAR = By.xpath("//android.widget.Toast[@text=\"Cập nhật ảnh thành công\"]");
    public static final By USER_INFORM_TOAST_UPDATE_FAIL_EMAIL = By.xpath("//android.widget.Toast[1]");
    public static final By USER_INFORM_BTN_LIST_AVT = By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[3]");
    public static final By USER_INFORM_BTN_EDIT_DATE = new AppiumBy.ByAccessibilityId(PropertiesFile.getPropValue("ID_DATE"));
    public static final By USER_INFORM_BTN_OKE_EDIT_DATE = By.xpath("//android.widget.Button[@text=\"OK\"]");
    public static final By USER_INFORM_BTN_CANCEL_EDIT_DATE = By.xpath("//android.widget.Button[@text=\"CANCEL\"]");
    public static final By MENU_LBL_LOGIN_NOTICE = By.id("com.vtvcab.onsports.dev:id/body");
    public static final By MENU_BTN_SUB_HISTORY = By.id("com.vtvcab.onsports.dev:id/sub_history");
    public static final By MENU_BTN_INPUT_COUPON = By.id("com.vtvcab.onsports.dev:id/coupon_layout");
    public static final By HISTORY_SUB_TITLE = By.id("com.vtvcab.onsports.dev:id/tv_title");
    public static final By HISTORY_SUB_BTN_ALL = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Tất cả\"]");
    public static final By HISTORY_SUB_BTN_SUB = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Đăng ký\"]");
    public static final By HISTORY_SUB_BTN_RENEW = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Gia hạn\"]");
    public static final By HISTORY_SUB_BTN_CANCEL = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Huỷ gói\"]");
    public static final By LIST_SUB_BTN_CHOOSE = By.xpath("com.vtvcab.onsports.dev:id/button_buy");
    public static final By LIST_SUB_BTN_SELECT_SUB = By.xpath("(//android.widget.ImageView[@resource-id=\"com.vtvcab.onsports.dev:id/test1\"])[2]");
    public static final By DEVICE_MANAGE_LBL_NAME_DEVICE = By.xpath("(//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/deviceName\"])[2]");
    public static final By POLICY_TERM_LBL_TITLE  = By.xpath("//android.view.View[@text=\"CHÍNH SÁCH BẢO MẬT & ĐIỀU KHOẢN SỬ DỤNG\"]");
    public static final By MENU_BTN_POLICY_TERM  = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/txt_info\" and @text=\"Điều khoản & chính sách\"]");
    public static final By DISCOUNT_CODE_BTN_APPLY  = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/button_next\"]");
    public static final By DISCOUNT_CODE_TXT_INPUT  = By.xpath("//android.widget.EditText[@resource-id=\"com.vtvcab.onsports.dev:id/ed_coupon\"]");
    public static final By DISCOUNT_CODE_LBL_INPUT_CODE  = By.xpath("//android.widget.TextView[@resource-id='com.vtvcab.onsports.dev:id/textView']");
    public static final By DISCOUNT_CODE_LBL_TITLE  = By.xpath("//android.widget.TextView[@resource-id='com.vtvcab.onsports.dev:id/tv_title']");
    public static final By MENU_BTN_BUY_SUB  = By.id("com.vtvcab.onsports.dev:id/buy_sub");
    public static final By POLICY_TERM_LBL_TITLE_MAIN  = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/tv_title\"]");
    public static final String POLICY_TERM_LBL_TITLE_1  = "//android.widget.TextView[@text=\"1. KHAI BÁO KHI SỬ DỤNG\"]";
    public static final By POLICY_TERM_BASE_VIEW = By.id("com.vtvcab.onsports.dev:id/base_view");
    public static final By NOT_ALLOW_BTN = By.id("com.android.permissioncontroller:id/permission_deny_button");
    public static final By FORGOT_PASS_BTN = By.id("com.vtvcab.onsports.dev:id/text_forgot_password");
    public static final By CONFIRM_OTP_TITLE = By.id("com.vtvcab.onsports.dev:id/title_phone");
    public static final By SEARCH_TXT_INPUT = By.id("com.vtvcab.onsports.dev:id/search_src_text");
    public static final By SEARCH_LBL_NO_RESULT = By.xpath("//android.widget.TextView[@text=\"Không có kết quả tìm kiếm\"]");
    public static final By SEARCH_LBL_FIND_OTHER_RESULT = By.xpath("//android.widget.TextView[@text=\"Hãy thử tìm kiếm với từ khóa khác.\"]");
    public static final By SEARCH_LBL_TITLE_RESULT = By.id("com.vtvcab.onsports.dev:id/title_text");
    public static final By SEARCH_BTN_TAG = By.id("com.vtvcab.onsports.dev:id/textview");
    public static final By SEARCH_BTN_BACK = By.id("com.vtvcab.onsports.dev:id/imageButtonBack");
    public static final By SEARCH_BTN_TAG_ALL = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Tất cả\"]");
    public static final By VIEW_VIDEO_BTN_CLOSE = By.id("com.vtvcab.onsports.dev:id/close_imageView");
    public static final By VIEW_VIDEO_BTN_PLAY = By.id("com.vtvcab.onsports.dev:id/play_imageView");
    public static final By MENU_LBL_SIGNING = By.id("com.vtvcab.onsports.dev:id/info");
    public static final By PLAYER_VIEW_BTN_PLAY = By.id("com.vtvcab.onsports.dev:id/onsport_play");
    public static final By PLAYER_VIEW_BTN_PAUSE = By.id("com.vtvcab.onsports.dev:id/onsport_pause");
    public static final By PLAYER_VIEW_BTN_REWIND = By.id("com.vtvcab.onsports.dev:id/ivRewind");
    public static final By PLAYER_VIEW_BTN_FORWARD = By.id("com.vtvcab.onsports.dev:id/ivForward");
    public static final By PLAYER_VIEW_LBL_TIME_LINE = By.id("com.vtvcab.onsports.dev:id/onsport_position");
    public static final By PLAYER_VIEW_LBL_TIME_DURATION = By.id("com.vtvcab.onsports.dev:id/onsport_duration");
    public static final By PLAYER_VIEW_BTN_FULL_SCREEN = By.id("com.vtvcab.onsports.dev:id/btnFullScreen");
    public static final By PLAYER_VIEW_BTN_FRAME_SCREEN = By.id("com.vtvcab.onsports.dev:id/exo_content_frame");
    public static final By CONTENT_LBL_LIVE = By.id("com.vtvcab.onsports.dev:id/include_live_text");
    public static final String CONTENT_IMG_LIVE  = "//androidx.appcompat.widget.LinearLayoutCompat[@resource-id=\"com.vtvcab.onsports.dev:id/include_live_text\"]/ancestor::android.widget.FrameLayout[@resource-id=\"com.vtvcab.onsports.dev:id/searchCardView\"]";











}

