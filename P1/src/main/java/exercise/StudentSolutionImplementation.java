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

    // Implementieren Sie hier Ihre Lösung für die Maximumsuche
    public int findMax(int[] numbers) {
        int max = 0;
        if (numbers == null || numbers.length == 0) {
            return max;
        }

        max = numbers[0];

        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] >= max) {
                max = numbers[i];
            }
        }

        return max;
    }

    // Implementieren Sie hier Ihre Lösung für das dichteste Punktepaar
    public void findClosestPair(Point[] points, ClosestPair closestPair) {
        if (points == null || points.length == 0) return;

        int dist = Integer.MAX_VALUE;


        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {

                int temp = sqrt(points[i].getX() - points[j].getX()) +
                        sqrt(points[i].getY() - points[j].getY());
                if (temp < dist) {
                    dist = temp;
                    closestPair.setPoint1(points[i]);
                    closestPair.setPoint2(points[j]);
                }
            }
        }
    }

    public int sqrt(int a){
        return a*a;
    }

    // Implementieren Sie hier Ihre Lösung für die Teilsummen
    public boolean hasSubsetSum(int sum, int[] numbers) {
        int subSum = 0;
        for (Integer i : numbers) {
            subSum += i;
        }
        return hasSubsetSum(0, subSum, numbers, sum,  0);

    }

    public boolean hasSubsetSum(int weight, int subSum, int[] numbers, int sum, int i) {

        if (weight == sum) {
            return true;
        }
        if (subSum < sum && weight == 0) {return false;}

        if (subSum <= 0 || weight > sum) {
            return false;
        }

        return hasSubsetSum(weight + numbers[i], subSum - numbers[i], numbers, sum, i + 1) || hasSubsetSum(weight, subSum - numbers[i], numbers, sum, i + 1);
    }
}
