package objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * author Syed Zeeshan
 */
public class ImdbHomePage {
	/**
	 * it is used to get element of imdb page
	 * @param driver
	 */
	public ImdbHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="q")
	private WebElement imdbsearchTextField;

	@FindBy(xpath="//ul[@role='listbox']/descendant::div[@class='sc-d2740ffb-2 STkQo searchResult__constTitle']")
	private List<WebElement> imdbAutoSuggesstion;

	@FindBy(xpath="//a[text()='Release date']/parent::li/descendant::div")
	private WebElement imdbReleaseDate;

	@FindBy(xpath="//button[text()='Country of origin']/parent::li/descendant::div")
	private WebElement imdbCountryName;

	public WebElement getImdbsearchTextField() {
		return imdbsearchTextField;
	}

	public List<WebElement> getImdbAutoSuggesstion() {
		return imdbAutoSuggesstion;
	}

	public WebElement getImdbReleaseDate() {
		return imdbReleaseDate;
	}

	public WebElement getImdbCountryName() {
		return imdbCountryName;
	}

}
