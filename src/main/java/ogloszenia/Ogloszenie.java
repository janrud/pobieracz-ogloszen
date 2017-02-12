package ogloszenia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Ogloszenie {
	// Klasa "immutable" - raz utworzony obiekt nie będzie się już zmieniał
	private final String tytul;
	private final int rocznik;
	private final int cena;
	private final String url;
	
	public Ogloszenie(String tytul, int rocznik, int cena, String url) {
		this.tytul = tytul;
		this.rocznik = rocznik;
		this.cena = cena;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return tytul + ", rocznik=" + rocznik + ", cena=" + cena + "   pod adresem " + url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cena;
		result = prime * result + rocznik;
		result = prime * result + ((tytul == null) ? 0 : tytul.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}
