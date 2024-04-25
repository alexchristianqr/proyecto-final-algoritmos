/*
    Ejercicio 04: Dominio de Tecnologías Frontend:

    Se consideran las habilidades técnicas de 10 candidatos
    para el puesto de Frontend Developer. Cada candidato tiene
    diferentes niveles de experiencia en tecnologías Frontend 
    calificandolos con puntajes entre 0 al 20 respectivamente.
 */
package tareas;

import java.util.Scanner;

public class Ejercicio04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros;
        int[] numerosOriginales;
        boolean ordenado = false;

        System.out.println("Se evaluan las habilidades tecnicas de los postulantes para el puesto de Frontend Developer.");
        System.out.print("\nIntroduce el numero de postulantes: ");
        int numPostulantes = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        numeros = new int[numPostulantes];
        numerosOriginales = new int[numPostulantes];

        System.out.println("Introduce los puntajes de los postulantes");

        for (int i = 0; i < numPostulantes; i++) {
            System.out.print("Puntaje del postulante " + (i + 1) + ": ");
            int puntaje = scanner.nextInt();
            // Verificar si el puntaje es mayor que 20
            if (puntaje < 0 || puntaje > 20) {
                System.out.println("Error: El puntaje no puede ser mayor que 20. Introduce un nuevo puntaje.");
                i--; // Permite al usuario ingresar el puntaje correcto nuevamente
                continue;
            }
            while (puntaje < 0 || puntaje > 20); // Verificar que el puntaje esté en el rango permitido
            numeros[i] = puntaje;
            numerosOriginales[i] = puntaje;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Mostrar datos");
            System.out.println("2. Ordenar insercion");
            System.out.println("3. Mostrar orden final");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\nDatos originales:");
                    mostrarNumeros(numerosOriginales);
                    break;
                case 2:
                    ordenarPorInsercionConMensajesYFilas(numeros);
                    ordenado = true;
                    System.out.println("\nPuntajes ordenados por insercion.");
                    break;

                case 3:
                    if (ordenado) {
                        System.out.println("\nOrden final de los puntajes:");
                        mostrarNumeros(numeros);
                    }
                    break;
                case 4:
                    System.out.println("\nSaliendo del programa...");
                    return;
                default:
                    System.out.println("\nOpcion no valida.");
                    return;
            }
        }
    }

    // Método para ordenar el arreglo utilizando el método de inserción y mostrar los cambios por fila
    public static void ordenarPorInsercionConMensajesYFilas(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Realizar el cambio fila por fila y mostrarlo
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                mostrarNumeros(arr);
            }

            System.out.println("\nSe inserta " + key);
            arr[j + 1] = key;
        }
    }

    // Método para mostrar los números en el arreglo
    public static void mostrarNumeros(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Método para mostrar el proceso de ordenación fila por fila
    public static void mostrarOrdenPorFilas(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }
    }
}
