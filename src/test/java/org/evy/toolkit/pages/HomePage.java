package org.evy.toolkit.pages;


import org.evy.toolkit.pages.account.AccountSection;
import org.evy.toolkit.pages.product.ProductSection;
import org.evy.toolkit.pages.searchbar.SearchbarSection;
import org.evy.toolkit.utils.LoggerUtils;


public class HomePage extends BasePage {

    public HomePage(){
        LoggerUtils.info(getClass(),"Landed on HomePage");
    }

    public AccountSection navigateToAccountSection(){
        LoggerUtils.info(getClass(),"Navigate to AccountSection");
        return new AccountSection();
    }

    public ProductSection navigateToProductSection(){
        LoggerUtils.info(getClass(),"Navigate to ProductSection");
        return new ProductSection();
    }

    public SearchbarSection navigateToSearchbarSection(){
        LoggerUtils.info(getClass(),"Navigate to SearchbarSection");
        return new SearchbarSection();
    }
}
