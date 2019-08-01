package model;

public class Platillo {
    private String bebida;
    private String postre;
    private Comida comida;

    public Platillo(Long id){
        this.bebida = "bebida" + id;
        this.postre = "postre" + id;
        this.comida = new Comida(id);
    }

    public String getBebida() {
        return bebida;
    }

    public String getPostre() {
        return postre;
    }

    public Comida getComida() {
        return comida;
    }
}
