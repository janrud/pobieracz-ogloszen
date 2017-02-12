package ogloszenia;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ZapiszXml {

	public static void main(String[] args) {
		try {
			Pobieracz pobieracz = null;
			try {
				pobieracz = new Pobieracz();
			
				pobieracz.wyszukaj("Mazda 6 III");
			} finally {
				pobieracz.close();
			}
			ZestawOgloszen zestaw = new ZestawOgloszen();
			zestaw.ogloszenia = pobieracz.getOgloszenia();
			
			JAXBContext ctx = JAXBContext.newInstance(ZestawOgloszen.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(zestaw, new File("auta.xml"));
			System.out.println("Gotowe");
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
