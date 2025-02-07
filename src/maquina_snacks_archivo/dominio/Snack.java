package maquina_snacks_archivo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {
    private static int contador = 0;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack() {
        this.idSnack = ++Snack.contador;

    }

    public Snack(String nombre, double precio) {
        this();
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdSnack() {
        return this.idSnack;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "idSnack=" + idSnack +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
    public String escribirSnack(){
        return idSnack + "," + nombre + "," + precio;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
