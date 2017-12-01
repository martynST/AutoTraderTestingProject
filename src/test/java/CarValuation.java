import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarValuation {
    @FindBy(id = "vrn")
    private WebElement regNo;
    @FindBy(id = "mileage")
    private WebElement miles;
    @FindBy(css = "body > main > div > section.page-hero > div.content-container > div > form > section > div.car-valuations__input-container.submit-vrm > button")
    private WebElement getValidation;
    @FindBy(css = "body > main > section > div > section > div > div > div.vehicle-details__details-item.left > ul > li:nth-child(1) > span.details-result")
    private WebElement confirmRegNo;

    public void enterRegNo(String regNo)
    {
        this.regNo.sendKeys(regNo);
    }
    public void enterMiles(String miles)
    {
        this.miles.sendKeys(miles);
    }
    public void clickGetValidation()
    {
        this.getValidation.click();
    }
    public WebElement getConfirmRegNo()
    {
        return confirmRegNo;
    }
}
