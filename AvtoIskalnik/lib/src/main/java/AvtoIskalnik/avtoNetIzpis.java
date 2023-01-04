import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class avtoNetIzpis {
    public static void main(String[] args) throws IOException {
        String url = "https://www.avto.net/rezultati.aspx";
        Document document = Jsoup.connect(url).get();
        Elements avtoOglasi = document.select("row bg-white position-relative GO-Results-Row GO-Shadow-B");
        for (Element avtoOglas : avtoOglasi) {
            Element znamkaElement = avtoOglas.select("div.make").first();
            String znamka = znamkaElement.text();
            
            Element modelElement = avtoOglas.select("div.model").first();
            String model = modelElement.text();
            
            Element cenaMinElement = avtoOglas.select("div.cenaMin").first();
            String cenaMin = cenaMinElement.text();
            
            Element cenaMaxElement = avtoOglas.select("div.cenaMax").first();
            String cenaMax = cenaMaxElement.text();
            
            Element prvaRegistracijaMinElement = avtoOglas.select("div.letnikMin").first();
            String prvaRegistracijaMin = prvaRegistracijaMinElement.text();
            
            Element prvaRegistracijaMaxElement = avtoOglas.select("div.letnikMax").first();
            String prvaRegistracijaMax = prvaRegistracijaMaxElement.text();
            
            Element tipGorivaElement = avtoOglas.select("div.bencin").first();
            String tipGoriva = tipGorivaElement.text();
            
            Element menjalnikElement = avtoOglas.select("div.automatic").first();
            String menjalnik = menjalnikElement.text();
            
            Element prostorninaMotorjaMinElement = avtoOglas.select("div.ccmMin").first();
            String prostorninaMotorjaMin = prostorninaMotorjaMinElement.text();
            
            Element prostorninaMotorjaMaxElement = avtoOglas.select("div.ccmMax").first();
            String prostorninaMotorjaMax = prostorninaMotorjaMaxElement.text();
            
            Element mocMotorjaMinElement = avtoOglas.select("div.kwMIN").first();
            String mocMotorjaMin = mocMotorjaMinElement.text();
            
            Element mocMotorjaMaxElement = avtoOglas.select("div.kwMAX").first();
            String mocMotorjaMax = mocMotorjaMaxElement.text();
            
            System.out.println("Znamka: " + znamka);
            System.out.println("Model: " + model);
            System.out.println("Cena od: " + cenaMin);
            System.out.println("Cena do: " + cenaMax);
            System.out.println("Prva registracija od: " + prvaRegistracijaMin);
            System.out.println("Prva registracija do: " + prvaRegistracijaMax);
            System.out.println("Tip goriva: " + tipGoriva);
            System.out.println("Menjalnik: " + menjalnik);
            System.out.println("Prostornina motorja od: " + prostorninaMotorjaMin);
            System.out.println("Prostornina motorja do: " + prostorninaMotorjaMax);
            System.out.println("Moc motorja od: " + mocMotorjaMin);
            System.out.println("Moc motorja do: " + mocMotorjaMax);
            System.out.println();
        }
    }
}

