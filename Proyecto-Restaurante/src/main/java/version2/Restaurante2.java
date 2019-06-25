package version2;

import model.Comanda;
import model.Menu;
import model.OrdenTerminada;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class Restaurante2 {

    public static void logea(String log){
        System.out.println("[ "+ Thread.currentThread().getName()+" ]  " + log);
    }

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> interval = Observable.interval(60 / 20, TimeUnit.SECONDS);
        PublishSubject<Menu.Entrada> entradas = PublishSubject.create();
        PublishSubject<Menu.Bebida> bebidas = PublishSubject.create();
        PublishSubject<Menu.PlatoFuerte> platosFuertes = PublishSubject.create();
        PublishSubject<Menu.Postre> postres = PublishSubject.create();

        PublishSubject<String> barman = PublishSubject.create();
        PublishSubject<String> repostero = PublishSubject.create();
        PublishSubject<String> chef = PublishSubject.create();
        PublishSubject<String> pinche1 = PublishSubject.create();
        PublishSubject<String> pinche2 = PublishSubject.create();

        Scheduler hilo_barman = Schedulers.newThread();
        Scheduler hilo_chef = Schedulers.newThread();
        Scheduler hilo_repostero = Schedulers.newThread();
        Scheduler hilo_pinche1 = Schedulers.newThread();
        Scheduler hilo_pinche2 = Schedulers.newThread();

        Observable.zip(bebidas, entradas, platosFuertes, postres, OrdenTerminada::new).subscribe(System.out::println);

        barman.observeOn(hilo_barman)
                .map(bebida -> {
                    Thread.currentThread().setName("hilo-barman");
                    logea("Preparando bebida " + bebida);
                    return Menu.Bebida.valueOf(bebida);
                }).subscribe(bebidaPreparada ->{
                    logea("Sirviendo bebida " + bebidaPreparada);
                    bebidas.onNext(bebidaPreparada);
                });

        repostero.observeOn(hilo_repostero)
                .map(postre -> {
                    Thread.currentThread().setName("hilo-repostero");
                    logea("Preparando postre " + postre);
                    return Menu.Postre.valueOf(postre);
                }).subscribe(postrePreparado ->{
                    logea("emplatando postre " + postrePreparado);
                    postres.onNext(postrePreparado);
                });

        chef.observeOn(hilo_chef)
                .buffer(2)
                .subscribe(comidas -> {
                    pinche1.onNext(comidas.get(0));
                    pinche2.onNext(comidas.get(1));
                });

        pinche1.observeOn(hilo_pinche1)
                .map(entrada -> {
                    Thread.currentThread().setName("hilo-pinche1");
                    logea("Preparando entrada " + entrada);
                    return Menu.Entrada.valueOf(entrada);
                }).subscribe(entradaPreparada ->{
                    logea("emplatando entrada " + entradaPreparada);
                    entradas.onNext(entradaPreparada);
        });

        pinche2.observeOn(hilo_pinche2)
                .map(plato -> {
                    Thread.currentThread().setName("hilo-pinche2");
                    logea("Preparando plato " + plato);
                    return Menu.PlatoFuerte.valueOf(plato);
                }).subscribe(platoPreparado ->{
                    logea("emplatando plato " + platoPreparado);
                    platosFuertes.onNext(platoPreparado);
                });

        interval.subscribe(l -> {
                    Comanda comanda = Comanda.getOrdenRandom();
                    barman.onNext(comanda.getBebida());
                    repostero.onNext(comanda.getPostre());
                    chef.onNext(comanda.getEntrada());
                    chef.onNext(comanda.getPlatoFuerte());
                });

        Thread.sleep(30000);

    }

}
