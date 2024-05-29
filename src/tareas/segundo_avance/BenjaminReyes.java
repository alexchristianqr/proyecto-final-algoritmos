/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tareas.segundo_avance;
import java.util.Arrays;

public class BenjaminReyes {
    public static void main(String[] args) {
        int[] arreglo = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int clave = 10;
        int resultado = busquedaBinaria(arreglo, clave);
        
        if (resultado == -1) {
            System.out.println("El elemento no está presente en el arreglo");
        } else {
            System.out.println("Elemento encontrado en el índice " + resultado);
        }
    }

    public static int busquedaBinaria(int[] arreglo, int clave) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] == clave) {
                return medio;
            }

            if (arreglo[medio] < clave) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        
        return -1;
    }
}
