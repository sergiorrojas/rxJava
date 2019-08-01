import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;
import java.util.Stack;

public class CLI {

    public static void ponResultado(String resultado){
        Observable.just(resultado).subscribe(cadena -> System.out.println("Imprimiendo resultado : " + cadena));

    }

    public static void main (String[] args){
        ConnectableObservable<String> cli = new ConsoleObservable().publish();
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(System.out::println);

        cli.filter(msg -> msg.startsWith("suma "))
                .map(msg -> msg.replace("suma ", ""))
                .map(comando -> comando.split(" "))
                .map(operandos -> Motor.generarSuma().apply(new Integer(operandos[0]), new Integer(operandos[1])))
                .doOnError(error -> System.out.println("Error en subscriptor suma"))
                .retry()
                .subscribe(result -> subject.onNext(result.toString()));
        //.subscribe(result -> ponResultado(result.toString()));

        cli.filter(msg -> msg.startsWith("resta "))
                .map(msg -> msg.replace("resta ", ""))
                .map(comando -> comando.split(" "))
                .map(operandos -> Motor.generarResta().apply(new Integer(operandos[1]), new Integer(operandos[0])))
                .doOnError(error -> System.out.println("Error en subscriptor resta"))
                .retry()
                .subscribe(result -> subject.onNext(result.toString()));
        //.subscribe(result -> ponResultado(result.toString()));

        cli.filter(msg -> msg.startsWith("imprime "))
                .map(msg -> msg.replace("imprime ", ""))
                .map(comando -> comando.split(" "))
                .map(operandos -> Motor.generarImprime().apply(operandos[0]))
                .doOnError(error -> System.out.println("Error en subscriptor imprime"))
                .retry()
                .subscribe(subject::onNext);
        // .subscribe(result -> ponResultado(result.toString()));

        cli.filter(msg -> msg.startsWith("hora"))
                .map(comando -> comando.contains("12")? "h:mm:ss a" : "HH:mm:ss")
                .doOnNext(System.out::println)
                .map(operandos -> Motor.generarHora().apply(operandos))
                .doOnError(error -> System.out.println("Error en subscriptor hora"))
                .retry()
                .subscribe(subject::onNext);
        //.subscribe(result -> ponResultado(result.toString()));
        cli.connect();


    }
}

