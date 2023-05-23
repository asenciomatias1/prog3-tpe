package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Grafo.Grafo;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;
    private HashMap<Integer, String> map;

    // Servicio caminos
    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
        this.map = new HashMap<>();
    }

    public List<List<Integer>> caminos() {
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while(it.hasNext()){
            this.map.put(it.next(), "blanco");
        }

        ArrayList<Integer> lista = new ArrayList();
        List<List<Integer>> recorrido = new ArrayList();
        lista.add(this.origen);
        buscarCamino(this.origen, lista, recorrido);
        return recorrido;
    }

    private void buscarCamino(int origen, List<Integer> lista, List<List<Integer>> recorrido) {
        this.map.replace(origen, "amarillo");
        if (lista.size() < this.lim) {
            if (this.destino == origen) {
                recorrido.add(new ArrayList<>(lista));
            } else {
                Iterator<Integer> it = this.grafo.obtenerAdyacentes(origen);
                while (it.hasNext()) {
                    int aux = it.next();
                    if (this.grafo.tieneAdyacentes(aux) && this.map.get(aux).equals("blanco")) {
                        lista.add(aux);
                        buscarCamino(aux, lista, recorrido);
                        lista.remove(lista.size()-1);
                    }
                }
            }
        }
        this.map.replace(origen, "blanco");
    }


}
