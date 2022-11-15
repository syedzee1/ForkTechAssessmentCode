package IMDBAndWIKI;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtils.BaseClass;
import objectRepository.ImdbHomePage;
import objectRepository.WikiHomePage;

/*
 * author Syed Zeeshan
 */
public class FetchAndValidateDataOfWIKIAndIMDBTest extends BaseClass{
	@Test
	public void fetchAndValidateDataTest() throws Throwable {
		//fetch data from properties file
		String imdbUrl = fLib.getPropertValue("ImdbURL");
		String wikiUrl = fLib.getPropertValue("WikiURL");

		//fetch movie names from excel
		String movieName = eLib.fetchDataFromExcel("Movies", 1, 0);
		
//		Scanner scan = new Scanner(System.in);
//		System.out.println("enter movie Name: ");
//		String movieName = scan.next();
		wLib.maximizeTheWindow(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(wikiUrl);

		//reference of WIKI home page
		WikiHomePage wikiHome = new WikiHomePage(driver);
		wikiHome.getWikisearchTextField().sendKeys(movieName);
		wikiHome.getWikiSearchButton().click();
		String wikiReleaseDate = wikiHome.getWikiReleaseDate().getText();
		System.out.println(wikiReleaseDate);
		String wikiCountryName = wikiHome.getWikiCountryName().getText();
		System.out.println(wikiCountryName);

		//navigate to next URL
		URL url = new URL(imdbUrl);
		wLib.navigateToNextURL(driver, url);

		//reference of IMDB home page
		ImdbHomePage imdbHome = new ImdbHomePage(driver);
		imdbHome.getImdbsearchTextField().sendKeys(movieName);
		List<WebElement> movieOptions = imdbHome.getImdbAutoSuggesstion();
		for(WebElement movieNames: movieOptions) {
			String actualMovieName = movieNames.getText();
			if(actualMovieName.contains(movieName)) {
				movieNames.click();
				break;
			}
		}
		wLib.scrollBarAction(driver);
		String imdbReleaseDate = imdbHome.getImdbReleaseDate().getText();
		System.out.println(imdbReleaseDate);
		String imdbCountryName = imdbHome.getImdbCountryName().getText();
		System.out.println(imdbCountryName);

		//validate the country name and release date of both IMDB and WIKI movie
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(wikiCountryName,imdbCountryName);
		softAssert.assertEquals(wikiReleaseDate,imdbReleaseDate);
		softAssert.assertAll();
	}

}
