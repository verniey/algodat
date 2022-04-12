package main.java.exercise;

public enum InstanceType {
    CHAR_AT_TEST,
    LOOKUP_KEY_TEST,
    INSERT_KEY_TEST,
    INSERT_AND_LOOKUP;

    public static InstanceType getInstanceTypeByGroupName(String groupName) {
        if (groupName.equals("Char At Test")) {
            return InstanceType.CHAR_AT_TEST;
        } else if (groupName.equals("Lookup Key Test")) {
            return InstanceType.LOOKUP_KEY_TEST;
        } else if (groupName.equals("Insert Key Test")) {
            return InstanceType.INSERT_KEY_TEST;
        } else if (groupName.equals("Insert and Lookup")) {
            return InstanceType.INSERT_AND_LOOKUP;
        }
        return null;
    }
}
