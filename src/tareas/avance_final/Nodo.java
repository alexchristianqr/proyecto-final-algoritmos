package tareas.avance_final;

import java.util.*;

// Clase para representar cada parada de autobús
public class Nodo {

    String nombre;
    List<Nodo> adyacentes;

    Nodo(String nombre) {
        this.nombre = nombre;
        this.adyacentes = new ArrayList<>();
    }

    void agregarAdyacente(Nodo nodo) {
        adyacentes.add(nodo);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
