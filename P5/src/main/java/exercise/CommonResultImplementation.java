package main.java.exercise;

import main.java.framework.PersistAs;
import main.java.framework.Result;

public class CommonResultImplementation implements Result {

    private InstanceType instanceType;

    @PersistAs("chosenNeighbors")
    private String chosenNeighborsString;

    @PersistAs("globalMinimum")
    int globalMinimum;

    @PersistAs("localMinimum")
    int localMinimum;

    public CommonResultImplementation(InstanceType instanceType) {
        this.instanceType = instanceType;
    }

    public CommonResultImplementation(InstanceType instanceType, int[][] chosenNeighbors, int globalMinimum, int localMinimum) {
        this.instanceType = instanceType;
        this.globalMinimum = globalMinimum;
        this.localMinimum = localMinimum;

        String chosenNeighborsString = "";
        for (int i = 0; i < chosenNeighbors.length; i++) {
            if (i != 0) {
                chosenNeighborsString += "+";
            }
            for (int j = 0; j < chosenNeighbors[i].length; j++) {
                if (j != 0) {
                    chosenNeighborsString += "|";
                }
                chosenNeighborsString += chosenNeighbors[i][j];
            }
        }
        this.chosenNeighborsString = chosenNeighborsString;
    }

    public InstanceType getInstanceType() {
        return instanceType;
    }
}
