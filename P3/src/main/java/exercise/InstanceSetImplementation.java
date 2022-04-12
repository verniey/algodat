package main.java.exercise;

import main.java.framework.InstanceSet;

import java.io.BufferedReader;
import java.nio.file.Path;


public class InstanceSetImplementation extends InstanceSet<InstanceImplementation, StudentSolutionImplementation, ResultImplementation, VerifierImplementation, Object> {

    public InstanceSetImplementation(Path instanceSetPath, Path outputPath) {
        super(instanceSetPath, outputPath, ResultImplementation.class);
    }

    @Override
    protected InstanceImplementation instanceFromCsv(String line) {
        String[] splitLine = line.split(",", 5);
        int sizeOfArray = Integer.parseInt(splitLine[2]);
        String[] inOrderTraversal = splitLine[3].split("\\|");
        String[] otherOrderTraversal = splitLine[4].split("\\|");
        int[] castInOrderTraversal = new int[inOrderTraversal.length];
        for (int i = 0; i < inOrderTraversal.length; i++) {
            int number = Integer.parseInt(inOrderTraversal[i]);
            castInOrderTraversal[i] = number;
        }
        int[] castOtherOrderTraversal = new int[otherOrderTraversal.length];
        for (int i = 0; i < otherOrderTraversal.length; i++) {
            int number = Integer.parseInt(otherOrderTraversal[i]);
            castOtherOrderTraversal[i] = number;
        }
        if (splitLine[1].equals("Pre Order")) {
            return new InstanceImplementation(splitLine[1], Integer.parseInt(splitLine[0]), sizeOfArray, castInOrderTraversal, OrderType.PRE_ORDER, castOtherOrderTraversal);
        } else {
            return new InstanceImplementation(splitLine[1], Integer.parseInt(splitLine[0]), sizeOfArray, castInOrderTraversal, OrderType.POST_ORDER, castOtherOrderTraversal);
        }
    }

    @Override
    protected StudentSolutionImplementation provideStudentSolution() {
        return new StudentSolutionImplementation();
    }

    @Override
    protected VerifierImplementation provideVerifier() {
        return new VerifierImplementation();
    }

    @Override
    protected Object parseAdditionalInput(BufferedReader reader) {
        return null;
    }
}
