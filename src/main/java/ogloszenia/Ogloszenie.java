package ogloszenia;

public class Ogloszenie {
	// Klasa "immutable" - raz utworzony obiekt nie będzie się już zmieniał
	private final String tytul;
	private final int rocznik;
	private final int cena;
	
	public Ogloszenie(String tytul, int rocznik, int cena) {
		this.tytul = tytul;
		this.rocznik = rocznik;
		this.cena = cena;
	}

	public String getTytul() {
		return tytul;
	}

	public int getRocznik() {
		return rocznik;
	}

	public int getCena() {
		return cena;
	}

	@Override
	public String toString() {
		return tytul + ", rocznik=" + rocznik + ", cena=" + cena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cena;
		result = prime * result + rocznik;
		result = prime * result + ((tytul == null) ? 0 : tytul.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ogloszenie other = (Ogloszenie) obj;
		if (cena != other.cena)
			return false;
		if (rocznik != other.rocznik)
			return false;
		if (tytul == null) {
			if (other.tytul != null)
				return false;
		} else if (!tytul.equals(other.tytul))
			return false;
		return true;
	}

}
