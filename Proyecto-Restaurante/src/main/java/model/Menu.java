package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
interface Preparable{}

public class Menu {
    public enum Bebida {CERVEZA, LIMONADA, NARANJADA, AGUA}
    public enum Entrada implements Preparable{PAPAS, ALITAS, DEDOS, BONELESS}
    public enum PlatoFuerte implements Preparable {MOLE, CEVICHE, CARNE, POLLO, PESCADO}
    public enum Postre {FLAN, PAY, PASTEL, NIEVE}

    private static final List<Bebida> BEBIDAS =
            Collections.unmodifiableList(Arrays.asList(Bebida.values()));
    private static final List<Entrada> ENTRADAS =
            Collections.unmodifiableList(Arrays.asList(Entrada.values()));
    private static final List<PlatoFuerte> PLATO_FUERTES =
            Collections.unmodifiableList(Arrays.asList(PlatoFuerte.values()));
    private static final List<Postre> POSTRES =
            Collections.unmodifiableList(Arrays.asList(Postre.values()));

    private static final Random RANDOM = new Random();

    public Bebida getBebidaRandom(){
        return BEBIDAS.get(RANDOM.nextInt(BEBIDAS.size()));
    }

    public Entrada getEntradaRandom(){
        return ENTRADAS.get(RANDOM.nextInt(ENTRADAS.size()));
    }

    public PlatoFuerte getPlatoFuerteRandom(){
        return PLATO_FUERTES.get(RANDOM.nextInt(PLATO_FUERTES.size()));
    }

    public Postre getPostreRandom(){
        return POSTRES.get(RANDOM.nextInt(POSTRES.size()));
    }

}
