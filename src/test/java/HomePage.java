import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    @FindBy(css = "#js-channel-nav > ul.other-vehicles__list > li:nth-child(1) > a")
    private WebElement carsTab;
    @FindBy(css = "#js-channel-nav > ul.other-vehicles__list > li:nth-child(2) > a")
    private WebElement prestigeCarsTab;
    @FindBy(css = "#js-channel-nav > ul.other-vehicles__list > li:nth-child(3) > a")
    private WebElement vansTab;
    @FindBy(css = "body > main > div > section.sell__nav.t-row.cars > a")
    private WebElement validation;
    @FindBy(id = "postcode")
    private WebElement postcode;
    @FindBy(id = "radius")
    private WebElement radius;
    @FindBy(css = "#searchVehicles > fieldset > fieldset > label:nth-child(2)")
    private WebElement usedChecked;
    @FindBy(css = "#searchVehicles > fieldset > fieldset > label:nth-child(3)")
    private WebElement nearlyNewChecked;
    @FindBy(css = "#searchVehicles > fieldset > fieldset > label:nth-child(4)")
    private WebElement newChecked;
    @FindBy(id = "searchVehiclesMake")
    private WebElement make;
    @FindBy(id = "searchVehiclesModel")
    private WebElement model;
    @FindBy(id = "searchVehiclesPriceFrom")
    private WebElement minPrice;
    @FindBy(id = "searchVehiclesPriceTo")
    private WebElement maxPrice;
    @FindBy(id = "js-search-button")
    private WebElement search;
    @FindBy(css = "#js-sell-module > form > input:nth-child(1)")
    private WebElement regNo;
    @FindBy(css = "#js-sell-module > form > input.c-form__input.no-spinner")
    private WebElement miles;
    @FindBy(css = "#js-sell-module > form > button")
    private WebElement createAd;


    public void enterPostcode(String postcode)
    {
        this.postcode.sendKeys(postcode);
    }
    public void enterRadius(String radius)
    {
        Select select = new Select(this.radius);
        select.selectByValue(radius);
    }
    public void clickUsedChecked()
    {
        usedChecked.click();
    }
    public void clickNearlyUsedChecked()
    {
        nearlyNewChecked.click();
    }
    public void clickNewChecked()
    {
        newChecked.click();
    }
    public void enterMake(String make)
    {
        Select select = new Select(this.make);
        select.selectByValue(make);
    }
    public void enterModel(String model)
    {
        Select select = new Select(this.model);
        select.selectByValue(model);
    }
    public void enterMinPrice(String minPrice)
    {
        Select select = new Select(this.minPrice);
        select.selectByValue(minPrice);
    }
    public void enterMaxPrice(String maxPrice)
    {
        Select select = new Select(this.maxPrice);
        select.selectByValue(maxPrice);
    }
    public void clickSearch()
    {
        search.click();
    }
    public void enterRegNo(String regNo)
    {
        this.regNo.sendKeys(regNo);
    }
    public void enterMiles(String miles)
    {
        this.miles.sendKeys(miles);
    }
    public void clickCreateAd()
    {
        this.createAd.click();
    }

    public void gotoCars()
    {
        carsTab.click();
    }
    public void gotoPrestigeCarsTab()
    {
        prestigeCarsTab.click();
    }
    public void gotoVansTab()
    {
        vansTab.click();
    }
    public void gotoValidation()
    {
        validation.click();
    }
}
