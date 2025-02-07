package maquina_snacks_archivo.servicios;

import maquina_snacks_archivo.dominio.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivo implements IServicioSnacks {

    private final String NOMBRE_ARCHIVO = "snacks.txt";
    //Creamos la lista de snacks
    private List<Snack> snacks = new ArrayList<>();

    //constructos clase

    public ServicioSnacksArchivo(){
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try {
            existe = archivo.exists();
            if (existe) {
                this.snacks = obtenerSnacks();
            } else {//Si no existe creamos el archivo
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();//Guarda el archivo en disco
                System.out.println("Se ha creado el archivo.");
            }
        } catch (Exception e){
            System.out.println("Error al crear el archivo" + e.getMessage());
            e.printStackTrace();
        }
        if (!existe) {
            cargarSnacksInciales();
        }
    }


    @Override
    public void agregarSnack(Snack snack) {
        //1. Agregamos el nuevo snack, a la lista en memoria
        this.snacks.add(snack);
        //2. Guardamos el nuevo snack en el archivo
        this.agregarSnackArchivo(snack);
    }
    private void agregarSnackArchivo(Snack snack){
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();//Se escribe la informacion en el archivo
        } catch (Exception e){
            System.out.println("Error al agregar Sack: " + e.getMessage());
        }
    }

    private void cargarSnacksInciales(){
        this.agregarSnack(new Snack("Papas", 70));
        this.agregarSnack(new Snack("Refresco", 50));
        this.agregarSnack(new Snack("Sandwich", 120));

    }

    private List<Snack> obtenerSnacks(){
        var snacks = new ArrayList<Snack>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for (String linea: lineas){
                String[] lineaSnack = linea.split(",");
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre, precio);
                snacks.add(snack);// Agregamos el snack leido a la lsita
            }

        } catch (Exception e){
            System.out.println("Error al leer archivo de snacks: " + e.getMessage());
                                                                    e.printStackTrace();
        }
        return snacks;
    }

    @Override
    public void mostrarSnacks() {
        System.out.println("--- Snacks en el Inventario ---");
        //Mostramos la lista de snacks en el archivo
        var inventarioSnacks = "";
        for (var snack: this.snacks){
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println(inventarioSnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
