package ogloszenia;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WypiszOgloszeniaNaKonsole {
	//private static final String SCIEZKA_GECKO = "C:/Tools/geckodriver.exe";
	private static final String SCIEZKA_GECKO = "/opt/selenium/geckodriver";
	
	private static final String baseUrl = "http://moto.gratka.pl/";

	private static final Pattern regexCeny = Pattern.compile("(\\d+\\s*)+");
	private static final Pattern regexRocznika = Pattern.compile("(\\d+)r\\.");

	public static void main(String[] args) {
		System.out.println("Startujemy...");
		System.setProperty("webdriver.gecko.driver", SCIEZKA_GECKO);
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String czegoSzukam = "Mazda 6";
		
		driver.get(baseUrl);
		System.out.println("Wszedłem na strone " + driver.getCurrentUrl());
		
	    driver.findElement(By.id("element-87")).clear();
	    driver.findElement(By.id("element-87")).sendKeys(czegoSzukam);
	    driver.findElement(By.cssSelector("button.btn.btnSearch")).click();

		System.out.println("Są wyniki...");
		System.out.println(driver.getCurrentUrl());

		List<WebElement> elementy = driver.findElements(By.className("opisOferty"));
		System.out.println("Znalezionio " + elementy.size() + " ogłoszeń");
		for (WebElement elementOgloszenia : elementy) {
			WebElement h3 = elementOgloszenia.findElement(By.tagName("h3"));
			String tytul = h3.getText().trim();
			
	    	WebElement pasek = elementOgloszenia.findElement(By.className("pasek"));
	    	WebElement elementCena = pasek.findElement(By.tagName("strong"));

	    	int cena = 0;
	    	Matcher matcherCeny = regexCeny.matcher(elementCena.getText());
	    	if(matcherCeny.find()) {
	    		cena = Integer.parseInt(matcherCeny.group().replace(" ", ""));	    	
	    	}
	    	
	    	WebElement elementRocznik = elementOgloszenia.findElement(By.cssSelector("li[title='Rok produkcji']"));
	    	
	    	int rocznik = 0;
	    	Matcher matcherRocznika = regexRocznika.matcher(elementRocznik.getText());
	    	if(matcherRocznika.find()) {
	    		rocznik = Integer.parseInt(matcherRocznika.group(1));
	    	}
	    	Ogloszenie ogl = new Ogloszenie(tytul, rocznik, cena, "");
	    	System.out.println(ogl);
		}
		
		driver.quit();
	}
}
