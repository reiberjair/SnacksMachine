package maquina_snacks_archivo.servicios;

import maquina_snacks_archivo.dominio.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks {
    private  static final List<Snack> snacks;
        //Bloque de tipo static inicializador
    static {
        snacks = new ArrayList<>();

        snacks.add(new Snack("Papas", 70.22));
        snacks.add(new Snack("Refrescos", 95));
        snacks.add(new Snack("Chocolate", 100));
        }
    public void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public void mostrarSnacks() {
        var inventarioSnacks = "";
        for (var snack: snacks){
            inventarioSnacks += snack.toString()+ "\n";
        }
        System.out.println("--- Snacks en el Inventario ---");
        System.out.println(inventarioSnacks);
    }

    public List<Snack> getSnacks(){
        return snacks;
    }
}
