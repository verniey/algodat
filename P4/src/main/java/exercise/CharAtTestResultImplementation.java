package main.java.exercise;

public class CharAtTestResultImplementation extends CommonResultImplementation {

    private char result;

    public CharAtTestResultImplementation(long duration, char result) {
        super(InstanceType.CHAR_AT_TEST, duration);
        this.result = result;
    }

    public char getResult() {
        return result;
    }
}
