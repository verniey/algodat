package main.java.exercise;

public class FindMinimumResultImplementation extends CommonResultImplementation {

    private int[][] chosenNeighbors;

    public FindMinimumResultImplementation(int[][] chosenNeighbors, int globalMinimum, int localMinimum) {
        super(InstanceType.FIND_MINIMUM, chosenNeighbors, globalMinimum, localMinimum);
    }

    public int[][] getChosenNeighbors() {
        return chosenNeighbors;
    }
}
