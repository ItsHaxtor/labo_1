import java.util.Scanner;

/**
 * Aplicacion de consola para operar una {@link ListaCredenciales}.
 * El cliente cumple las precondiciones del TAD ANTES de llamar a cada
 * operacion, de modo que ninguna excepcion llega a producirse.
 */
public class AppConsola {

    /**
     * Lee un entero validando la entrada: repite la pregunta mientras el
     * usuario escriba algo que no sea un entero.
     *
     * @param sc      lector de consola
     * @param mensaje texto que se muestra al pedir el dato
     * @return el entero ingresado
     */
    static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            if (!sc.hasNext()) {          // se cerro la entrada (Ctrl+D / Ctrl+Z)
                System.out.println("\nEntrada cerrada. Fin del programa.");
                System.exit(0);
            }
            System.out.println("  Entrada invalida: escriba un numero entero.");
            sc.next();                    // descarta el texto no numerico
            System.out.print(mensaje);
        }
        return sc.nextInt();
    }

    /** Muestra las opciones del menu. */
    static void mostrarMenu() {
        System.out.println();
        System.out.println("----- MENU -----");
        System.out.println("1. Insertar un codigo en una posicion");
        System.out.println("2. Eliminar el codigo de una posicion");
        System.out.println("3. Buscar un codigo");
        System.out.println("0. Salir");
    }

    /**
     * Opcion 1: inserta un codigo validando espacio libre y posicion.
     *
     * @param sc    lector de consola
     * @param lista lista sobre la que se opera
     */
    static void opcionInsertar(Scanner sc, ListaCredenciales lista) {
        System.out.println("Estado inicial: " + lista.representacion());
        if (lista.estaLlena()) {
            System.out.println("  Operacion rechazada: la lista esta llena.");
        } else {
            int pos = leerEntero(sc, "Posicion de insercion [0-" + lista.cantidad() + "]: ");
            int codigo = leerEntero(sc, "Codigo a insertar: ");
            if (pos < 0 || pos > lista.cantidad()) {
                System.out.println("  Operacion rechazada: posicion fuera de rango (valida: 0 a "
                        + lista.cantidad() + ").");
            } else {
                lista.insertar(pos, codigo);
                System.out.println("  Codigo " + codigo + " insertado en la posicion " + pos + ".");
            }
        }
        System.out.println("Estado final:   " + lista.representacion());
    }

    /**
     * Opcion 2: elimina el codigo de una posicion validando que haya datos y
     * que la posicion exista.
     *
     * @param sc    lector de consola
     * @param lista lista sobre la que se opera
     */
    static void opcionEliminar(Scanner sc, ListaCredenciales lista) {
        System.out.println("Estado inicial: " + lista.representacion());
        if (lista.estaVacia()) {
            System.out.println("  Operacion rechazada: la lista esta vacia.");
        } else {
            int pos = leerEntero(sc, "Posicion a eliminar [0-" + (lista.cantidad() - 1) + "]: ");
            if (pos < 0 || pos >= lista.cantidad()) {
                System.out.println("  Operacion rechazada: posicion fuera de rango (valida: 0 a "
                        + (lista.cantidad() - 1) + ").");
            } else {
                int eliminado = lista.eliminar(pos);
                System.out.println("  Codigo " + eliminado + " eliminado de la posicion " + pos + ".");
            }
        }
        System.out.println("Estado final:   " + lista.representacion());
    }

    /**
     * Opcion 3: busca un codigo; la lista no se modifica.
     *
     * @param sc    lector de consola
     * @param lista lista sobre la que se opera
     */
    static void opcionBuscar(Scanner sc, ListaCredenciales lista) {
        System.out.println("Estado inicial: " + lista.representacion());
        int codigo = leerEntero(sc, "Codigo a buscar: ");
        int pos = lista.buscar(codigo);
        if (pos == -1) {
            System.out.println("  Codigo " + codigo + " no encontrado (resultado: -1).");
        } else {
            System.out.println("  Codigo " + codigo + " encontrado en la posicion " + pos
                    + " (primera coincidencia).");
        }
        System.out.println("Estado final:   " + lista.representacion());
    }

    /**
     * Punto de entrada: pide la longitud del arreglo y repite el menu hasta
     * que el usuario elija salir.
     *
     * @param args no se usan
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== REGISTRO DE CREDENCIALES DEL GIMNASIO ===");

        int capacidad = leerEntero(sc, "Longitud del arreglo [1-"
                + ListaCredencialesArreglo.CAPACIDAD_MAXIMA + "]: ");
        while (capacidad < 1 || capacidad > ListaCredencialesArreglo.CAPACIDAD_MAXIMA) {
            System.out.println("  Longitud fuera de rango: debe estar entre 1 y "
                    + ListaCredencialesArreglo.CAPACIDAD_MAXIMA + ".");
            capacidad = leerEntero(sc, "Longitud del arreglo [1-"
                    + ListaCredencialesArreglo.CAPACIDAD_MAXIMA + "]: ");
        }

        // El cliente trabaja con la interfaz, no con la clase concreta
        ListaCredenciales lista = new ListaCredencialesArreglo(capacidad);
        System.out.println("Lista creada: " + lista.representacion());

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Opcion: ");
            switch (opcion) {
                case 1:
                    opcionInsertar(sc, lista);
                    break;
                case 2:
                    opcionEliminar(sc, lista);
                    break;
                case 3:
                    opcionBuscar(sc, lista);
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("  Opcion no valida: elija 1, 2, 3 o 0.");
            }
        } while (opcion != 0);
    }
}