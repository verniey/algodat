package main.java.exercise;

public class IndexOfBestImprovementTestResultImplementation extends CommonResultImplementation {

    private int calculatedIndex;

    public IndexOfBestImprovementTestResultImplementation(int calculatedIndex) {
        super(InstanceType.INDEX_OF_BEST_IMPROVEMENT_TEST);
        this.calculatedIndex = calculatedIndex;
    }

    public int getCalculatedIndex() {
        return calculatedIndex;
    }
}
