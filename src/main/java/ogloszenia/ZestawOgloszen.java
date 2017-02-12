package ogloszenia;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZestawOgloszen {
	@XmlElement(name="ogloszenie")
	public List<Ogloszenie> ogloszenia;
}
