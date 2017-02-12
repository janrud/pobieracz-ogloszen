package ogloszenia;

import java.util.List;

public class PobieraczKonsola2 {

	public static void main(String[] args) {
		Pobieracz pobieracz = null;
		try {
			pobieracz = new Pobieracz();
		
			pobieracz.wyszukaj("Mazda 6 III");
		} finally {
			pobieracz.close();
		}
		List<Ogloszenie> ogloszenia = pobieracz.getOgloszenia();
		
		for (Ogloszenie ogloszenie : ogloszenia) {
			System.out.println(ogloszenie);
		}

	}

}
