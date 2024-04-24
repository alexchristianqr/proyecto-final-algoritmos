/*
    Ejercicio 06: Participación en Comunidades de Desarrollo: 

    Se examinan las solicitudes de 10 candidatos para el puesto de 
    Frontend Developer. Cada candidato indica el número de veces que 
    ha participado en conferencias, hackathons, meetups y contribuciones
    a proyectos de código abierto, con un rango de 0 a 30 participaciones.
 */
package tareas;

import java.util.Scanner;

public class Ejercicio06 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros;
        int[] numerosOriginales;
        boolean ordenado = false;

        System.out.println("Numero de veces que el postulante ha participado en conferencias");
        System.out.print("\nIntroduce el numero de postulantes: ");
        int numPostulantes = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        numeros = new int[numPostulantes];
        numerosOriginales = new int[numPostulantes];

        System.out.println("Introduce las participaciones de los postulantes");

        for (int i = 0; i < numPostulantes; i++) {
            System.out.print("Participaciones del postulante " + (i + 1) + ": ");
            int participaciones = scanner.nextInt();
            // Verificar si las participaciones son mayor a 30
            if (participaciones < 0 || participaciones > 30) {
                System.out.println("Error: Las participaciones no puede ser mayor que 30. Introduce un nuevo puntaje.");
                i--; // Permite al usuario ingresar el numero de participaciones correcta nuevamente
                continue;

            }
            while (participaciones < 0 || participaciones > 30); // Verificar que las participaciones esté en el rango permitido
            numeros[i] = participaciones;
            numerosOriginales[i] = participaciones;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Mostrar datos");
            System.out.println("2. Ordenar insercion");
            System.out.println("3. Mostrar orden final");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("\nDatos originales:");
                    mostrarNumeros(numerosOriginales);
                }
                case 2: {
                    ordenarPorInsercionConMensajesYFilas(numeros);
                    ordenado = true;
                    System.out.println("\nParticipaciones ordenadss por insercion.");
                }

                case 3: {
                    if (ordenado) {
                        System.out.println("\nOrden final de las participaciones:");
                        mostrarNumeros(numeros);
                    }
                }
                case 4: {
                    System.out.println("\nSaliendo del programa...");
                    return;
                }
                default:
                    System.out.println("\nOpcion no valida.");
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
