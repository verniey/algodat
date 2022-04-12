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

    public void calculateMoves(int[] state, int[][] neighbors, int i, int j) {

        if (i < state.length) {
            int pos = state[i];

            if (pos - 1 >= 0) {
                neighbors[j][i] = pos - 1;
                j++;
            }

            if (pos + 1 < state.length) {
                neighbors[j][i] = pos + 1;
                j++;
            }
            calculateMoves(state, neighbors, i + 1, j);
        }

    }

    // Implementieren Sie hier Ihre Lösung für die Berechnung der Nachbarschaft
    public void getNeighbors(int[] state, int[][] neighbors) {


        for (int i = 0; i < neighbors.length; i++) {
            for (int j = 0; j < neighbors[i].length; j++) {
                neighbors[i][j] = state[j];
            }
        }

        calculateMoves(state, neighbors, 0, 0);

    }

    public int conflictsRow(int[] state) {

        int count = 0;

        for (int i = 0; i < state.length-1; i++) {
            for (int j = i; j < state.length - 1; j++) {
                if (state[i] == state[j+1]) {
                    count++;
                }
            }

        }
        return count;
    }

    public int conflictsDiagonal(int[] state) {

        int count = 0;

        for (int i = 0; i < state.length - 1; i++) {

            for (int j = i; j < state.length - 1; j++) {

                int positive1 = i - state[i];
                int positive2 = (j + 1) - state[j+1];

                int negative1 = i + state[i];
                int negative2 = (j + 1) + state[j+1];

                if ( positive1 == positive2) {
                    count++;
                }

                if (negative1 == negative2) {
                    count++;
                }
            }
        }
        return count;
    }

    // Implementieren Sie hier Ihre Lösung für die Berechnung der Konflikte
    public int calculateNumberOfConflicts(int[] state) {

        return conflictsRow(state) + conflictsDiagonal(state);
    }

    // Implementieren Sie hier Ihre Lösung für die Auswahl aus der Nachbarschaft
    public int indexOfBestImprovement(int numberOfConflictsInState, int[] numberOfConflictsInNeighbors) {

        int min = -1;
        for (int i = 0; i < numberOfConflictsInNeighbors.length; i++) {
            if (numberOfConflictsInNeighbors[i] < numberOfConflictsInState) {
                min = i;
            }
        }
        return min;
    }

}
