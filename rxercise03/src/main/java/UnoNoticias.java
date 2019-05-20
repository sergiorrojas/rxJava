import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.deploy.util.StringUtils;
import rx.Observable;

public class UnoNoticias {

    public static void main (String ... args){

        List<String> noticias = new ArrayList<String>();
        noticias.add("0001,Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa,POLITICA");
        noticias.add("0002,José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho,DEPORTIVA");
        noticias.add("0003,OFICIAL: Oribe Peralta cubrirá a Cristiano en la Juve tras ser condenado a meses de prisión,DEPORTIVA");
        noticias.add("0004,Revelan que contingencia ambiental es a causa del humo que vendió morena,POLITICA");
        noticias.add("0005,Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación,ESPECTACULOS");
        noticias.add("006,Confirman que hay más gente afiliada al Blockbuster que al PRI,POLITICA");


        Observable<String[]> news = Observable.from(noticias)
                                            .map(n -> n.split(","))
                                            .filter(n -> n.length == 3)
                                            ;
        Observable<String> sportsNews =
                news.filter(n -> "DEPORTIVA".equals(n[2]))
                        .map(n -> StringUtils.join(Arrays.asList(n), ","));

        Observable<String> politicsNews =
                news.takeUntil(n -> "ESPECTACULOS".equals(n[2]))
                        .filter(n -> "POLITICA".equals(n[2]))
                        .map(n -> StringUtils.join(Arrays.asList(n), ","));

        Observable<String> entertainmentNews =
                news.filter(n -> "ESPECTACULOS".equals(n[2]))
                        .map(n -> StringUtils.join(Arrays.asList(n), ","));

        Observable<String> entertainmentSportsNews = Observable.merge(entertainmentNews, sportsNews);
        Observable<String> sportsEntertainmentPoliticsNews = Observable.merge(entertainmentSportsNews, politicsNews);

        sportsNews.subscribe(x -> System.out.println("From sports: " + x));
        politicsNews.subscribe(x -> System.out.println("From politics: " + x));
        entertainmentNews.subscribe(x -> System.out.println("From entertainment: " + x));
        entertainmentSportsNews.subscribe(x -> System.out.println("from entertainment-sports: " + x));
        sportsEntertainmentPoliticsNews.subscribe(x -> System.out.println("from all news: " + x));

    }

}
