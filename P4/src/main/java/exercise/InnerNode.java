package main.java.exercise;

public class InnerNode extends Node {

    private Node[] children = new Node[27];

    public InnerNode() {

    }

    public boolean isLeaf() {
        return false;
    }

    public Node getChild(char c) {
        if (c >= 'a' && c < 'a' + 27) {
            return children[c - 'a'];
        } else {
            return null;
        }
    }

    public InnerNode attachInnerNode(char c) {
        if (c >= 'a' && c < 'a' + 27) {
            InnerNode innerNode = new InnerNode();
            children[c - 'a'] = innerNode;
            return innerNode;
        }
        return null;
    }

    public void attachLeafNode(char c, LeafNode leafNode) {
        if (c >= 'a' && c < 'a' + 27) {
            children[c - 'a'] = leafNode;
        }
    }
}
