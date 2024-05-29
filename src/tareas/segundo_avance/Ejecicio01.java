package tareas.segundo_avance;

import java.util.Scanner;

/**
 *
 * @author Alex Christian
 */
public class Ejecicio01 {

    /*
        La tienda de zapatillas "YOLU" esta contabilizando los lotes de zapatillas que ingresan al almacén. 
        Por ello, para un mejor control, se requiere ingresar y ordenar por los codigos de identificación unicos (N° LOTE). 
        Además, se debe validar que no se pueden permitir valores negativos y ceros. 
     */
    public static void main(String[] args) {
        // Inicializamos las variables a utilizar
        int[] miarray = {}; // {60, 12, 20, 8, 35, 6, 10, 11};

        Scanner dato = new Scanner(System.in);

        // Obtener tamaño de los elementos del arreglo
        System.out.println("Ingresar un valor para definir el tamano del arreglo:");
        miarray = new int[dato.nextInt()];

        // Inicializar menu principal
        mostrarMenu(miarray);
    }

    public static void mostrarMenu(int[] miarray) {
        Scanner dato = new Scanner(System.in);
        int opcion;

        System.out.println("-- Tienda YOLU -- ");
        System.out.println("1: Cargar lotes");
        System.out.println("2: Mostrar lotes");
        System.out.println("3: Ordenar lotes");
        System.out.println("4: Salir");
        System.out.println("-- * --");
        opcion = dato.nextInt();

        switch (opcion) {
            case 1:
                cargar(miarray, 0);
                mostrarMenu(miarray);
                break;
            case 2:
                mostrar(miarray);
                mostrarMenu(miarray);
                break;
            case 3:
                int indiceIzq = 0;
                int indiceDer = miarray.length - 1;
                ordenarPorQuicksort(miarray, indiceIzq, indiceDer);
                mostrarMenu(miarray);
                break;
            case 4:
                dato.close();
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void cargar(int[] miarray, int indice) {
        Scanner dato = new Scanner(System.in);
        int valor;

        int numero = indice + 1;
        System.out.println("Ingresar N° Lote: " + numero);
        valor = dato.nextInt();

        System.out.println("es: " + valor);

        if (esNegativo(valor)) {
            System.out.println("Error: El N° Lote no puede ser negativo");
            cargar(miarray, indice); // Recursión: volver a pedir el número hasta que sea válido
        } else if (esCero(valor)) {
            System.out.println("Error: El N° Lote no puede ser cero");
            cargar(miarray, indice); // Recursión: volver a pedir el número hasta que sea válido
        } else {
            // Obtener valores para guardar en cada posición del arreglo
            miarray[indice] = valor;
            if (indice < miarray.length - 1) {
                cargar(miarray, indice + 1); // Recursión: pasar al siguiente índice
            }
        }
    }

    public static void mostrar(int[] miarray) {
        // Mostrar arreglo
        System.out.print("[");
        for (int i = 0; i < miarray.length; i++) {
            System.out.print("" + miarray[i] + "");

            if (i != miarray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static boolean esNegativo(int valor) {
        return valor < 0;
    }

    public static boolean esCero(int valor) {
        return valor == 0;
    }

    public static void ordenarPorQuicksort(int[] miarray, int indiceIzq, int indiceDer) {
        int pivote, i, j, temp;

        // Se toma como pivote el primer elemento en la posición 0
        pivote = miarray[indiceIzq];

        // Se definen los indices de izquierda y derecha
        i = indiceIzq;
        j = indiceDer;

        // Validar que i sea menor a j
        while (i < j) {
            // Mientras el elemento actual en i sea menor o igual al pivote; i sea menor a j
            while (miarray[i] <= pivote && i < j) {
                i++;
            }

            // Mientras el elemento actual en j sea mayor al pivote
            while (miarray[j] > pivote) {
                j--;
            }

            // Validar que i sea menor a j
            if (i < j) {
                temp = miarray[i];
                miarray[i] = miarray[j];
                miarray[j] = temp;
            }
        }

        miarray[indiceIzq] = miarray[j];
        miarray[j] = pivote;

        int derecha = j - 1;// Obtener el ultimo indice recorrido en j y restarle -1
        if (indiceIzq < derecha) {
            ordenarPorQuicksort(miarray, indiceIzq, derecha);
        }

        int izquierda = j + 1;// Obtener el ultimo indice recorrido en j y sumarle +1
        if (izquierda < indiceDer) {
            ordenarPorQuicksort(miarray, izquierda, indiceDer);
        }

    }
}
