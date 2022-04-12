package main.java.exercise;

public class InsertKeyTestInstanceImplementation extends CommonInstanceImplementation {

    private String[] keys;

    public InsertKeyTestInstanceImplementation(String groupName, int number, String[] keys) {
        super(groupName, number, InstanceType.INSERT_KEY_TEST);
        this.keys = keys;
    }

    public String[] getKeys() {
        return keys;
    }
}
