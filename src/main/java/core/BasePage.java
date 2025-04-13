package core;

import helpers.LogHelper;
import keyword.KeywordWeb;
import org.slf4j.Logger;

public class BasePage {
    private static Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;
    public BasePage() {
        keyword = new KeywordWeb();
    }
    public BasePage(KeywordWeb keyword) {
        this.keyword = keyword;
    }
}
