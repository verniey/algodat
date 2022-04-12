package main.java.exercise;

import main.java.framework.PersistAs;

public class InsertAndLookupResultImplementation extends CommonResultImplementation {

    private String lastKeyNotFound;

    public InsertAndLookupResultImplementation(long duration, int numberOfKeys, String keyGroup, long averageDurationLookupTrie, long averageDurationLookupTree, long averageDurationLookupList, String lastKeyNotFound) {
        super(InstanceType.INSERT_AND_LOOKUP, duration, numberOfKeys, keyGroup, averageDurationLookupTrie, averageDurationLookupTree, averageDurationLookupList);
        this.lastKeyNotFound = lastKeyNotFound;
    }

    public String getLastKeyNotFound() {
        return lastKeyNotFound;
    }

}
