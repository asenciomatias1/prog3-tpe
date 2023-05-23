package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {

    private HashMap<Integer, ArrayList<Arco>> vertices;

    public GrafoDirigido () {
        this.vertices = new HashMap<>();
    }

    /**
    * Complejidad: O(1) debido a que la operación put en el HashMap tiene una complejidad 
    * constante, y la creación de un nuevo ArrayList también tiene una complejidad constante.
    */
    @Override
    public void agregarVertice(int verticeId) {
        // [1, (arco2, arco3)]
        if (!this.contieneVertice(verticeId)) {
            this.vertices.put(verticeId, new ArrayList<>());
        }

    }

    /**
    * Complejidad: O(V + A) donde V es el número de vértices y A es el número total de arcos
    * del grafo. Primero elimina el vértice del mapa de vértices, lo cual tiene una
    * complejidad de O(1), luego recorre la lista de arcos de cada vértice en el mapa y elimina
    * los arcos cuyo vértice destino coincida con el vértice a eliminar, lo que implica recorrer
    * en promedio O(A) arcos en total y realizar una comparación por arco, en total la complejidad
    * es O(V+A).
    */
    @Override
    public void borrarVertice(int verticeId) {
        this.vertices.remove(verticeId);
        for (ArrayList<Arco> listaArcos: vertices.values()) {
            listaArcos.removeIf(arco -> arco.getVerticeDestino() == verticeId);
        }

    }

    /**
    * Complejidad: O(1) debido a que la operación add en un ArrayList tiene una complejidad 
    * constante, y obtener el valor correspondiente a verticeId1 en el HashMap también tiene 
    * una complejidad constante.
    */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (!this.existeArco(verticeId1, verticeId2)) {
            Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
            this.vertices.get(verticeId1).add(arco);
        }
    }

    /**
    * Complejidad: O(N) donde N es el número de arcos en la lista correspondiente al verticeId1.
    * La función removeIf realiza una iteración sobre la lista de arcos y verifica una
    * condición para cada elemento. En el peor caso, podría tener que revisar todos los
    * elementos de la lista, lo que resulta en una complejidad lineal.
    */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        ArrayList<Arco> listaArcos = this.vertices.get(verticeId1);
        listaArcos.removeIf(arco -> arco.getVerticeDestino() == verticeId2);
    }

    /**
    * Complejidad: O(1) debido a que la operación containsKey en el HashMap tiene una
    * complejidad constante.
    */
    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    /**
    * Complejidad: O(N) donde N es el número de arcos en la lista correspondiente al verticeId1.
    * El método realiza un recorrido lineal a través de los arcos de la lista para verificar
    * si existe un arco con verticeId2 como destino. En el peor caso, debe revisar todos los
    * elementos de la lista, lo que resulta en una complejidad lineal.
    */
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

    /**
    * Complejidad: O(N) donde N es el número de arcos en la lista correspondiente al verticeId1.
    * El método realiza un recorrido lineal a través de los arcos de la lista para buscar un
    * arco con verticeId2 como destino. En el peor caso, debe revisar todos los elementos de
    * la lista, lo que resulta en una complejidad lineal.
    */
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

    /**
    * Complejidad: O(1) debido a que la operación size en el HashMap tiene una complejidad
    * constante. No importa cuántos elementos haya en el mapa, el método simplemente devuelve
    * el tamaño del mapa, por lo que la complejidad es constante.
    */
    @Override
    public int cantidadVertices() {
        return this.vertices.size();
    }

    /**
    * Complejidad: O(V), donde V es el número de vértices en el grafo. El método recorre todos
    * los valores (listas de arcos) en el mapa de vértices y suma el tamaño de cada lista.
    * Como el número de vértices es proporcional a la cantidad de arcos, la complejidad es
    * lineal en función de la cantidad de vértices.
    */
    @Override
    public int cantidadArcos() {
        int cantArcos = 0;
        for (ArrayList<Arco> arcos:
             vertices.values()) {
            cantArcos += arcos.size();
        }
        return cantArcos;
    }

    /**
    * Complejidad: O(1) debido a que la operación keySet en el HashMap tiene una complejidad
    * constante. El método obtiene la colección de claves del mapa (que son los vértices) y
    * devuelve un iterador sobre ellas. La operación de obtener las claves del mapa tiene una
    * complejidad constante.
    */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    /**
    * Complejidad: O(A), donde A es el número de arcos adyacentes al vértice dado. El método
    * obtiene la lista de arcos adyacentes al vértice y crea una nueva lista de vértices
    * adyacentes, iterando sobre los arcos y obteniendo sus vértices destino. Luego, devuelve
    * un iterador sobre la lista de vértices adyacentes. La complejidad depende del número de
    * arcos adyacentes al vértice, ya que recorre cada arco una vez para obtener sus vértices
    * destino.
    */
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

    /**
    * Complejidad: O(V + A), donde V es el número de vértices y A es el número de arcos en el
    * grafo. El método crea una lista de arcos y recorre todos los valores del mapa de vértices
    * (que representa las listas de arcos adyacentes). Luego, itera sobre cada lista de arcos
    * y agrega cada arco a la lista de arcos. Finalmente, devuelve un iterador sobre la lista
    * de arcos. La complejidad depende del número total de vértices y arcos en el grafo, ya
    * que recorre todas las listas de arcos.
    */
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

    /**
    * Complejidad: O(A), donde A es el número de arcos adyacentes al vértice especificado. El
    * método obtiene la lista de arcos adyacentes al vértice dado, recorre esta lista y los
    * agrega a una nueva lista de arcos. Luego, devuelve un iterador sobre la lista de arcos.
    * La complejidad está determinada por el número de arcos adyacentes al vértice especificado.
    */
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        ArrayList<Arco<T>> listaArcos = new ArrayList<>();
        for (Arco<T> arco:
             vertices.get(verticeId)) {
            listaArcos.add(arco);
        }

        return listaArcos.iterator();
    }

    /**
    * Complejidad: O(1). El método obtiene la lista de arcos adyacentes al vértice
    * especificado y verifica si está vacía. La operación de obtención de la lista de arcos
    * y la verificación de si está vacía tienen una complejidad constante, independientemente
    * del tamaño de la lista de arcos. Por lo tanto, la complejidad total del método es O(1).
    */
    @Override
    public boolean tieneAdyacentes(int verticeId) {
        return this.vertices.get(verticeId).isEmpty();
    }

}