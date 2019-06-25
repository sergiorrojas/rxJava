package OpcionB2;

import rx.Observable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Motor {
    public static Stack getStack() {
        return stack;
    }

    public static void setStack(Stack stack) {
        Motor.stack = stack;
    }

    private static Stack stack = new Stack();

    public static Function<String, String> generarHora(String comando) {
        if (comando.startsWith("hora ")) {
            String format = "HH:mm:ss";
            if (comando.contains("12"))
                format = "h:mm:ss a";
            stack.add(format);
            return formato -> LocalTime.now().format(DateTimeFormatter.ofPattern(formato));
        } else {
            return null;
        }
    }

    public static Function<String, String> generarImprime(String comando) {
        if (comando.startsWith("imprime ")) {
            String cadena = comando.replace("imprime ", "");
            stack.add(cadena);
            return x -> x;
        } else {
            return null;
        }
    }

    public static BiFunction<Integer, Integer, Integer> generarSuma(String comando) {
        if (comando.startsWith("suma ")) {
            String[] operandos = comando.replace("suma ", "").split(" ");
            stack.add(new Integer(operandos[0]));
            stack.add(new Integer(operandos[1]));
            return (x, y) -> x + y;
        } else {
            return null;
        }
    }

    public static BiFunction<Integer, Integer, Integer> generarResta(String comando) {
        if (comando.startsWith("resta ")) {
            String[] operandos = comando.replace("resta ", "").split(" ");
            stack.add(new Integer(operandos[0]));
            stack.add(new Integer(operandos[1]));
            return (x, y) -> y - x;
        } else {
            return null;
        }
    }
}