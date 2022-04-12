package main.java.exercise;

public class InsertAndLookupInstanceImplementation extends CommonInstanceImplementation {

    private String[] keys;
    private int numberOfKeys;
    private String keyGroup;

    public InsertAndLookupInstanceImplementation(String groupName, int number, String[] keys, int numberOfKeys, String keyGroup) {
        super(groupName, number, InstanceType.INSERT_AND_LOOKUP);
        this.keys = keys;
        this.numberOfKeys = numberOfKeys;
        this.keyGroup = keyGroup;
    }

    public String[] getKeys() {
        return keys;
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public String getKeyGroup() {
        return keyGroup;
    }
}
