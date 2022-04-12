package main.java.exercise;

public class CalculateNumberOfConflictsTestResultImplementation extends CommonResultImplementation {

    private int calculatedNumberOfConflicts;

    public CalculateNumberOfConflictsTestResultImplementation(int calculatedNumberOfConflicts) {
        super(InstanceType.CALCULATE_NUMBER_OF_CONFLICTS_TEST);
        this.calculatedNumberOfConflicts = calculatedNumberOfConflicts;
    }

    public int getCalculatedNumberOfConflicts() {
        return calculatedNumberOfConflicts;
    }
}
