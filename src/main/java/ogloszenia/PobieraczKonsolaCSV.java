package ogloszenia;

import java.util.List;

public class PobieraczKonsolaCSV {

	public static void main(String[] args) {
		
		String plik = "auta.csv";
		String szukam = "Mazda 6 III";
		
		Pobieracz pobieracz = null;
		try {
			pobieracz = new Pobieracz();
		
			pobieracz.wyszukaj(szukam);
		} finally {
			pobieracz.close();
		}
		List<Ogloszenie> ogloszenia = pobieracz.getOgloszenia();
		
		System.out.println("Znalazłem " + ogloszenia.size() + " ogoszeń");
		
		ObslugaCSV.zapiszCSV(ogloszenia, plik);
		System.out.println("Zapisałem");

	}

}
