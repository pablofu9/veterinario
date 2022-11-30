package modelo;

public class Perros {

    private int id;
    private String nombre;
    private String raza;
    private int peso;

    public Perros() {
    }

    public Perros(String nombre, String raza, int peso) {
        this.nombre = nombre;
        this.raza = raza;
        this.peso = peso;
    }

    public Perros(int id, String nombre, String raza, int peso) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return nombre + " - "+ raza+ " - "+peso+"Kg";
    }
}
