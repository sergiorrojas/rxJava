package version1;

import model.Comanda;
import model.Menu;
import model.OrdenTerminada;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class Restaurante {

    private static void logea(String log){
        System.out.println("[ hilo - " + Thread.currentThread().getName() +" ]  " + log);
    }

    public static void main(String[] args) throws InterruptedException {
        Menu menu = new Menu();
        final Scheduler hiloBarman = Schedulers.newThread();
        final Scheduler hiloRepostero = Schedulers.newThread();
        final Scheduler hiloChef = Schedulers.newThread();
        final Scheduler hiloPinche1 = Schedulers.newThread();
        final Scheduler hiloPinche2 = Schedulers.newThread();

        Observable<Long> interval = Observable.interval(60/20, TimeUnit.SECONDS);

        PublishSubject<Menu.Bebida> bebidas = PublishSubject.create();
        PublishSubject<Menu.Postre> postres = PublishSubject.create();
        PublishSubject<Menu.PlatoFuerte> platosFuertes = PublishSubject.create();
        PublishSubject<Menu.Entrada> entradas = PublishSubject.create();

        PublishSubject<Comanda> preparar = PublishSubject.create();
        PublishSubject<Object> servir = PublishSubject.create();
        PublishSubject<Comanda> chef = PublishSubject.create();

        Observable<Comanda> comandas = interval
                .flatMap(i -> Observable.fromCallable(Comanda::getOrdenRandom));

        comandas.subscribe(preparar);
        comandas.subscribeOn(hiloChef).subscribe(chef);

        preparar.observeOn(hiloPinche1)
                .map(Comanda::getEntrada)
                .doOnNext(entrada -> logea("Preparando Entrada: "  + entrada))
                .map(Menu.Entrada::valueOf)
                .subscribe(servir);

        preparar.observeOn(hiloPinche2)
                .map(Comanda::getPlatoFuerte)
                 .doOnNext(platoFuerte -> logea("Preparando Plato Fuerte: "  + platoFuerte))
                 .map(Menu.PlatoFuerte::valueOf)
                .subscribe(servir);

        preparar.observeOn(hiloRepostero)
                .map(Comanda::getPostre)
                .map(Menu.Postre::valueOf)
                .doOnNext(postre -> logea("Preparando Postre: " + postre))
                .subscribe(servir);


        preparar.observeOn(hiloBarman)
                .map(Comanda::getBebida)
                .map(Menu.Bebida::valueOf)
                .doOnNext(bebida -> logea("Preparando bebida: "  + bebida))
                .subscribe(servir);

        servir.subscribeOn(hiloPinche1)
                .filter(o -> o instanceof Menu.Entrada)
                .map(o -> (Menu.Entrada) o)
                .doOnNext(preparado -> logea("Emplatando: Entrada: " + preparado))
                .subscribe(entradas);

        servir .subscribeOn(hiloPinche2)
                .filter(o -> o instanceof Menu.PlatoFuerte)
                .map(o -> (Menu.PlatoFuerte)o)
                .doOnNext(preparado -> logea("Emplatando: Plato Fuerte: " + preparado))
                .subscribe(platosFuertes);

        servir.subscribeOn(hiloBarman)
                .filter(o -> o instanceof Menu.Bebida)
                .map(o -> (Menu.Bebida)o)
                .doOnNext(preparado -> logea("Sirviendo Bebida: " + preparado))
                .subscribe(bebidas);


        servir.subscribeOn(hiloRepostero)
                .filter(o-> o instanceof Menu.Postre)
                .map(o-> (Menu.Postre) o)
                .doOnNext(preparado -> logea("Emplatando Postre: " + preparado))
                .subscribe(postres);


        Observable.zip(bebidas, entradas, platosFuertes, postres, OrdenTerminada::new).subscribe(System.out::println);
        Thread.sleep(10000);

    }
}
