package main.java.framework.graph;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import java.util.*;

public abstract class BasicGraph<VertexT extends Vertex, EdgeT extends Edge> {

    protected Graph<VertexT, EdgeT> graph;
    protected Map<Integer, VertexT> vertices;
    protected Map<Tupel<Integer, Integer>, EdgeT> edges;

    public BasicGraph(Graph<VertexT, EdgeT> graph) {
        this.graph = graph;
        this.vertices = new HashMap<>();
        for(VertexT vertex : graph.vertexSet()) {
            this.vertices.put(vertex.getId(), vertex);
        }
        this.edges = new HashMap<>();
        for(EdgeT edge : graph.edgeSet()) {
            this.edges.put(new Tupel<Integer, Integer>(edge.getFrom().getId(), edge.getTo().getId()), edge);
        }
    }

    public int[] getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<Integer>();
        VertexT mappedVertex = this.vertices.get(vertex);
        if (mappedVertex == null) {
            return new int[0];
        }
        for(EdgeT edges : this.graph.outgoingEdgesOf(mappedVertex)) {
            neighbors.add(Graphs.getOppositeVertex(this.graph, edges, mappedVertex).getId());
        }

        int[] mappedNeighbors = new int[neighbors.size()];
        for (int i = 0; i < neighbors.size(); i++) {
            mappedNeighbors[i] = neighbors.get(i);
        }
        return mappedNeighbors;
    }

    public boolean containsNode(int vertex) {
        return vertices.containsKey(vertex);
    }

    public boolean containsEdge(int vertexStart, int vertexEnd) {
        if (containsNode(vertexStart) && containsNode(vertexEnd)) {
            return this.edges.containsKey(new Tupel<Integer, Integer>(vertexStart, vertexEnd));
        } else {
            return false;
        }
    }

    public int numberOfVertices() {
        return this.graph.vertexSet().size();
    }

    public int numberOfEdges() {
        return this.graph.edgeSet().size();
    }

    protected static class Tupel<Value1T, Value2T> {
        private Value1T value1;
        private Value2T value2;

        public Tupel(Value1T value1, Value2T value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public Value1T getValue1() {
            return value1;
        }

        public Value2T getValue2() {
            return value2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tupel<Value1T, Value2T> tupel = (Tupel<Value1T, Value2T>) o;
            return value1.equals(tupel.value1) &&
                    value2.equals(tupel.value2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value1, value2);
        }
    }
}
