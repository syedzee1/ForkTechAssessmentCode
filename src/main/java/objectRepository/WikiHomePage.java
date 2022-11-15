package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * author Syed Zeeshan
 */
public class WikiHomePage {
	/**
	 * it is used to get element of Wiki page
	 * @param driver
	 */
	public WikiHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="search")
	private WebElement wikisearchTextField;
	
	@FindBy(id="searchButton")
	private WebElement wikiSearchButton;
	
	@FindBy(xpath="//table[@class='infobox vevent']/tbody/tr[contains(.,'Release date')]/td")
	private WebElement wikiReleaseDate;
	
	@FindBy(xpath="//table[@class='infobox vevent']/tbody/tr[contains(.,'Country')]/td")
	private WebElement wikiCountryName;

	public WebElement getWikisearchTextField() {
		return wikisearchTextField;
	}

	public WebElement getWikiSearchButton() {
		return wikiSearchButton;
	}

	public WebElement getWikiReleaseDate() {
		return wikiReleaseDate;
	}

	public WebElement getWikiCountryName() {
		return wikiCountryName;
	}
	
}
