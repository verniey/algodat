package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

import java.util.*;


public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Veronika", // Vorname
                "Zapodobnikova", // Nachname
                "" // Matrikelnummer
        );
    }


    // Implementieren Sie hier Ihre Lösung für die Heapify-Up-Operation
    public void heapifyUp(PriorityQueue priorityQueue, int index) {
        if (index > 1) {
            int j = index / 2;
            if (priorityQueue.getWeight(index) < priorityQueue.getWeight(j)) {
                priorityQueue.swap(index, j);
                heapifyUp(priorityQueue, j);
            }
        }
    }

    // Implementieren Sie hier Ihre Lösung für die Heapify-Down-Operation
    public void heapifyDown(PriorityQueue priorityQueue, int index) {

        int n = priorityQueue.length() - 1;
        int j;
        if (2 * index > n) {
            return;
        } else if (2 * index < n) {
            int left = 2 * index;
            int right = 2 * index + 1;
            j = priorityQueue.getWeight(left) < priorityQueue.getWeight(right) ? left : right;
        } else {
            j = 2 * index;
        }
        if (priorityQueue.getWeight(j) < priorityQueue.getWeight(index)) {
            priorityQueue.swap(index, j);
            heapifyDown(priorityQueue, j);
        }
    }

    // Implementieren Sie hier Ihre Lösung für den Algorithmus von Prim
    public double prim(Graph g, PriorityQueue q, int[] predecessors) {

        double[] keys = new double[g.numberOfVertices() + 1];
        LinkedList<Integer> s = new LinkedList<>();

        boolean chooseStart = true;


        for (int i = 1; i < g.numberOfVertices() + 1; i++) {
            keys[i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 1; i <= g.numberOfVertices(); i++) {
            if (g.isRelevant(i)) {
                q.add(Double.POSITIVE_INFINITY, i);

                if (chooseStart) {
                    chooseStart = false;
                    q.decreaseWeight(0, i);
                    predecessors[i] = 1;
                }
            }

        }


        while (!q.isEmpty()) {
            int u = q.removeFirst();

            s.add(u);

            int[] adjacent = g.getNeighbors(u);
            for (int i = 0; i < adjacent.length; i++) {

                int v = adjacent[i];

                if (!g.isRelevant(v)) {
                    continue;
                }
                double weight = g.getEdgeWeight(u,v);

                if (!s.contains(v) && keys[v] > weight) {
                    keys[v] = weight;
                    q.decreaseWeight(keys[v],v);
                    predecessors[v] = u;
                }


            }
        }

        int path = 0;
        for (int i = 1; i < s.size(); i++) {
            int u = s.get(i);
            int v = predecessors[u];
            path += g.getEdgeWeight(u, v);
        }

        return path;
    }

    // Implementieren Sie hier Ihre Lösung für die Find-Set-Operation
    public int findset(UnionFindDataStructure unionFindDataStructure, int vertexId) {
        int h = vertexId;

        while (unionFindDataStructure.getParent(h) != h) {
            h = unionFindDataStructure.getParent(h);
        }

        return h;
    }

    // Implementieren Sie hier Ihre Lösung für den Algorithmus von Kruskal
    public double kruskal(Graph g, UnionFindDataStructure u, boolean[] chosenEdges) {

        int[][] sortedEdges = g.getEdgesOrderedByWeight();

        LinkedList<Double> t = new LinkedList<>();
        for (int i = 1; i < g.numberOfVertices() + 1; i++) {

            u.makeset(i);

        }

        for (int i = 0; i < g.numberOfEdges(); i++) {

            int s = sortedEdges[i][0];
            int v = sortedEdges[i][1];


            if (g.isRelevant(s) && g.isRelevant(v)) {

                int h1 = u.findset(s);
                int h2 = u.findset(v);

                if (h1 != h2) {
                    chosenEdges[i] = true;
                    u.union(h1,h2);
                    double e = g.getEdgeWeight(s,v);
                    t.add(e);
                }
            }

        }

        double lenght = 0;
        for (Double i : t) {
            lenght += i;
        }

        return lenght;
    }

}
