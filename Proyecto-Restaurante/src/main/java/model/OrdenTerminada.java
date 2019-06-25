package model;

import model.Menu;

public class OrdenTerminada {
    private Menu.Bebida bebida;
    private Menu.Entrada entrada;
    private Menu.PlatoFuerte platoFuerte;
    private Menu.Postre postre;



    public OrdenTerminada(){

    }

    public OrdenTerminada(Menu.Bebida bebida, Menu.Entrada entrada, Menu.PlatoFuerte platoFuerte, Menu.Postre postre) {
        this.bebida = bebida;
        this.entrada = entrada;
        this.platoFuerte = platoFuerte;
        this.postre = postre;
    }

    public Menu.Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Menu.Bebida bebida) {
        this.bebida = bebida;
    }

    public Menu.Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Menu.Entrada entrada) {
        this.entrada = entrada;
    }

    public Menu.PlatoFuerte getPlatoFuerte() {
        return platoFuerte;
    }

    public void setPlatoFuerte(Menu.PlatoFuerte platoFuerte) {
        this.platoFuerte = platoFuerte;
    }

    public Menu.Postre getPostre() {
        return postre;
    }

    public void setPostre(Menu.Postre postre) {
        this.postre = postre;
    }

    @Override
    public String toString() {
        return "OrdenTerminada{" +
                "bebida=" + bebida +
                ", entrada=" + entrada +
                ", platoFuerte=" + platoFuerte +
                ", postre=" + postre +
                '}';
    }
}
