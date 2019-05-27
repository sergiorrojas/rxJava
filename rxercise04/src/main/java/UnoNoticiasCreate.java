import news.NewsItem;
import news.NewsType;
import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import subscribers.NewsSubscriber;

import java.util.Arrays;

import static news.NewsType.*;

public class UnoNoticiasCreate {
    public static void main(String[] args) {
        Observable<NewsItem> newsItemObservable = Observable.create(new Observable.OnSubscribe<NewsItem>() {
            @Override
            public void call(Subscriber<? super NewsItem> subscriber) {
                System.out.println("Creando observable");

                subscriber.onNext(new NewsItem(0001,
                        "Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa",
                        POLITICA));
                subscriber.onNext(new NewsItem(0002,
                        "José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho",
                        NewsType.DEPORTIVA));
                subscriber.onNext(new NewsItem(0003,
                        "OFICIAL: Oribe Peralta cubrirá a Cristiano en la Juve tras ser condenado a meses de prisión",
                        NewsType.DEPORTIVA));
                subscriber.onNext(new NewsItem(0004,
                        "Revelan que contingencia ambiental es a causa del humo que vendió morena",
                        NewsType.POLITICA));
                subscriber.onNext(new NewsItem(0005,
                        "Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación",
                        NewsType.ESPECTACULOS));
                subscriber.onNext(new NewsItem(0006,
                        "Confirman que hay más gente afiliada al Blockbuster que al PRI",
                        NewsType.POLITICA));
                subscriber.onCompleted();
            }
        });

        //Observable<NewsItem> newsItemConnectableObservable = newsItemObservable;
        ConnectableObservable<NewsItem> newsItemConnectableObservable = newsItemObservable.publish();

        Subscriber<NewsItem> reforma = new NewsSubscriber(POLITICA);
        Subscriber<NewsItem> ventaneando = new NewsSubscriber(ESPECTACULOS);
        Subscriber<NewsItem> espn = new NewsSubscriber(DEPORTIVA);
        Subscriber<NewsItem> entertainmentSports = new NewsSubscriber(Arrays.asList(ESPECTACULOS, DEPORTIVA));
        Subscriber<NewsItem> allNews = new NewsSubscriber(Arrays.asList(ESPECTACULOS, DEPORTIVA, POLITICA));
        newsItemConnectableObservable.subscribe(reforma);
        newsItemConnectableObservable.subscribe(ventaneando);
        newsItemConnectableObservable.subscribe(espn);
        newsItemConnectableObservable.subscribe(entertainmentSports);
        newsItemConnectableObservable.subscribe(allNews);

        newsItemConnectableObservable.connect();
    }

}
