package OpcionB2;

import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

import java.util.Scanner;

public class ConsoleObservable extends ConnectableObservable<String>{
    ConsoleObservable() {
        super(s -> {
            Scanner teclado = new Scanner(System.in);
            while (!s.isUnsubscribed()) {
                System.out.print("rx CLI: ");
                String linea = teclado.nextLine();

                if (linea.matches("^\\b(adios|(imprime |suma |resta |hora)\\b.*)")) {
                    if (linea.equals("adios")) {
                        s.unsubscribe();
                    } else {
                        s.onNext(linea);
                    }
                }else{
                    System.out.println("Invalid command");
                }
            }
        });
    }

    @Override
    public void connect(Action1<? super Subscription> action1) {
        super.connect();
    }
}
