package main.java.exercise;

import main.java.framework.Report;
import main.java.framework.Timer;
import main.java.framework.Verifier;


public class VerifierImplementation extends Verifier<InstanceImplementation, StudentSolutionImplementation, ResultImplementation> {

    @Override
    public ResultImplementation solveProblemUsingStudentSolution(InstanceImplementation instance, StudentSolutionImplementation studentSolution) {
        int[] relevantVertices = instance.getRelevantVertices();
        int[] parents;
        int[][] selectedEdges = new int[relevantVertices.length][2];
        double totalWeight;
        Timer timer = new Timer();
        if (instance.getGroupName().contains("Prim")) {
            parents = new int[instance.getPreparedGraph().numberOfVertices() + 1];
            timer.start();
            totalWeight = studentSolution.prim(instance.getPreparedGraph(), new PriorityQueue(studentSolution), parents);
            timer.stop();
            for (int i = 0; i < selectedEdges.length; i++) {
                int startVertex = relevantVertices[i];
                int endVertex = parents[startVertex];
                selectedEdges[i][0] = startVertex;
                selectedEdges[i][1] = endVertex;
            }
        } else {
            parents = new int[instance.getPreparedGraph().numberOfVertices() + 1];
            boolean[] chosenEdges = new boolean[instance.getPreparedGraph().numberOfEdges()];
            timer.start();
            totalWeight = studentSolution.kruskal(instance.getPreparedGraph(), new UnionFindDataStructure(studentSolution, parents), chosenEdges);
            timer.stop();
            int j = 0;
            int[][] edgesOrderedByWeight = instance.getPreparedGraph().getEdgesOrderedByWeight();
            for (int i = 0; j < selectedEdges.length - 1 && i < chosenEdges.length; i++) {
                if (chosenEdges[i]) {
                    selectedEdges[j][0] = edgesOrderedByWeight[i][0];
                    selectedEdges[j][1] = edgesOrderedByWeight[i][1];
                    j++;
                }
            }
        }

        return new ResultImplementation(timer.getDuration(), relevantVertices, parents, totalWeight, selectedEdges);
    }

    @Override
    public Report verifyResult(InstanceImplementation instance, ResultImplementation result) {
        if (instance.getTotalWeight() != result.getTotalWeight()) {
            return new Report(false, "Error in instance " + instance.getNumber() + "'s result: Total weight expected to be " + instance.getTotalWeight() + " but was " + result.getTotalWeight() + ".");
        }

        int previousParent = 0;
        for (int i = 0; i < instance.getRelevantVertices().length; i++) {
            int parent = findParent(result.getParents(), instance.getRelevantVertices()[i]);
            if (previousParent != 0 && previousParent != parent) {
                return new Report(false, "Error in instance " + instance.getNumber() + "'s result: Not all relevant vertices are connected.");
            }
            previousParent = parent;
        }
        return new Report(true, "");
    }

    private int findParent(int[] parents, int vertexId) {
        while (parents[vertexId] != vertexId) {
            vertexId = parents[vertexId];
        }
        return vertexId;
    }
}
