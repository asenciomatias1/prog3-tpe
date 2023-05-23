package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {

    private HashMap<Integer, ArrayList<Arco>> vertices;

    public GrafoDirigido () {
        this.vertices = new HashMap<>();
    }
    @Override
    public void agregarVertice(int verticeId) {
        // [1, (arco2, arco3)]
        if (!this.contieneVertice(verticeId)) {
            this.vertices.put(verticeId, new ArrayList<>());
        }

    }

    @Override
    public void borrarVertice(int verticeId) {
        this.vertices.remove(verticeId);
        // Navegar en la lista de arcos de cada key para buscar verticeID y borrar el arco de la lista


    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (!this.existeArco(verticeId1, verticeId2)) {
            Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
            this.vertices.get(verticeId1).add(arco);
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        ArrayList<Arco> listaArcos = this.vertices.get(verticeId1);
        listaArcos.removeIf(arco -> arco.getVerticeDestino() == verticeId2);
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        ArrayList<Arco> listaArcos = this.vertices.get(verticeId1);
        for (Arco arco:
             listaArcos) {
            if (arco.getVerticeDestino() == verticeId2) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        ArrayList<Arco> listaArcos = this.vertices.get(verticeId1);
        for (Arco arco:
                listaArcos) {
            if (arco.getVerticeDestino() == verticeId2) {
                return arco;
            }
        }

        return null;
    }

    @Override
    public int cantidadVertices() {
        return this.vertices.size();
    }

    @Override
    public int cantidadArcos() {
        int cantArcos = 0;
        for (ArrayList<Arco> arcos:
             vertices.values()) {
            cantArcos += arcos.size();
        }
        return cantArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Arco> listaArcos = vertices.get(verticeId);
        ArrayList<Integer> listaAdyacentes = new ArrayList<>();
        for (Arco arco:
             listaArcos) {
            listaAdyacentes.add(arco.getVerticeDestino());
        }

        return listaAdyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> listaArcos = new ArrayList<>();
        for (ArrayList<Arco> arcos:
                vertices.values()) {
            for (Arco<T> arco:
                 arcos) {
                listaArcos.add(arco);
            }
        }

        return listaArcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        ArrayList<Arco<T>> listaArcos = new ArrayList<>();
        for (Arco<T> arco:
             vertices.get(verticeId)) {
            listaArcos.add(arco);
        }

        return listaArcos.iterator();
    }

    @Override
    public boolean tieneAdyacentes(int verticeId) {
        return this.vertices.get(verticeId).isEmpty();
    }

}