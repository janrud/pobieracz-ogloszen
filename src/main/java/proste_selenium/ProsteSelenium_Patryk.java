package proste_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProsteSelenium_Patryk {

	public static void main(String[] args) {
		System.out.println("Startujemy...");
		System.setProperty("webdriver.gecko.driver", "C:/Tools/geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", "/opt/tools/geckodriver");
		
		WebDriver driver = null;
		
		try {
			driver = new FirefoxDriver();
			
			driver.get("http://www.alx.pl");
			
			System.out.println("Bieżąca strona "
					+ driver.getCurrentUrl() + " "
					+ driver.getTitle());
			
		} finally {
			if(driver != null) {
				driver.quit();
			}
		}
		
		System.out.println("Koniec programu");
	}

}
