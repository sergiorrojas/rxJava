import rx.Observable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Motor {

    public static Function<String, String> generarHora() {
        //String format = "HH:mm:ss";
        //if (comando.contains("12"))
        //    format = "h:mm:ss a";
        return formato -> LocalTime.now().format(DateTimeFormatter.ofPattern(formato));
    }

    public static Function<String, String> generarImprime() {
        return x -> x;
    }

    public static BiFunction<Integer, Integer, Integer> generarSuma() {
        return Integer::sum;
    }

    public static BiFunction<Integer, Integer, Integer> generarResta() {
        return (x, y) -> y - x;
    }
}