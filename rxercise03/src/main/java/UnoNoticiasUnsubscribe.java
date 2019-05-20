import rx.Observable;
import rx.Subscriber;
import java.util.ArrayList;
import java.util.List;

public class UnoNoticiasUnsubscribe {
    public enum NewsType {DEPORTIVA, ESPECTACULOS, POLITICA}

    public static void main (String ... args){

        List<String> noticias = new ArrayList<String>();
        noticias.add("0001,Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa,POLITICA");
        noticias.add("0002,José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho,DEPORTIVA");
        noticias.add("0003,OFICIAL: Oribe Peralta cubrirá a Cristiano en la Juve tras ser condenado a meses de prisión,DEPORTIVA");
        noticias.add("0004,Revelan que contingencia ambiental es a causa del humo que vendió morena,POLITICA");
        noticias.add("0005,Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación,ESPECTACULOS");
        noticias.add("0006,Confirman que hay más gente afiliada al Blockbuster que al PRI,POLITICA");



        Subscriber<String> subsPolitica = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("se acabo");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error:" + throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                String[] news = s.split(",");
                if(news.length == 3) {
                    if ("ESPECTACULOS".equals(news[2])) {
                        System.out.println("ESPECTACULOS en POLITICA terminando subscripcion");
                        this.unsubscribe();
                    } else if ("POLITICA".equals(news[2])) {
                        System.out.println("POLITICA: " + s);
                    }
                }
            }
        };



        Observable<String> news = Observable.from(noticias);

        news.subscribe(subsPolitica);

        news.subscribe(
                x -> {
                    String[] msg = x.split(",");
                    if(msg.length == 3) {
                        if ("DEPORTIVA".equals(msg[2]))
                            System.out.println("Deportiva: " + x);
                    }
                },
                (t) -> {System.out.println("Error:" + t.getMessage());},
                () -> {System.out.println("DEPORTIVA se acabo");}

        );

        news.subscribe(
                x -> {
                    String[] msg = x.split(",");
                    if(msg.length == 3) {
                        if ("ESPECTACULOS".equals(msg[2]))
                            System.out.println("ESPECTACULOS: " + x);
                    }
                },
                (t) -> {System.out.println("Error:" + t.getMessage());},
                () -> {System.out.println("ESPECTACULOS se acabo");}

        );

        news.filter(n -> n.split(",")[2].matches("ESPECTACULOS|DEPORTIVA"))
                .subscribe(
                        x-> System.out.println("Espectaculos-Deportivo: " + x),
                        (t)-> System.out.println("Error:" + t.getMessage()),
                        () -> System.out.println("Espectaculos-Deportivo se acabo"));

        news.filter(n -> n.split(",")[2].matches("DEPORTIVA|ESPECTACULOS|POLITICA"))
                .subscribe(x-> System.out.println("Deportivo-Espectaculos-Politica: " + x),
                        (t)-> System.out.println("Error:" + t.getMessage()),
                        () -> System.out.println("Deportivo-Espectaculos-Politica se acabo"));



    }

}
