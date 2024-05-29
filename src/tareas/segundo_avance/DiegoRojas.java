/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tareas.segundo_avance;

/**
 *
 * @author diego
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class DiegoRojas {

    static class Reclutador {
        private String nom;
        private List<String> postulantesAsignados;

        public Reclutador(String nombre) {
            this.nom= nombre;
            this.postulantesAsignados = new ArrayList<>();
        }

        public void agregarPostulante(String nombrePostulante) {
            postulantesAsignados.add(nombrePostulante);
        }

        public String getNombre() {
            return nom;
        }

        public List<String> getPostulantesAsignados() {
            return postulantesAsignados;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Reclutador> reclutadores = new ArrayList<>();

        int cantidadReclutadores = obtenerCantidad("reclutadores", s);
        for (int i = 1; i <= cantidadReclutadores; i++) {
            System.out.println("Ingrese el nombre del reclutador " + i + ":");
            String nombreReclutador = s.nextLine();
            reclutadores.add(new Reclutador(nombreReclutador));
        }

        int cantidadPostulantes = obtenerCantidad("postulantes", s);
        for (int i = 1; i <= cantidadPostulantes; i++) {
            System.out.println("Ingrese el nombre del postulante " + i + ":");
            String nombrePostulante = s.nextLine();
            asignarPostulante(reclutadores, nombrePostulante, s);
        }

        // Mostrar la asignación de postulantes a reclutadores
        mostrarAsignacion(reclutadores);
    }

    public static int obtenerCantidad(String tipo, Scanner scanner) {
        int cant = 0;
        while (true) {
            try {
                System.out.println("Ingrese la cantidad de " + tipo + ":");
                cant = scanner.nextInt();
                scanner.nextLine();  // Consumir el salto de línea
                if (cant <= 0) {
                    System.out.println("Error: ingrese un número válido mayor que 0.");
                } else {
                    break;  // Salir del bucle si se ingresa un número válido
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un número válido.");
                scanner.nextLine();  // Consumir la entrada inválida
            }
        }
        return cant;
    }

    public static void asignarPostulante(List<Reclutador> reclutadores, String nombrePostulante, Scanner scanner) {
        for (Reclutador reclutador : reclutadores) {
            String resp;
            while (true) {
                System.out.println("¿El reclutador " + reclutador.getNombre() + " evaluará a " + nombrePostulante + "? (s/n)");
                resp = scanner.nextLine().toLowerCase();
                if (resp.equals("s") || resp.equals("n")) {
                    break;
                } else {
                    System.out.println("Error: ingrese 's' o 'n'.");
                }
            }
            if (resp.equals("s")) {
                reclutador.agregarPostulante(nombrePostulante);
                break;  // Salir del bucle si el postulante fue asignado
            }
        }
    }

    public static void mostrarAsignacion(List<Reclutador> reclutadores) {
        System.out.println("Asignación de Postulantes:");
        for (Reclutador reclutador : reclutadores) {
            System.out.println("Reclutador: " + reclutador.getNombre());
            System.out.println("Postulantes asignados: " + reclutador.getPostulantesAsignados());
        }
    }
}