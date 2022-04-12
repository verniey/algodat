package main.java.exercise;

public enum InstanceType {
    GET_NEIGHBORS_TEST,
    CALCULATE_NUMBER_OF_CONFLICTS_TEST,
    INDEX_OF_BEST_IMPROVEMENT_TEST,
    FIND_MINIMUM;

    public static InstanceType getInstanceTypeByGroupName(String groupName) {
        if (groupName.equals("Get Neighbors Test")) {
            return InstanceType.GET_NEIGHBORS_TEST;
        } else if (groupName.equals("Calculate Number of Conflicts Test")) {
            return InstanceType.CALCULATE_NUMBER_OF_CONFLICTS_TEST;
        } else if (groupName.equals("Index of Best Improvement Test")) {
            return InstanceType.INDEX_OF_BEST_IMPROVEMENT_TEST;
        } else if (groupName.equals("Find Minimum")) {
            return InstanceType.FIND_MINIMUM;
        }
        return null;
    }
}
