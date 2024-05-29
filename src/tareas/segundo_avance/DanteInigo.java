package tareas.segundo_avance;

import java.util.Scanner;
/**
 *
 * @author danteInigo
 */
public class DanteInigo {
    /*
        Se gestiona las calificaciones en un examen, necesita ordenar la lista en orden ascendente, 
        validando que las calificaciones no pueden ser negativas.
    */
    public static void main(String[] args) {
        // Inicializamos las variables a utilizar
        int[] ArregloNotas = {};

        Scanner dato = new Scanner(System.in);

        // Obtener tamaño de los elementos del arreglo
        System.out.println("Ingresar un valor para definir el tamano del arreglo:");
        ArregloNotas = new int[dato.nextInt()];

        // Inicializar menu principal
        menuPrincipal(ArregloNotas);
    }

    public static void menuPrincipal(int[] ArregloNotas) {
        Scanner dato = new Scanner(System.in);
        int opcion;

        System.out.println("***********Gestión de Notas de Estudiantes***********");
        System.out.println("1: Cargar Nota");
        System.out.println("2: Mostrar Notas");
        System.out.println("3: Ordenar Notas");
        System.out.println("4: Salir");
        System.out.println("*****************************************************");
        opcion = dato.nextInt();

        switch (opcion) {
            case 1:
                cargar(ArregloNotas, 0);
                menuPrincipal(ArregloNotas);
                break;
            case 2:
                mostrar(ArregloNotas);
                menuPrincipal(ArregloNotas);
                break;
            case 3:
                int indiceIzq = 0;
                int indiceDer = ArregloNotas.length - 1;
                MergeSort(ArregloNotas, indiceIzq, indiceDer);
                menuPrincipal(ArregloNotas);
                break;
            case 4:
                dato.close();
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void cargar(int[] Arreglo, int indice) {
        Scanner dato = new Scanner(System.in);
        int valor;

        int numero = indice + 1;
        System.out.println("Ingresar la nota del estudiante " + numero + ":");
        valor = dato.nextInt();

        if (esNegativo(valor)) {
            System.out.println("Error: La nota no puede ser negativa");
            cargar(Arreglo, indice); // Recursión: volver a pedir la nota hasta que sea válida
        } else {
            // Guardar valor en la posición del arreglo
            Arreglo[indice] = valor;
            if (indice < Arreglo.length - 1) {
                cargar(Arreglo, indice + 1); // Recursión: pasar al siguiente índice
            }
        }
    }

    public static void mostrar(int[] Arreglo) {
        // Mostrar arreglo
        System.out.print("[");
        for (int i = 0; i < Arreglo.length; i++) {
            System.out.print("" + Arreglo[i] + "");

            if (i != Arreglo.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static boolean esNegativo(int valor) {
        return valor < 0;
    }

    public static void MergeSort(int[] Arreglo, int izq, int der) {
        if (izq < der) {
            int m = (izq + der) / 2;
            MergeSort(Arreglo, izq, m);
            MergeSort(Arreglo, m + 1, der);
            mezclar(Arreglo, izq, m, der);
        }
    }

    public static void mezclar(int[] Arreglo, int izq, int m, int der) {
        int n1 = m - izq + 1;
        int n2 = der - m;

        int[] izquierdaArray = new int[n1];
        int[] derechaArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            izquierdaArray[i] = Arreglo[izq + i];
        }
        for (int j = 0; j < n2; j++) {
            derechaArray[j] = Arreglo[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = izq;
        while (i < n1 && j < n2) {
            if (izquierdaArray[i] <= derechaArray[j]) {
                Arreglo[k] = izquierdaArray[i];
                i++;
            } else {
                Arreglo[k] = derechaArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            Arreglo[k] = izquierdaArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            Arreglo[k] = derechaArray[j];
            j++;
            k++;
        }
    }
}
