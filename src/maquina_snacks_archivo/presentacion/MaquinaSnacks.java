package maquina_snacks_archivo.presentacion;

import maquina_snacks_archivo.dominio.Snack;
import maquina_snacks_archivo.servicios.IServicioSnacks;
import maquina_snacks_archivo.servicios.ServicioSnacksArchivo;
import maquina_snacks_archivo.servicios.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }
    public static void maquinaSnacks(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos el objeto para obtener el servicio de snack
       // IServicioSnacks servicioSnacks = new ServicioSnacksLista();
        IServicioSnacks servicioSnacks = new ServicioSnacksArchivo();
        //Creamos la lista de productos de tipo SNack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");
        servicioSnacks.mostrarSnacks(); // Mostrar inventario de snakcs disponibles
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);// Comienza la ejecucion del metodo mostrarMenu antes de asiganrle valor a la variable opcion, mientras se muestra el menu.
                salir = ejecutarOpciones(opcion, consola, productos, servicioSnacks);

            } catch (Exception e){
                System.out.println("Error: " + e);
            } finally {
                System.out.println();// Imprime un salto de linea con cada iteracion
            }
        }

    }
    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                Menu: 
                1. Comprar Snack
                2. Mostar ticket
                3. Agregar Nuevo Snack
                4. Mostrar inventario de snacks
                5. Salir
                Elige una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola,
                                            List<Snack> productos, IServicioSnacks servicioSnacks){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnacks);
            case 4 -> listarInventarioSnacks(consola, servicioSnacks);
            case 5 -> {
                System.out.println("Regresa pronto...");
                salir = true;
            }
            default ->
                System.out.println("Opcion invalida." + opcion);
        }
        return salir;
    }

    private static void listarInventarioSnacks(Scanner consola, IServicioSnacks servicioSnacks){
        servicioSnacks.mostrarSnacks();
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos,
                                     IServicioSnacks servicioSnacks){
        System.out.println("Que snack quieres comprar? (ID): " );
        var idSnack = Integer.parseInt(consola.nextLine());
        //Validar si el snack existe
        var snackEncontrado = false;
        for (var snack: servicioSnacks.getSnacks()){
            if (idSnack == snack.getIdSnack()){
                //Agregamos el snack a la lista de productos
                productos.add(snack);
                System.out.println("Ok, Snack agregado: " + snack);
                snackEncontrado = true;
                break;

            }
        }
        if (!snackEncontrado){
            System.out.println("Id de SNack no encontrado: " + idSnack);
        }

    }

    private static void mostrarTicket(List<Snack> productos){
        var tickect = "*** Ticket de Venta ***";
        var total = 0.0;
        for (var producto: productos){
            tickect += "\n\t-" + producto.getNombre() + "-$" + producto.getPrecio();
            total += producto.getPrecio();
        }
        tickect+= "\n\tTotal -> $" + total;
        System.out.println(tickect);
    }

    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnacks){
        System.out.print("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        servicioSnacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Tu snack se ha agregado correctaente.");
        servicioSnacks.mostrarSnacks();
    }
}

