import model.Comida;
import model.Platillo;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class DonCarbon {
    private static void logea(String log){
        System.out.println("[hilo-" + Thread.currentThread().getName() + "] " + log);

    }
    public static void main(String[] args) throws InterruptedException {
        PublishSubject<String> barman = PublishSubject.create();
        PublishSubject<Comida> chef = PublishSubject.create();
        PublishSubject<String> repostero = PublishSubject.create();
        PublishSubject<String> pinche1 = PublishSubject.create();
        PublishSubject<String> pinche2 = PublishSubject.create();


        Observable<Platillo> comandas =
                Observable.interval(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .map(Platillo::new);

        comandas.map(Platillo::getComida)
                .subscribe(chef);
        comandas.map(Platillo::getBebida)
                .subscribe(barman);
        comandas.map(Platillo::getPostre)
                .subscribe(repostero);

        chef.map(Comida::getEntrada)
                .subscribe(pinche1);

        chef.map(Comida::getPlatoFuerte)
                .subscribe(pinche2);

        barman.map(bebida -> Observable.just("preparar: " + bebida, "servir: " + bebida))
                .subscribe(innerObs -> innerObs.subscribe(DonCarbon::logea));

        repostero.map(postre -> Observable.just("preparar: " + postre, "emplatar: " + postre))
                .subscribe(innerObs -> innerObs.subscribe(DonCarbon::logea));

        pinche1.map(entrada -> Observable.just("Preparar: " + entrada, "emplatar: " + entrada))
                .subscribe(innerObs -> {
                    innerObs.subscribe(DonCarbon::logea);
                });

        pinche2.map(platoFuerte -> Observable.just("Preparar: " + platoFuerte, "emplatar: " + platoFuerte))
                .subscribe(innerObs -> innerObs.subscribe(DonCarbon::logea));

        Thread.sleep(60000);

    }
}
