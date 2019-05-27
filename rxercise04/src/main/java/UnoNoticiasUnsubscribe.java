import com.sun.javafx.UnmodifiableArrayList;
import news.NewsItem;
import news.NewsType;
import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import subscribers.NewsSubscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static news.NewsType.*;

public class UnoNoticiasUnsubscribe {
    public static void main (String ... args){

        List<NewsItem> newsItems = new ArrayList<NewsItem>();
        newsItems.add(new NewsItem(0001,
                "Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa",
                POLITICA));
        newsItems.add(new NewsItem(0002,
                "José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho",
                NewsType.DEPORTIVA));
        newsItems.add(new NewsItem(0003,
                "OFICIAL: Oribe Peralta cubrirá a Cristiano en la Juve tras ser condenado a meses de prisión",
                NewsType.DEPORTIVA));
        newsItems.add(new NewsItem(0004,
                "Revelan que contingencia ambiental es a causa del humo que vendió morena",
                POLITICA));
        newsItems.add(new NewsItem(0005,
                "Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación",
                ESPECTACULOS));
        newsItems.add(new NewsItem(0006,
                "Confirman que hay más gente afiliada al Blockbuster que al PRI",
                POLITICA));

        ConnectableObservable<NewsItem> news =
                Observable.from(newsItems)
                .publish()
                ;



        Subscriber<NewsItem> reforma = new NewsSubscriber(POLITICA);
        Subscriber<NewsItem> ventaneando = new NewsSubscriber(ESPECTACULOS);
        Subscriber<NewsItem> espn = new NewsSubscriber(DEPORTIVA);
        Subscriber<NewsItem> entertainmentSports = new NewsSubscriber(Arrays.asList(ESPECTACULOS, DEPORTIVA));
        Subscriber<NewsItem> allNews = new NewsSubscriber(Arrays.asList(ESPECTACULOS, DEPORTIVA, POLITICA));
        news.subscribe(reforma);
        news.subscribe(ventaneando);
        news.subscribe(espn);
        news.subscribe(entertainmentSports);
        news.subscribe(allNews);

        news.connect();

    }

}
