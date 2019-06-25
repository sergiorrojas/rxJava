package model;

public class Comanda {
    private String bebida;
    private String entrada;
    private String platoFuerte;
    private String postre;


    public static Comanda getOrdenRandom(){
        Menu menu = new Menu();
        Comanda comanda = new Comanda();
        comanda.setBebida(menu.getBebidaRandom().toString());
        comanda.setEntrada(menu.getEntradaRandom().toString());
        comanda.setPlatoFuerte(menu.getPlatoFuerteRandom().toString());
        comanda.setPostre(menu.getPostreRandom().toString());
        return comanda;
    }

    public Comanda(){

    }

    public Comanda(String bebida, String entrada, String platoFuerte, String postre) {
        this.bebida = bebida;
        this.entrada = entrada;
        this.platoFuerte = platoFuerte;
        this.postre = postre;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getPlatoFuerte() {
        return platoFuerte;
    }

    public void setPlatoFuerte(String platoFuerte) {
        this.platoFuerte = platoFuerte;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
    }
}
