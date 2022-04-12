package main.java.exercise;

public class Key {

    private String value;
    private StudentSolutionImplementation studentSolutionImplementation;

    public Key(String value, StudentSolutionImplementation studentSolutionImplementation) {
        this.value = value;
        this.studentSolutionImplementation = studentSolutionImplementation;
    }

    public char charAt(int i) {
        return this.studentSolutionImplementation.getCharAt(this.value, i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return value.equals(key.value);
    }
}
