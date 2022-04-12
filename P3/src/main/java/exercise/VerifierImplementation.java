package main.java.exercise;

import main.java.framework.Report;
import main.java.framework.Timer;
import main.java.framework.Verifier;

public class VerifierImplementation extends Verifier<InstanceImplementation, StudentSolutionImplementation, ResultImplementation> {

    @Override
    public ResultImplementation solveProblemUsingStudentSolution(InstanceImplementation instance, StudentSolutionImplementation studentSolution) {
        int[] reconstructedTree = new int[instance.getSizeOfArray()];
        Timer timer = new Timer();
        switch (instance.getOrderType()) {
            case PRE_ORDER:
                timer.start();
                studentSolution.reconstructFromInAndPreOrder(
                        instance.getInOrderTraversal(),
                        instance.getOtherOrderTraversal(),
                        reconstructedTree);
                timer.stop();
                break;
            default:
                timer.start();
                studentSolution.reconstructFromInAndPostOrder(
                        instance.getInOrderTraversal(),
                        instance.getOtherOrderTraversal(),
                        reconstructedTree);
                timer.stop();
                break;
        }
        return new ResultImplementation(timer.getDuration(), instance.getInOrderTraversal().length, reconstructedTree);
    }

    @Override
    public Report verifyResult(InstanceImplementation instance, ResultImplementation result) {
        int[] inOrderTraversal = instance.getInOrderTraversal();
        int[] otherOrderTraversal = instance.getOtherOrderTraversal();
        int[] reconstructedInOrderTraversal = new int[inOrderTraversal.length];
        int[] reconstructedOtherOrderTraversal = new int[otherOrderTraversal.length];
        getInOrderTraversal(
                result.getReconstructedTree(),
                reconstructedInOrderTraversal,
                0,
                0
        );
        switch (instance.getOrderType()) {
            case PRE_ORDER:
                getPreOrderTraversal(
                        result.getReconstructedTree(),
                        reconstructedOtherOrderTraversal,
                        0,
                        0
                );
                break;
            default:
                getPostOrderTraversal(
                        result.getReconstructedTree(),
                        reconstructedOtherOrderTraversal,
                        0,
                        0
                );
                break;
        }
        for (int i = 0; i < instance.getInOrderTraversal().length; i++) {
            if (inOrderTraversal[i] != reconstructedInOrderTraversal[i]
                    || otherOrderTraversal[i] != reconstructedOtherOrderTraversal[i]) {
                return new Report(false, "Error in instance " + instance.getNumber() + ": Reconstructed tree does not match given traversals.");
            }
        }
        return new Report(true, "");
    }

    private int getInOrderTraversal(int[] reconstructedTree, int[] traversal, int currentTreeIndex, int currentTraversalIndex) {
        if (currentTraversalIndex >= traversal.length) {
            return currentTraversalIndex;
        }
        int leftChildIndex = leftChildIndex(currentTreeIndex);
        int rightChildIndex = rightChildIndex(currentTreeIndex);
        if (leftChildIndex < reconstructedTree.length) {
            if (reconstructedTree[leftChildIndex] != 0) {
                currentTraversalIndex = getInOrderTraversal(reconstructedTree, traversal, leftChildIndex, currentTraversalIndex);
            }
        }
        traversal[currentTraversalIndex++] = reconstructedTree[currentTreeIndex];
        if (rightChildIndex < reconstructedTree.length) {
            if (reconstructedTree[rightChildIndex] != 0) {
                currentTraversalIndex = getInOrderTraversal(reconstructedTree, traversal, rightChildIndex, currentTraversalIndex);
            }
        }
        return currentTraversalIndex;
    }

    private int getPreOrderTraversal(int[] reconstructedTree, int[] traversal, int currentTreeIndex, int currentTraversalIndex) {
        if (currentTraversalIndex >= traversal.length) {
            return currentTraversalIndex;
        }
        int leftChildIndex = leftChildIndex(currentTreeIndex);
        int rightChildIndex = rightChildIndex(currentTreeIndex);
        traversal[currentTraversalIndex++] = reconstructedTree[currentTreeIndex];
        if (leftChildIndex < reconstructedTree.length) {
            if (reconstructedTree[leftChildIndex] != 0) {
                currentTraversalIndex = getPreOrderTraversal(reconstructedTree, traversal, leftChildIndex, currentTraversalIndex);
            }
        }
        if (rightChildIndex < reconstructedTree.length) {
            if (reconstructedTree[rightChildIndex] != 0) {
                currentTraversalIndex = getPreOrderTraversal(reconstructedTree, traversal, rightChildIndex, currentTraversalIndex);
            }
        }
        return currentTraversalIndex;
    }

    private int getPostOrderTraversal(int[] reconstructedTree, int[] traversal, int currentTreeIndex, int currentTraversalIndex) {
        if (currentTraversalIndex >= traversal.length) {
            return currentTraversalIndex;
        }
        int leftChildIndex = leftChildIndex(currentTreeIndex);
        int rightChildIndex = rightChildIndex(currentTreeIndex);
        if (leftChildIndex < reconstructedTree.length) {
            if (reconstructedTree[leftChildIndex] != 0) {
                currentTraversalIndex = getPostOrderTraversal(reconstructedTree, traversal, leftChildIndex, currentTraversalIndex);
            }
        }
        if (rightChildIndex < reconstructedTree.length) {
            if (reconstructedTree[rightChildIndex] != 0) {
                currentTraversalIndex = getPostOrderTraversal(reconstructedTree, traversal, rightChildIndex, currentTraversalIndex);
            }
        }
        traversal[currentTraversalIndex++] = reconstructedTree[currentTreeIndex];
        return currentTraversalIndex;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }
}
