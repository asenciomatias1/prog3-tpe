package Servicios;

import java.util.*;

import Grafo.Grafo;

public class ServicioBFS {

    private HashMap<Integer, String> map;
    private Grafo<?> grafo;

    public ServicioBFS(Grafo<?> grafo) {
        this.map = new HashMap<>();
        this.grafo = grafo;
    }

    public List<Integer> bfsForest() {
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while (it.hasNext()) {
            this.map.put(it.next(), "noVisitado");
        }
        ArrayList<Integer> listaNoVisitados = new ArrayList<>();
        it = this.grafo.obtenerVertices();
        while (it.hasNext()) {
            Integer verticeActual = it.next();
            if (this.map.get(verticeActual).equals("noVisitado")) {
                listaNoVisitados.add(verticeActual);
                bfs_visit(verticeActual,listaNoVisitados);
            }
        }
        return listaNoVisitados;
    }

    private void bfs_visit(int verticeActual, ArrayList<Integer> noVisitados) {
        Queue<Integer> queue = new LinkedList<>();
        this.map.replace(verticeActual, "visitado");
        queue.offer(verticeActual);
        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            Iterator<Integer> it = this.grafo.obtenerAdyacentes(v);
            while (it.hasNext()) {
                int i = it.next();
                if (this.map.get(i).equals("noVisitado")) {
                    this.map.replace(i, "visitado");
                    noVisitados.add(i);
                    queue.offer(i);
                }
            }
        }
    }
}