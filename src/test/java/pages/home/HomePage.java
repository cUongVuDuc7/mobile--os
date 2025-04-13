package pages.home;

import core.BasePage;
import io.qameta.allure.Step;
import locator.Locator;

public class HomePage extends BasePage {
    public HomePage() {
    }
    @Step("Hiển thị btn trang trủ")
    public void visibleHome(){
        keyword.verifyElementDisplay(Locator.HOME_BTN_HOME, true);
    }
    @Step("Hiển thị live score")
    public void visibleLiveScore(){
        keyword.verifyElementDisplay(Locator.HOME_BTN_LIVE_SCORE, true);
    }
    @Step("Hiển thị yêu thích")
    public void visibleLike(){
        keyword.verifyElementDisplay(Locator.HOME_BTN_FOLLOWING, true);
    }
    @Step("Hiển thị logo On Plus")
    public void visibleLogo(){
        keyword.verifyElementDisplay(Locator.HOME_IMAGES_LOGO, true);
    }
    @Step("Bỏ qua quảng cáo")
    public void skipBanner(){
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_SKIP_BANNER, 10);
        keyword.click(Locator.HOME_BTN_SKIP_BANNER);
    }
}
