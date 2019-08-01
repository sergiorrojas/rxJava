package model;

public class Comida {
    private String entrada;
    private String platoFuerte;

    public Comida(Long id){
        this.entrada = "entrada" + id;
        this.platoFuerte = "platoFuerte" + id;

    }
    public String getEntrada() {
        return entrada;
    }

    public String getPlatoFuerte() {
        return platoFuerte;
    }
}
