package main.java.exercise;

public class FindMinimumInstanceImplementation extends CommonInstanceImplementation {

    private int[] state;
    private int globalMinimum;

    public FindMinimumInstanceImplementation(String groupName, int number, int[] state, int globalMinimum) {
        super(groupName, number, InstanceType.FIND_MINIMUM);
        this.state = state;
        this.globalMinimum = globalMinimum;
    }

    public int[] getState() {
        return state;
    }

    public int getGlobalMinimum() {
        return globalMinimum;
    }
}
