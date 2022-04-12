package main.java.exercise;

public class InsertKeyTestResultImplementation extends CommonResultImplementation {

    private InnerNode root;
    private StudentSolutionImplementation studentSolutionImplementation;

    public InsertKeyTestResultImplementation(long duration, InnerNode root, StudentSolutionImplementation studentSolutionImplementation) {
        super(InstanceType.INSERT_KEY_TEST, duration);
        this.root = root;
        this.studentSolutionImplementation = studentSolutionImplementation;
    }

    public InnerNode getRoot() {
        return root;
    }

    public StudentSolutionImplementation getStudentSolutionImplementation() {
        return studentSolutionImplementation;
    }
}
