package main.java.exercise;

public class LeafNode extends Node {

    private String key;
    private StudentSolutionImplementation studentSolutionImplementation;

    public LeafNode(String key, StudentSolutionImplementation studentSolutionImplementation) {
        this.key = key;
        this.studentSolutionImplementation = studentSolutionImplementation;
    }

    public boolean isLeaf() {
        return true;
    }

    public char keyCharAt(int i) {
        return this.studentSolutionImplementation.getCharAt(this.key, i);
    }

    public boolean containsKey(Key key) {
        return (new Key(this.key, studentSolutionImplementation)).equals(key);
    }

    public boolean hasSameKey(LeafNode leafNode) {
        return this.key.equals(leafNode.key);
    }
}
