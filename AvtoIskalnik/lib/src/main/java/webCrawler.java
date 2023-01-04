import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebCrawlerAvtoNet {

    private static final Logger log = LoggerFactory.getLogger(WebCrawlerAvtoNet.class);

    private final AdRepository adRepository;

    public WebCrawlerAvtoNet(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Scheduled(cron = "0 0 * * *")  // to naredi da ob polnoci enkrat dnevno gre
    public void crawl() throws IOException {
        log.info("Iskanje se zacne...");
        List<Ad> allAds = Stream.concat(crawlAvtoNet().stream(), crawlDoberAvtoSi().stream())
                .collect(Collectors.toList());
        adRepository.saveAll(ads);
        markInactiveAds(allAds);
        log.info("Iskanje koncano.");
    }

    private List<Ad> crawlAvtoNet() throws IOException {
        log.info("Crawling avto.net...");
        List<Ad> ads = new ArrayList<>();
        String url = "https://https://www.avto.net/rezultati.aspx";
        Document document = Jsoup.connect(url).get();
        Elements avtoOglasi = document.select("row bg-white position-relative GO-Results-Row GO-Shadow-B");
        for (Element avtoOglas : avtoOglasi) {
            Ad ad = new Ad();
            ad.setSource("avto.net");
            Element znamka = avtoOglas.select("div.make").first();
            ad.setTitle(znamka.text());
            
            Element model = avtoOglas.select("div.model").first();
            ad.setDescription(model.text());
            
            Element cenaMin = avtoOglas.select("div.cenaMin").first();
            ad.setCenaMin(cenaMin.text());
            
            Element cenaMax = avtoOglas.select("div.cenaMax").first();
            ad.setCenaMax(cenaMax.text());
            
            Element prvaRegistracijaMin = avtoOglas.select("div.letnikMin").first();
            ad.setPrvaRegistracijaMin(prvaRegistracijaMin.text());
            
            Element prvaRegistracijaMax = avtoOglas.select("div.letnikMax").first();
            ad.setPrvaRegistracijaMax(prvaRegistracijaMax.text());
            
            Element tipGoriva = avtoOglas.select("div.bencin").first();
            ad.setTipGoriva(bencin.text());
            
            Element menjalnik = avtoOglas.select("div.automatic").first();
            ad.setTipGoriva(automatic.text());
            
            Element prostorninaMotorjaMin = avtoOglas.select("div.ccmMin").first();
            ad.setTipGoriva(ccMin.text());
            
            Element prostorninaMotorjaMax = avtoOglas.select("div.ccmMax").first();
            ad.setTipGoriva(ccMax.text());
            
            Element mocMotorjaMin = avtoOglas.select("div.kwMIN").first();
            ad.setTipGoriva(kwMIN.text());
            
            Element mocMotorjaMax = avtoOglas.select("div.kwMAX").first();
            ad.setMocMotorjaMax(kwMAX.text());
            
            
            ad.setLastVisited(LocalDateTime.now());
            ad.setActive(true);
            ads.add(ad);
        }
        return ads;
    }
    
    private List<Ad> crawlDoberAvtoSi() throws IOException {
        log.info("Crawling doberavto.si...");
        
        List<Ad> ads = new ArrayList<>();
        String url = "https://www.doberavto.si/iskanje?showFilters=true";
        Document document = Jsoup.connect(url).get();
        Elements avtoOglasi = document.select("div.car-card");
        for (Element avtoOglas : avtoOglasi) {
            Ad ad = new Ad();
            ad.setSource("doberavto.si");
            
            Element znamka = avtoOglas.select("#__BVID__4144").first();
            ad.setTitle(znamka.text());
            
            Element model = avtoOglas.select("#__BVID__4145").first();
            ad.setDescription(model.text());
            
            Element cenaMin = avtoOglas.select("#__BVID__4129").first();
            ad.setCenaMin(cenaMin.text());
            
            Element cenaMax = avtoOglas.select("#__BVID__4163").first();
            ad.setCenaMax(cenaMax.text());
            
            Element prvaRegistracijaMin = avtoOglas.select("#__BVID__4201").first();
            ad.setPrvaRegistracijaMin(prvaRegistracijaMin.text());
            
            Element prvaRegistracijaMax = avtoOglas.select("#__BVID__4261").first();
            ad.setPrvaRegistracijaMax(prvaRegistracijaMax.text());
            
            Element tipGoriva = avtoOglas.select("#__BVID__4166").first();
            ad.setTipGoriva(bencin.text());
            
            Element menjalnik = avtoOglas.select("#__BVID__4167").first();
            ad.setTipGoriva(automatic.text());
            
            Element prostorninaMotorjaMin = avtoOglas.select("#__BVID__4168").first();
            ad.setTipGoriva(ccMin.text());
            
            Element prostorninaMotorjaMax = avtoOglas.select("#__BVID__4169").first();
            ad.setTipGoriva(ccMax.text());
            
            Element mocMotorjaMin = avtoOglas.select("#__BVID__4171").first();
            ad.setTipGoriva(kwMIN.text());
            
            Element mocMotorjaMax = avtoOglas.select("#__BVID__4172").first();
            ad.setMocMotorjaMax(kwMAX.text());
            
           
            ad.setLastVisited(LocalDateTime.now());
            ad.setActive(true);
            ads.add(ad);
        }
        
        return ads;
    }

    
    private void markInactiveAds(List<Ad> crawledAds) {
        log.info("Marking inactive ads...");
        List<Ad> activeAds = adRepository.findByActiveTrue();
        for (Ad ad : activeAds) {
            if (!crawledAds.contains(ad)) {
                ad.setActive(false);
                adRepository.save(ad);
            }
        }
        log.info("Oznacene {} reklame niso vec dejavne", activeAds.size() - crawledAds.size());
    }

}

