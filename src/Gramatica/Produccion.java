package Gramatica;

public class Produccion {
    
    //Sea una produccion (x, der) == x -> der == x::=der
    private String izq;
    private String der;

    public Produccion(String izq, String der) {
        this.izq = izq;
        this.der = der;
    }

    public String getIzq() {
        return izq;
    }

    public String getDer() {
        return der;
    }       
    
}
