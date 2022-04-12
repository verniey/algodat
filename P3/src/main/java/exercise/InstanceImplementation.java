package main.java.exercise;

import main.java.framework.Instance;

public class InstanceImplementation implements Instance {

    private String groupName;

    private int number;

    private int sizeOfArray;

    private int[] inOrderTraversal;

    private OrderType orderType;

    private int[] otherOrderTraversal;

    public InstanceImplementation(String groupName, int number, int sizeOfArray, int[] inOrderTraversal, OrderType orderType, int[] otherOrderTraversal) {
        this.groupName = groupName;
        this.number = number;
        this.sizeOfArray = sizeOfArray;
        this.inOrderTraversal = inOrderTraversal;
        this.orderType = orderType;
        this.otherOrderTraversal = otherOrderTraversal;
    }

    @Override
    public String getGroupName() {
        return this.groupName;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    public int getSizeOfArray() {
        return sizeOfArray;
    }

    public int[] getInOrderTraversal() {
        return inOrderTraversal;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public int[] getOtherOrderTraversal() {
        return otherOrderTraversal;
    }
}
