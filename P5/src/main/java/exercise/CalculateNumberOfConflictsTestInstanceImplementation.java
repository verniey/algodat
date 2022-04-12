package main.java.exercise;

public class CalculateNumberOfConflictsTestInstanceImplementation extends CommonInstanceImplementation {

    private int[] state;

    private int expectedNumberOfConflicts;

    public CalculateNumberOfConflictsTestInstanceImplementation(String groupName, int number, int[] state, int expectedNumberOfConflicts) {
        super(groupName, number, InstanceType.CALCULATE_NUMBER_OF_CONFLICTS_TEST);
        this.state = state;
        this.expectedNumberOfConflicts = expectedNumberOfConflicts;
    }

    public int[] getState() {
        return state;
    }

    public int getExpectedNumberOfConflicts() {
        return expectedNumberOfConflicts;
    }
}
