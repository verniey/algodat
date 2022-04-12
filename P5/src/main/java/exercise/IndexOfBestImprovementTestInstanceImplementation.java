package main.java.exercise;

public class IndexOfBestImprovementTestInstanceImplementation extends CommonInstanceImplementation {

    private int numberOfConflictsInState;
    private int[] numberOfConflictsInNeighbors;
    private int expectedIndex;

    public IndexOfBestImprovementTestInstanceImplementation(String groupName, int number, int numberOfConflictsInState, int[] numberOfConflictsInNeighbors, int expectedIndex) {
        super(groupName, number, InstanceType.INDEX_OF_BEST_IMPROVEMENT_TEST);
        this.numberOfConflictsInState = numberOfConflictsInState;
        this.numberOfConflictsInNeighbors = numberOfConflictsInNeighbors;
        this.expectedIndex = expectedIndex;
    }

    public int getNumberOfConflictsInState() {
        return numberOfConflictsInState;
    }

    public int[] getNumberOfConflictsInNeighbors() {
        return numberOfConflictsInNeighbors;
    }

    public int getExpectedIndex() {
        return expectedIndex;
    }
}
