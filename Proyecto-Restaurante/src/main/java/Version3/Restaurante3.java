package Version3;

import model.Comanda;
import model.Menu;
import model.OrdenTerminada;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class Restaurante3 {

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

        Observable<Comanda> comandas = Observable.interval(60/20, TimeUnit.SECONDS).take(5)
                .flatMap(i -> Observable.fromCallable(Comanda::getOrdenRandom));


        PublishSubject<Comanda> barman = PublishSubject.create();
        PublishSubject<Comanda> repostero = PublishSubject.create();
        PublishSubject<Comanda> chef = PublishSubject.create();
        PublishSubject<Comanda> pinche1 = PublishSubject.create();
        PublishSubject<Comanda> pinche2 = PublishSubject.create();

        PublishSubject<Menu.Bebida> bebidas = PublishSubject.create();
        PublishSubject<Menu.Postre> postres = PublishSubject.create();
        PublishSubject<Menu.PlatoFuerte> platosFuertes = PublishSubject.create();
        PublishSubject<Menu.Entrada> entradas = PublishSubject.create();

        PublishSubject<Object> mesero = PublishSubject.create();


        comandas.subscribeOn(hiloBarman)
                .subscribe(barman);

        comandas.subscribeOn(hiloRepostero)
                .subscribe(repostero);

        comandas.subscribeOn(hiloChef)
                .subscribe(chef);

        chef.subscribe(pinche1);
        chef.subscribe(pinche2);

        pinche1.observeOn(hiloPinche1)
                .map(Comanda::getEntrada)
                .doOnNext(entrada -> logea("Preparando Entrada: "  + entrada))
                .map(Menu.Entrada::valueOf)
                .subscribe(mesero);

        pinche2.observeOn(hiloPinche2)
                .map(Comanda::getPlatoFuerte)
                .doOnNext(platoFuerte -> logea("Preparando Plato Fuerte: "  + platoFuerte))
                .map(Menu.PlatoFuerte::valueOf)
                .subscribe(mesero);

        repostero.observeOn(hiloRepostero)
                .map(Comanda::getPostre)
                .map(Menu.Postre::valueOf)
                .doOnNext(postre -> logea("Preparando Postre: " + postre))
                .subscribe(mesero);

        barman.observeOn(hiloBarman)
                .map(Comanda::getBebida)
                .map(Menu.Bebida::valueOf)
                .doOnNext(bebida -> logea("Preparando bebida: "  + bebida))
                .subscribe(mesero);

        mesero.subscribeOn(hiloPinche1)
                .filter(o -> o instanceof Menu.Entrada)
                .map(o -> (Menu.Entrada) o)
                .doOnNext(preparado -> logea("Emplatando: Entrada: " + preparado))
                .subscribe(entradas);

        mesero.subscribeOn(hiloPinche2)
                .filter(o -> o instanceof Menu.PlatoFuerte)
                .map(o -> (Menu.PlatoFuerte)o)
                .doOnNext(preparado -> logea("Emplatando: Plato Fuerte: " + preparado))
                .subscribe(platosFuertes);

        mesero.subscribeOn(hiloBarman)
                .filter(o -> o instanceof Menu.Bebida)
                .map(o -> (Menu.Bebida)o)
                .doOnNext(preparado -> logea("Sirviendo Bebida: " + preparado))
                .subscribe(bebidas);


        mesero.subscribeOn(hiloRepostero)
                .filter(o-> o instanceof Menu.Postre)
                .map(o-> (Menu.Postre) o)
                .doOnNext(preparado -> logea("Emplatando Postre: " + preparado))
                .subscribe(postres);


        Observable.zip(bebidas, entradas, platosFuertes, postres, OrdenTerminada::new).subscribe(System.out::println);
        Thread.sleep(150000);

    }
}
