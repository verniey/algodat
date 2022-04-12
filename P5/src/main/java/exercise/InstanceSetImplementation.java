package main.java.exercise;

import main.java.framework.InstanceSet;

import java.io.BufferedReader;
import java.nio.file.Path;


public class InstanceSetImplementation extends InstanceSet<CommonInstanceImplementation, StudentSolutionImplementation, CommonResultImplementation, VerifierImplementation, Object> {

    public InstanceSetImplementation(Path instanceSetPath, Path outputPath) {
        super(instanceSetPath, outputPath, CommonResultImplementation.class);
    }

    @Override
    protected CommonInstanceImplementation instanceFromCsv(String line) {
        String[] splitLine = line.split(",", 5);
        int number = Integer.parseInt(splitLine[0]);
        String groupName = splitLine[1];
        String[] state = splitLine[2].split("\\|");
        if (state.length == 1 && state[0].equals("")) {
            state = new String[0];
        }
        int[] castState = new int[state.length];
        for (int i = 0; i < state.length; i++) {
            castState[i] = Integer.parseInt(state[i]);
        }
        switch (InstanceType.getInstanceTypeByGroupName(groupName)) {
            case GET_NEIGHBORS_TEST:
                return new GetNeighborsTestInstanceImplementation(splitLine[1], number, castState);
            case CALCULATE_NUMBER_OF_CONFLICTS_TEST:
                return new CalculateNumberOfConflictsTestInstanceImplementation(splitLine[1], number, castState, Integer.parseInt(splitLine[3]));
            case INDEX_OF_BEST_IMPROVEMENT_TEST:
                return new IndexOfBestImprovementTestInstanceImplementation(splitLine[1], number, Integer.parseInt(splitLine[3]), castState, Integer.parseInt(splitLine[4]));
            case FIND_MINIMUM:
                return new FindMinimumInstanceImplementation(splitLine[1], number, castState, Integer.parseInt(splitLine[3]));
        }
        return null;
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
