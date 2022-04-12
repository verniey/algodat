package main.java.exercise;

public class GetNeighborsTestInstanceImplementation extends CommonInstanceImplementation {

    private int[] state;

    public GetNeighborsTestInstanceImplementation(String groupName, int number, int[] state) {
        super(groupName, number, InstanceType.GET_NEIGHBORS_TEST);
        this.state = state;
    }

    public int[] getState() {
        return state;
    }
}
