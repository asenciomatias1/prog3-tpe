package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Grafo.Grafo;

public class ServicioDFS {

    private HashMap<Integer, String> map;
    private Grafo<?> grafo;

    public ServicioDFS(Grafo<?> grafo) {
        this.map = new HashMap<>();
        this.grafo = grafo;
    }

    public List<Integer> dfsForest(){
        Iterator<Integer> it = this.grafo.obtenerVertices();
        ArrayList<Integer> listaNoVisitados = new ArrayList<>();
        while(it.hasNext()){
            this.map.put(it.next(), "blanco");
        }
        it = this.grafo.obtenerVertices();
        while (it.hasNext()){
            Integer verticeActual = it.next();
            if (this.map.get(verticeActual).equals("blanco")){
                listaNoVisitados.add(verticeActual);
                dfs_visit(verticeActual, listaNoVisitados);
            }
        }
        return listaNoVisitados;
    }

    private void dfs_visit(int verticeActual, ArrayList<Integer> noVisitados) {
        this.map.replace(verticeActual, "amarillo");
        Iterator<Integer> it = this.grafo.obtenerAdyacentes(verticeActual);
        while (it.hasNext()){
            int aux = it.next();
            noVisitados.add(aux);
            if(this.map.get(aux).equals("blanco")){
                dfs_visit(aux, noVisitados);
            }
        }
        this.map.replace(verticeActual, "negro");
    }
}
