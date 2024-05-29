/*En una empresa de ventas, el gerente necesita analizar los resultados mensuales de los vendedores para identificar
tendencias y evaluar el rendimiento del equipo, para facilitar el analisis el gerente necesita ordenar los datos*/

package tareas.segundo_avance;

import java.util.Scanner;

public class KevinGuevara {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[6];

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Ingresar resultados del mes de los vendedores:");
            System.out.println("2. Ordenar");
            System.out.println("3. Mostrar");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    cargarArreglo(scanner, array);
                    break;
                case 2:
                    shellsort(array);
                    System.out.println("\n");
                    System.out.println("Arreglo ordenado.");
                    break;
                case 3:
                    mostrarArray(array);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void cargarArreglo(Scanner scanner, int[] array) {
        System.out.println("Ingrese resultado de ventas del mes: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
    }

    public static void shellsort(int[] array) {
        int n = array.length;
        for (int arreglo = n / 2; arreglo > 0; arreglo /= 2) {
            for (int i = arreglo; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= arreglo && array[j - arreglo] > temp; j -= arreglo) {
                    array[j] = array[j - arreglo];
                    mostrarArray(array); 
                }
                array[j] = temp;
                mostrarArray(array); 
            }
        }
    }

    public static void mostrarArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
        
}
