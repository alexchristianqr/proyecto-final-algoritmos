package tareas.avance_final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clase para representar el sistema de rutas de autobuses (grafo)
public class Grafo {

    private Map<String, Nodo> nodos;

    Grafo() {
        nodos = new HashMap<>();
    }

    void agregarNodo(String nombre) {
        nodos.putIfAbsent(nombre, new Nodo(nombre));
    }

    void agregarArista(String nombre1, String nombre2) {
        Nodo nodo1 = nodos.get(nombre1);
        Nodo nodo2 = nodos.get(nombre2);
        if (nodo1 != null && nodo2 != null) {
            nodo1.agregarAdyacente(nodo2);
            nodo2.agregarAdyacente(nodo1); // Para grafos no dirigidos
        }
    }

    Nodo obtenerNodo(String nombre) {
        return nodos.get(nombre);
    }

    void mostrarGrafo() {
        for (Nodo nodo : nodos.values()) {
            System.out.print(nodo + " -> ");
            for (Nodo adyacente : nodo.adyacentes) {
                System.out.print(adyacente + " ");
            }
            System.out.println();
        }
    }
}
