package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

public class StudentSolutionImplementation implements StudentSolution {

    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Veronika", // Vorname
                "Zapodobnikova", // Nachname
                "" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für das Auslesen eines Zeichens
    public char getCharAt(String s, int i) {

        return (i < 0 || i > s.length() - 1) ? '{' : s.charAt(i);
    }

    // Implementieren Sie hier Ihre Lösung für das Suchen nach einem Schlüssel
    public boolean lookupKey(InnerNode root, Key key) {

        if (root != null) {
            Node node = root;
            int i = 0;
            char ch = key.charAt(i);
            while ((node = ((InnerNode) node).getChild(ch)) != null) {

                if (node.isLeaf()) {
                    boolean b = ((LeafNode) node).containsKey(key);
                    //System.out.println(b);
                    return b;
                }
                i++;
                ch = key.charAt(i);


            }
        }

        return false;
    }

    // special case
    public void insert(Node node, LeafNode child, LeafNode leafNode, int i) {
        char ch = leafNode.keyCharAt(i);
        if ((child).hasSameKey(leafNode)) {
            return;
        }
        if (child.keyCharAt(i) == leafNode.keyCharAt(i)) {
            insert(((InnerNode)node).attachInnerNode(ch), child, leafNode, i + 1);
        } else {
            ((InnerNode) node).attachLeafNode(child.keyCharAt(i), child);
            ((InnerNode) node).attachLeafNode(leafNode.keyCharAt(i), leafNode);
        }



    }

    public void addWord(LeafNode leafNode, Node node, int i) {


        char ch = leafNode.keyCharAt(i);
        Node tmp = node;
        if (((InnerNode)node).getChild(ch) == null) {
            ((InnerNode)node).attachLeafNode(ch, leafNode);
        }


        if (!((InnerNode)node).getChild(ch).isLeaf()) {
            addWord(leafNode, ((InnerNode)node).getChild(ch), i+1);
        } else {

            Node oldLeaf = ((InnerNode)node).getChild(ch);
            if (((LeafNode) oldLeaf).hasSameKey(leafNode)) {
                return;
            } else {
                insert(node, (LeafNode) oldLeaf, leafNode, i);
            }
        }
    }

    // Implementieren Sie hier Ihre Lösung für das Einfügen eines Schlüssels
    public void insertKey(InnerNode root, LeafNode leafNode) {

        int i = 0;
        char ch = leafNode.keyCharAt(i);
        Node node = root;

        if (root.getChild(ch) == null) {
            root.attachLeafNode(ch, leafNode);
        } else {

            if (root.getChild(ch).isLeaf()) {
                insert(root, (LeafNode) root.getChild(ch), leafNode, i );
            } else {

                addWord(leafNode, root, i);
            }
        }
    }
}
