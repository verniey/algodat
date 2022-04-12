package main.java.exercise;

import main.java.framework.PersistAs;
import main.java.framework.Result;

public class ResultImplementation implements Result {

    @PersistAs("duration")
    private long duration;

    @PersistAs("treeSize")
    private long treeSize;

    private int[] reconstructedTree;

    @PersistAs("reconstructedTree")
    private String reconstructedTreeString;

    public ResultImplementation(long duration, int treeSize, int[] reconstructedTree) {
        this.duration = duration;
        this.treeSize = treeSize;
        this.reconstructedTree = reconstructedTree;

        String reconstructedTreeString = "";
        for (int i = 0; i < reconstructedTree.length; i++) {
            if (i != 0) {
                reconstructedTreeString += "|";
            }
            reconstructedTreeString += reconstructedTree[i];
        }
        this.reconstructedTreeString = reconstructedTreeString;
    }

    public int[] getReconstructedTree() {
        return reconstructedTree;
    }
}
