package main.java.exercise;

public class GetNeighborsTestResultImplementation extends CommonResultImplementation {

    private int[][] neighbors;

    public GetNeighborsTestResultImplementation(int[][] neighbors) {
        super(InstanceType.GET_NEIGHBORS_TEST);
        this.neighbors = neighbors;
    }

    public int[][] getNeighbors() {
        return neighbors;
    }
}
