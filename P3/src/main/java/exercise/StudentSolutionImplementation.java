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

    // Implementieren Sie hier Ihre Lösung für Binärbaumrekonstruktion aus Inorder- und Preorder-Durchmusterung
    public  int findRootId(int[] inOrderTraversal, int rootValue) {
        int rootId = 0;
        for (int i = 0; i < inOrderTraversal.length; i++) {
            if (inOrderTraversal[i] == rootValue) {
                rootId = i;

                return rootId;
            }
        }
        return -1;
    }

    public void reconstruct1 (int[] inOrderTraversal, int inStart, int inEnd, int[] preOrderTraversal, int preStart, int preEnd, int[] reconstructedTree, int level) {
        if (!subtreeExists(inStart, inEnd, preStart, preEnd)) {
            return;
        }
        int root = preOrderTraversal[preStart];
        int rootId = findRootId(inOrderTraversal, root);
        reconstructedTree[level] = root;

        // left subtree
        reconstruct1(inOrderTraversal, inStart, rootId - 1, preOrderTraversal, preStart + 1, preStart + rootId - inStart, reconstructedTree, level * 2 + 1);

        // right subtree
        reconstruct1(inOrderTraversal, rootId + 1, inEnd, preOrderTraversal, preStart + rootId - inStart + 1, preEnd, reconstructedTree, level * 2 + 2);
    }

    public boolean subtreeExists(int inStart, int inEnd, int subStart, int subEnd) {
        if (inStart > inEnd || subStart > subStart) {
            return false;
        }
        return true;
    }

    public void reconstructFromInAndPreOrder(int[] inOrderTraversal, int[] preOrderTraversal, int[] reconstructedTree) {

        reconstruct1(inOrderTraversal, 0, inOrderTraversal.length - 1, preOrderTraversal,0, preOrderTraversal.length - 1, reconstructedTree,0);

    }

    // Implementieren Sie hier Ihre Lösung für Binärbaumrekonstruktion aus Inorder- und Postorder-Durchmusterung
    public void reconstruct2 (int[] inOrderTraversal, int inStart, int inEnd, int[] postOrderTraversal, int postStart, int postEnd, int[] reconstructedTree, int level) {
        if (!subtreeExists(inStart, inEnd, postStart, postEnd)) {
            return;
        }

        int root = postOrderTraversal[postEnd];
        int rootId = findRootId(inOrderTraversal, root);
        reconstructedTree[level] = root;

        // left subtree
        reconstruct2(inOrderTraversal, inStart, rootId - 1, postOrderTraversal, postStart, postStart + rootId - (inStart + 1), reconstructedTree, level*2+1);

        // right subtree
        reconstruct2(inOrderTraversal, rootId + 1, inEnd, postOrderTraversal, postStart + rootId - inStart, postEnd - 1, reconstructedTree,level*2+2);

    }

    public void reconstructFromInAndPostOrder(int[] inOrderTraversal, int[] postOrderTraversal, int[] reconstructedTree) {

        reconstruct2(inOrderTraversal, 0, inOrderTraversal.length - 1, postOrderTraversal,0, postOrderTraversal.length - 1, reconstructedTree,0);

    }
}
