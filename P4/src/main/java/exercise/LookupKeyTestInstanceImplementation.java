package main.java.exercise;

public class LookupKeyTestInstanceImplementation extends CommonInstanceImplementation {

    private String key;
    private boolean expectedToBeContained;

    public LookupKeyTestInstanceImplementation(String groupName, int number, String key, boolean expectedToBeContained) {
        super(groupName, number, InstanceType.LOOKUP_KEY_TEST);
        this.key = key;
        this.expectedToBeContained = expectedToBeContained;
    }

    public String getKey() {
        return key;
    }

    public boolean isExpectedToBeContained() {
        return expectedToBeContained;
    }
}
