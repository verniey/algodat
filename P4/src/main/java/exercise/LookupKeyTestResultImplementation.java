package main.java.exercise;

public class LookupKeyTestResultImplementation extends CommonResultImplementation {

    private boolean isContained;

    public LookupKeyTestResultImplementation(long duration, boolean isContained) {
        super(InstanceType.LOOKUP_KEY_TEST, duration);
        this.isContained = isContained;
    }

    public boolean isContained() {
        return isContained;
    }
}
