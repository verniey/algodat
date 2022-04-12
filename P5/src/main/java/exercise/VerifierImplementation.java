package main.java.exercise;

import main.java.framework.Report;
import main.java.framework.Verifier;

import java.util.ArrayList;
import java.util.List;

public class VerifierImplementation extends Verifier<CommonInstanceImplementation, StudentSolutionImplementation, CommonResultImplementation> {

    @Override
    public CommonResultImplementation solveProblemUsingStudentSolution(CommonInstanceImplementation instance, StudentSolutionImplementation studentSolution) {
        InstanceType instanceType = instance.getInstanceType();
        switch (instanceType) {
            case GET_NEIGHBORS_TEST:
                GetNeighborsTestInstanceImplementation getNeighborsTestInstanceImplementation = (GetNeighborsTestInstanceImplementation) instance;
                int[] state = getNeighborsTestInstanceImplementation.getState();
                int numberOfNeighbors = 0;
                for (int i = 0; i < state.length; i++) {
                    if (state[i] > 0 && state[i] < state.length - 1) {
                        numberOfNeighbors += 2;
                    } else if (state[i] == 0 && state[i] < state.length - 1 || state[i] > 0 && state[i] == state.length - 1) {
                        numberOfNeighbors++;
                    }
                }
                int[][] neighbors = new int[numberOfNeighbors][state.length];
                studentSolution.getNeighbors(state, neighbors);
                return new GetNeighborsTestResultImplementation(neighbors);
            case CALCULATE_NUMBER_OF_CONFLICTS_TEST:
                CalculateNumberOfConflictsTestInstanceImplementation calculateNumberOfConflictsTestInstanceImplementation = (CalculateNumberOfConflictsTestInstanceImplementation) instance;
                return new CalculateNumberOfConflictsTestResultImplementation(studentSolution.calculateNumberOfConflicts(calculateNumberOfConflictsTestInstanceImplementation.getState()));
            case INDEX_OF_BEST_IMPROVEMENT_TEST:
                IndexOfBestImprovementTestInstanceImplementation indexOfBestImprovementTestInstanceImplementation = (IndexOfBestImprovementTestInstanceImplementation) instance;
                return new IndexOfBestImprovementTestResultImplementation(studentSolution.indexOfBestImprovement(indexOfBestImprovementTestInstanceImplementation.getNumberOfConflictsInState(), indexOfBestImprovementTestInstanceImplementation.getNumberOfConflictsInNeighbors()));
            case FIND_MINIMUM:
                FindMinimumInstanceImplementation findMinimumInstanceImplementation = (FindMinimumInstanceImplementation) instance;
                int[] currentState = findMinimumInstanceImplementation.getState();
                int indexOfCurrentBestImprovement = -1;
                int localMinimum = Integer.MAX_VALUE;
                List<int[]> chosenNeighbors = new ArrayList<int[]>();
                while (currentState != null) {
                    chosenNeighbors.add(currentState);
                    int numberOfCurrentNeighbors = 0;
                    for (int i = 0; i < currentState.length; i++) {
                        if (currentState[i] > 0 && currentState[i] < currentState.length - 1) {
                            numberOfCurrentNeighbors += 2;
                        } else if (currentState[i] == 0 && currentState[i] < currentState.length - 1 || currentState[i] > 0 && currentState[i] == currentState.length - 1) {
                            numberOfCurrentNeighbors++;
                        }
                    }
                    int[][] currentNeighbors = new int[numberOfCurrentNeighbors][currentState.length];
                    studentSolution.getNeighbors(currentState, currentNeighbors);
                    int numberOfConflictsInCurrentState = localMinimum = studentSolution.calculateNumberOfConflicts(currentState);
                    int[] numberOfConflictsInNeighbors = new int[numberOfCurrentNeighbors];
                    for (int i = 0; i < currentNeighbors.length; i++) {
                        // think about copying neighbors
                        numberOfConflictsInNeighbors[i] = studentSolution.calculateNumberOfConflicts(currentNeighbors[i]);
                    }
                    indexOfCurrentBestImprovement = studentSolution.indexOfBestImprovement(numberOfConflictsInCurrentState, numberOfConflictsInNeighbors);
                    if (indexOfCurrentBestImprovement > -1) {
                        currentState = currentNeighbors[indexOfCurrentBestImprovement];
                    } else {
                        currentState = null;
                    }
                }
                return new FindMinimumResultImplementation(chosenNeighbors.toArray(new int[chosenNeighbors.size()][]), findMinimumInstanceImplementation.getGlobalMinimum(), localMinimum);
        }
        return null;
    }

    @Override
    public Report verifyResult(CommonInstanceImplementation instance, CommonResultImplementation result) {
        InstanceType instanceType = instance.getInstanceType();
        switch (instanceType) {
            case GET_NEIGHBORS_TEST:
                GetNeighborsTestInstanceImplementation getNeighborsTestInstanceImplementation = (GetNeighborsTestInstanceImplementation) instance;
                GetNeighborsTestResultImplementation getNeighborsTestResultImplementation = (GetNeighborsTestResultImplementation) result;
                int[] state = getNeighborsTestInstanceImplementation.getState();
                int[][] neighbors = getNeighborsTestResultImplementation.getNeighbors();
                int[] changeDirectionForPosition = new int[state.length];
                int[] numberOfRequiredChangesOverAllNeighbors = new int[state.length];
                for (int i = 0; i < state.length; i++) {
                    if (state[i] > 0 && state[i] < state.length - 1) {
                        numberOfRequiredChangesOverAllNeighbors[i] = 2;
                    } else if (state[i] == 0 && state[i] < state.length - 1 || state[i] > 0 && state[i] == state.length - 1) {
                        numberOfRequiredChangesOverAllNeighbors[i] = 1;
                    }
                }
                for (int i = 0; i < neighbors.length; i++) {
                    int numberOfChanges = 0;
                    if (neighbors[i].length != state.length) {
                        return new Report(false, "Error in instance " + instance.getNumber() + ": The size of neighbor " + stateToString(neighbors[i]) + " does not match up with original state " + stateToString(state) + ".");
                    }
                    for (int j = 0; j < neighbors[i].length; j++) {
                        if (neighbors[i][j] < 0 || neighbors[i][j] >= state.length) {
                            return new Report(false, "Error in instance " + instance.getNumber() + ": The neighbor " + stateToString(neighbors[i]) + " contains an illegal position. All positions must be in [0, " + (state.length - 1) + "].");
                        }
                        int difference = neighbors[i][j] - state[j];
                        int absoluteDifference = Math.abs(difference);
                        if (absoluteDifference > 1) {
                            return new Report(false, "Error in instance " + instance.getNumber() + ": A position in neighbor " + stateToString(neighbors[i]) + " differs too much from the corresponding position in the original state " + stateToString(state) + ".");
                        } else if (absoluteDifference == 1) {
                            numberOfChanges++;
                            numberOfRequiredChangesOverAllNeighbors[j]--;
                        }
                        if (difference < 0) {
                            if (changeDirectionForPosition[j] < 0) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": Two neighbors " + stateToString(neighbors[i]) + " of original state " + stateToString(state) + " are equal.");
                            }
                            changeDirectionForPosition[j] = -1;
                        } else if (difference > 0) {
                            if (changeDirectionForPosition[j] > 0) {
                                return new Report(false, "Error in instance " + instance.getNumber() + ": Two neighbors " + stateToString(neighbors[i]) + " of original state " + stateToString(state) + " are equal.");
                            }
                            changeDirectionForPosition[j] = 1;
                        }
                    }
                    if (numberOfChanges == 0) {
                        return new Report(false, "Error in instance " + instance.getNumber() + ": The neighbor " + stateToString(neighbors[i]) + " contains no difference to the original state " + stateToString(state) + ".");
                    } else if (numberOfChanges > 1) {
                        return new Report(false, "Error in instance " + instance.getNumber() + ": The neighbor " + stateToString(neighbors[i]) + " contains more than one change to the original state " + stateToString(state) + ".");
                    }
                }
                for (int i = 0; i < numberOfRequiredChangesOverAllNeighbors.length; i++) {
                    if (numberOfRequiredChangesOverAllNeighbors[i] > 0) {
                        return new Report(false, "Error in instance " + instance.getNumber() + ": Not all neighbors of original state " + stateToString(state) + " have been found.");
                    }
                }
                return new Report(true, "");
            case CALCULATE_NUMBER_OF_CONFLICTS_TEST:
                CalculateNumberOfConflictsTestInstanceImplementation calculateNumberOfConflictsTestInstanceImplementation = (CalculateNumberOfConflictsTestInstanceImplementation) instance;
                CalculateNumberOfConflictsTestResultImplementation calculateNumberOfConflictsTestResultImplementation = (CalculateNumberOfConflictsTestResultImplementation) result;
                int expectedNumberOfConflicts = calculateNumberOfConflictsTestInstanceImplementation.getExpectedNumberOfConflicts();
                int calculatedNumberOfConflicts = calculateNumberOfConflictsTestResultImplementation.getCalculatedNumberOfConflicts();
                if (expectedNumberOfConflicts != calculatedNumberOfConflicts) {
                    return new Report(false, "Error in instance " + instance.getNumber() + ": Number of conflicts calculated for state " + stateToString(calculateNumberOfConflictsTestInstanceImplementation.getState()) + " is " + calculatedNumberOfConflicts + " but " + expectedNumberOfConflicts + " was expected.");
                } else {
                    return new Report(true, "");
                }
            case INDEX_OF_BEST_IMPROVEMENT_TEST:
                IndexOfBestImprovementTestInstanceImplementation indexOfBestImprovementTestInstanceImplementation = (IndexOfBestImprovementTestInstanceImplementation) instance;
                IndexOfBestImprovementTestResultImplementation indexOfBestImprovementTestResultImplementation = (IndexOfBestImprovementTestResultImplementation) result;
                int expectedIndex = indexOfBestImprovementTestInstanceImplementation.getExpectedIndex();
                int calculatedIndex = indexOfBestImprovementTestResultImplementation.getCalculatedIndex();
                if (expectedIndex != calculatedIndex) {
                    int numberOfConflictsInState = indexOfBestImprovementTestInstanceImplementation.getNumberOfConflictsInState();
                    return new Report(false, "Error in instance " + instance.getNumber() + ": Given the current state has " + indexOfBestImprovementTestInstanceImplementation.getNumberOfConflictsInState() + " " + (numberOfConflictsInState == 1 ? "Conflict" : "Conflicts") + " and the number of Conflicts in the neighborhood are represented by the array " + stateToString(indexOfBestImprovementTestInstanceImplementation.getNumberOfConflictsInNeighbors()) + ", " + expectedIndex + " was expected to be the index of the chosen neighbor. However, " + calculatedIndex + " was calculated.");
                } else {
                    return new Report(true, "");
                }
            case FIND_MINIMUM:
                FindMinimumInstanceImplementation findMinimumInstanceImplementation = (FindMinimumInstanceImplementation) instance;
                FindMinimumResultImplementation findMinimumResultImplementation = (FindMinimumResultImplementation) result;
                return new Report(true, "");
        }
        return null;
    }

    private String stateToString(int[] neighbor) {
        String string = "[";
        for (int i = 0; i < neighbor.length; i++) {
            if (i != 0) {
                string += ", ";
            }
            string += neighbor[i];
        }
        string += "]";
        return string;
    }
}
