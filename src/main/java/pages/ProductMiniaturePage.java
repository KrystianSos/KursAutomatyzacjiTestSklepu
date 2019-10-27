package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.math.BigDecimal;

public class ProductMiniaturePage extends BasePage {
    public ProductMiniaturePage(WebDriver driver, WebElement element) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    @FindBy(className = "price")
    private WebElement price;

    @FindBy(className = "regular-price")
    private WebElement priceBeforeDiscount;

    @FindBy(className = "h3")
    private WebElement name;

    @FindBy(className = "new")
    private WebElement newLabel;

    @FindBy(css = "div > a > img")
    private WebElement image;

    @FindBy(className = "discount-percentage")
    private WebElement discountLabel;

    public BigDecimal getPrice(){
        return new BigDecimal(this.price.getText().replace("$",""));
    }

    public BigDecimal getPriceBeforeDiscount(){
        return new BigDecimal(this.priceBeforeDiscount.getText().replace("$",""));
    }

    public String getName() {
        //nie rozwija się cała nazwa(pojawiają się ...), trzeba zrobić replace
        return name.getText().replace("...","");
    }

    public boolean isNew() {
        return isPresent(newLabel);
    }

    public boolean isDiscounted() {
       return isPresent(discountLabel);
    }

    //&& sprawdza oba warunki, a & jak 1 się wywali to nie sprawdza drugiego warunku
    public boolean hasImage(){
        return isPresent(image) & !image.getAttribute("src").contains("empty");
    }

    public void open() {
        click(name);
    }

    public BigDecimal getDiscountValue(){
        return new BigDecimal(discountLabel.getText()
                .replace("%","")
                .replace("-",""))
                .divide(new BigDecimal(100)); //Dzielimy żeby zamienić liczbę całkowitą na ułamek
    }

}
