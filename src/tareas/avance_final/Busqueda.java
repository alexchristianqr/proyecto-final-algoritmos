package tareas.avance_final;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Busqueda {

    static List<Nodo> bfs(Grafo grafo, String inicio, String destino) {
        Nodo nodoInicio = grafo.obtenerNodo(inicio);
        Nodo nodoDestino = grafo.obtenerNodo(destino);

        if (nodoInicio == null || nodoDestino == null) {
            return null;
        }

        Queue<Nodo> queue = new LinkedList<>();
        Map<Nodo, Nodo> predecesores = new HashMap<>();
        Set<Nodo> visitados = new HashSet<>();

        queue.add(nodoInicio);
        visitados.add(nodoInicio);

        while (!queue.isEmpty()) {
            Nodo nodoActual = queue.poll();
            System.out.println("Visitando nodo: " + nodoActual);

            if (nodoActual.equals(nodoDestino)) {
                System.out.println("Destino encontrado!");
                return reconstruirRuta(predecesores, nodoInicio, nodoDestino);
            }

            for (Nodo adyacente : nodoActual.adyacentes) {
                if (!visitados.contains(adyacente)) {
                    System.out.println("Añadiendo nodo a la cola: " + adyacente);
                    visitados.add(adyacente);
                    predecesores.put(adyacente, nodoActual);
                    queue.add(adyacente);
                }
            }
        }

        return null;
    }

    private static List<Nodo> reconstruirRuta(Map<Nodo, Nodo> predecesores, Nodo inicio, Nodo destino) {
        List<Nodo> ruta = new LinkedList<>();
        for (Nodo en = destino; en != null; en = predecesores.get(en)) {
            ruta.add(en);
        }
        Collections.reverse(ruta);
        return ruta;
    }
}
