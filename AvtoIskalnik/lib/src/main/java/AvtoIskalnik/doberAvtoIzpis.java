import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class doberAvtoIzpis {
    public static void main(String[] args) throws IOException {
    	
        String url = "https://www.doberavto.si/iskanje?showFilters=true";
        Document document = Jsoup.connect(url).get();
        Elements avtoOglasi = document.select("div.card-title a");
        
        for (Element avtoOglas : avtoOglasi) {
            Element titleElement = avtoOglas.select("h4").first();
            String title = titleElement.text();
            
            Element urlElement = avtoOglas.select("a").first();
            String adUrl = urlElement.attr("href");
            Document avtoOglas = Jsoup.connect(adUrl).get();
            
            Element znamkaElement = avtoOglas.select("#__BVID__4144").first();
            String znamka = znamkaElement.text();
            
            Element modelElement = avtoOglas.select("#__BVID__4145").first();
            String model = modelElement.text();
            
            Element cenaMinElement = avtoOglas.select("#__BVID__4129").first();
            String cenaMin = cenaMinElement.text();
            
            Element cenaMaxElement = avtoOglas.select("#__BVID__4163").first();
            String cenaMax = cenaMaxElement.text();

            Element prvaRegistracijaMinElement = avtoOglas.select("#__BVID__4201").first();
            String prvaRegistracijaMin = prvaRegistracijaMinElement.text();
            
            Element prvaRegistracijaMaxElement = avtoOglas.select("#__BVID__4261").first();
            String prvaRegistracijaMax = prvaRegistracijaMaxElement.text();

            Element tipGorivaElement = avtoOglas.select("#__BVID__4166").first();
            String tipGoriva = tipGorivaElement.text();
            
            Element menjalnikElement = avtoOglas.select("#__BVID__4167").first();
            String menjalnik = menjalnikElement.text();
            
            Element prostorninaMotorjaMinElement = avtoOglas.select("#__BVID__4168").first();
            String prostorninaMotorjaMin = prostorninaMotorjaMinElement.text();
            
            Element prostorninaMotorjaMaxElement = avtoOglas.select("#__BVID__4169").first();
            String prostorninaMotorjaMax = prostorninaMotorjaMaxElement.text();
            
            Element mocMotorjaMinElement = avtoOglas.select("#__BVID__4171").first();
            String mocMotorjaMin = mocMotorjaMinElement.text();
            
            Element mocMotorjaMaxElement = avtoOglas.select("#__BVID__4172").first();
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
