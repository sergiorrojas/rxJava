import java.util.ArrayList;
import java.util.List;

import news.NewsItem;
import news.NewsType;
import rx.Observable;
import rx.observables.ConnectableObservable;

public class UnoNoticias {

    public static void main (String ... args){
        //Creating news
        List<NewsItem> newsItems = new ArrayList<NewsItem>();
        newsItems.add(new NewsItem(0001,
                "Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa",
                NewsType.POLITICA));
        newsItems.add(new NewsItem(0002,
                "José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho",
                NewsType.DEPORTIVA));
        newsItems.add(new NewsItem(0003,
                "OFICIAL: Oribe Peralta cubrirá a Cristiano en la Juve tras ser condenado a meses de prisión",
                NewsType.DEPORTIVA));
        newsItems.add(new NewsItem(0004,
                "Revelan que contingencia ambiental es a causa del humo que vendió morena",
                NewsType.POLITICA));
        newsItems.add(new NewsItem(0005,
                "Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación",
                NewsType.ESPECTACULOS));
        newsItems.add(new NewsItem(0006,
                "Confirman que hay más gente afiliada al Blockbuster que al PRI",
                NewsType.POLITICA));

        ConnectableObservable<NewsItem> newsItemConnectableObservable = Observable.from(newsItems).publish();

        Observable<NewsItem> sportsNews =
                newsItemConnectableObservable
                        .filter(item -> NewsType.DEPORTIVA.equals(item.getNewsType()))

                                            ;
        Observable<NewsItem> politicsNews =
                newsItemConnectableObservable
                        .takeUntil(item -> NewsType.ESPECTACULOS.equals(item.getNewsType()))
                        .filter(item -> NewsType.POLITICA.equals(item.getNewsType()));


        Observable<NewsItem> entertainmentNews =
                newsItemConnectableObservable
                        .filter(item -> NewsType.ESPECTACULOS.equals(item.getNewsType()));

        Observable<NewsItem> entertainmentSportsNews =
                Observable.merge(entertainmentNews, sportsNews);

        Observable<NewsItem> allNews = newsItemConnectableObservable;


        sportsNews.subscribe(newsItem -> System.out.println("From sports: " + formatNews(newsItem)));
        politicsNews.subscribe(newsItem -> System.out.println("From politics: " + formatNews(newsItem)));
        entertainmentNews.subscribe(newsItem -> System.out.println("From entertainment: " + formatNews(newsItem)));
        entertainmentSportsNews.subscribe(newsItem -> System.out.println("From entertainment-sports: " + formatNews(newsItem)));
        allNews.subscribe(newsItem -> System.out.println("From all news: " + formatNews(newsItem)));

        newsItemConnectableObservable.connect();

    }

    private static String formatNews (NewsItem item){
        return item.getNewsType() + " - " + item.getContent();
    }

}
