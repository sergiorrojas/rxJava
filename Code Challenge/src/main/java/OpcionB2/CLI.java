package OpcionB2;


import rx.Observable;
import rx.observables.ConnectableObservable;

public class CLI {
    public static void ponResultado(String resultado){
        Observable.just(resultado).subscribe(cadena -> System.out.println("Imprimiendo resultado : " + cadena));
    }


    public static void main (String[] args){
        ConnectableObservable<String> cli = new ConsoleObservable().publish();

        cli.subscribe(comando ->
                Observable.just(Motor.generarImprime(comando))
                        .subscribe(
                                funcion -> {
                                    if(!Motor.getStack().isEmpty()) {
                                        Observable.just(funcion.apply((String)Motor.getStack().pop()))
                                            .subscribe(
                                                    CLI::ponResultado)

                                    ;}
                                }),
                error -> {
                    System.out.println("Error en subscriptor imprime");
                    Motor.getStack().removeAllElements();
                });

        cli.subscribe(comando ->
                Observable.just(Motor.generarSuma(comando))
                        .subscribe(
                                funcion -> {
                                    if(!Motor.getStack().isEmpty()) {
                                        Observable.just(funcion.apply((Integer) Motor.getStack().pop(), (Integer) Motor.getStack().pop()))
                                                .subscribe(
                                                        resultado -> ponResultado(resultado.toString()));
                                    }
                                }
                        ),
                error -> {
                    System.out.println("Error en subscriptor suma");
                    Motor.getStack().removeAllElements();
                });


        cli.subscribe(
                comando ->
                        Observable.just(Motor.generarResta(comando))
                                .subscribe(
                                        funcion -> {
                                            if(!Motor.getStack().isEmpty()) {
                                                Observable.just(funcion.apply((Integer) Motor.getStack().pop(), (Integer) Motor.getStack().pop()))
                                                    .subscribe(
                                                            resultado -> ponResultado(resultado.toString()));
                                            }
                                        }
                                        ),
                error -> {
                    System.out.println("Error en subscriptor resta");
                    Motor.getStack().removeAllElements();
                });

        cli.subscribe(
                comando ->
                        Observable.just(Motor.generarHora(comando))
                .subscribe(
                        funcion -> {
                            if(!Motor.getStack().isEmpty()){
                                Observable.just(funcion.apply((String)Motor.getStack().pop()))
                                        .subscribe(
                                                CLI::ponResultado);
                            }
                        }),
                error -> {
                    System.out.println("Error en subscriptor hora");
                    Motor.getStack().removeAllElements();
                });

        cli.connect();
    }


}
