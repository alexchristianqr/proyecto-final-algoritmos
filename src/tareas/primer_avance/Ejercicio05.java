/*
    Ejercicio 5 - Burbuja:Colaboración en Equipo

    Se evalúa en rango de 0 a 10 por medio de una entrevista, la capacidad del postulante para la colaboración en equipo teniendo en cuenta: 
    comunicación, colaboración y resolución de problemas de manera conjunta.
 */
package tareas.primer_avance;

import java.util.Scanner;

public class Ejercicio05 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] valores = null;

        while (true) {
            System.out.println("\nSe evalúa en rango de 0 a 10 la capacidad del postulante para la colaboración en equipo:");
            System.out.println("1. Cargar valores (Nota por Postulante)");
            System.out.println("2. Ordenar (Metodo Burbuja)");
            System.out.println("3. Mostrar");
            System.out.println("4. Reiniciar");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    valores = cargarValores(scanner);
                    break;
                case 2:
                    ordenarBurbuja(valores);
                    break;
                case 3:
                    mostrarValores(valores);
                    break;
                case 4:
                    valores = null;
                    System.out.println("Valores reiniciados");
                    break;
                case 5:
                    System.out.println("Hasta Luego");
                    return;
                default:
                    System.out.println("Opción invalida");
            }
        }
    }

    public static int[] cargarValores(Scanner scanner) {
        System.out.print("Ingrese el número de postulantes: ");
        int tamano = scanner.nextInt();
        int[] valores = new int[tamano];

        System.out.println("\nIngrese los valores: ");
        int valor;
        int contador = 0;

        do {
            System.out.print("Postulante N°" + (contador + 1) + ": ");
            valor = scanner.nextInt();
            if (valor >= 0) { // Verificar si el valor ingresado es no negativo
                if (valor != -1) {
                    if (valor <= 10) {
                        valores[contador] = valor;
                        contador++;
                    } else {
                        System.out.println("Error: Por favor, ingrese un valor del 0 a 10.");
                    }
                }
            } else {
                System.out.println("Error: El número ingresado no puede ser negativo.");
            }
        } while (valor != -1 && contador < tamano);

        System.out.println("Valores cargados correctamente");
        return valores;
    }

    public static void mostrarValores(int[] valores) {
        if (valores == null || valores.length == 0) {
            System.out.println("No hay valores cargados");
            return;
        }
        System.out.print("Valores cargados: ");
        for (int i = 0; i < valores.length; i++) {
            System.out.print(valores[i]);
            if (i < valores.length - 1) {
                System.out.print("| ");
            }
        }
        System.out.println();
    }

    public static void ordenarBurbuja(int lista[]) {
        int aux;

        for (int i = 0; i < lista.length - 1; i++) {
            int movimientos = 0; // Contador de movimientos para este ciclo
            for (int j = 0; j < lista.length - 1 - i; j++) {
                if (lista[j] > lista[j + 1]) {
                    aux = lista[j];
                    lista[j] = lista[j + 1];
                    lista[j + 1] = aux;
                    movimientos++; // Incrementa el contador de movimientos para este ciclo
                }
            }
            System.out.println("Cantidad de movimientos en el ciclo " + (i + 1) + ": " + movimientos);
        }
        System.out.println("Valores ordenados correctamente");
    }
}
