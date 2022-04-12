package main.java.exercise;

import main.java.framework.PersistAs;
import main.java.framework.Result;

public class CommonResultImplementation implements Result {

    private InstanceType instanceType;

    @PersistAs("duration")
    private long duration;

    @PersistAs("numberOfKeys")
    private long numberOfKeys;

    @PersistAs("keyGroup")
    private String keyGroup;

    @PersistAs("averageDurationLookupTrie")
    private long averageDurationLookupTrie;

    @PersistAs("averageDurationLookupTree")
    private long averageDurationLookupTree;

    @PersistAs("averageDurationLookupList")
    private long averageDurationLookupList;

    public CommonResultImplementation(InstanceType instanceType, long duration) {
        this.instanceType = instanceType;
        this.duration = duration;
    }

    public CommonResultImplementation(InstanceType instanceType, long duration, int numberOfKeys, String keyGroup, long averageDurationLookupTrie, long averageDurationLookupTree, long averageDurationLookupList) {
        this(instanceType, duration);
        this.numberOfKeys = numberOfKeys;
        this.keyGroup = keyGroup;
        this.averageDurationLookupTrie = averageDurationLookupTrie;
        this.averageDurationLookupTree = averageDurationLookupTree;
        this.averageDurationLookupList = averageDurationLookupList;
    }

    public InstanceType getInstanceType() {
        return instanceType;
    }
}
