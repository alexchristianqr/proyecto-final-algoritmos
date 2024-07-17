package tareas.avance_final;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregarNodo("D");
        grafo.agregarNodo("E");

        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");
        grafo.agregarArista("C", "D");
        grafo.agregarArista("D", "E");

        System.out.println("Grafo creado:");
        grafo.mostrarGrafo();

        System.out.println("\nBuscando ruta más corta de A a E...");
        List<Nodo> ruta = Busqueda.bfs(grafo, "A", "E");

        if (ruta != null) {
            System.out.println("Ruta más corta de A a E: " + ruta);
        } else {
            System.out.println("No se encontró una ruta de A a E.");
        }

    }
}
