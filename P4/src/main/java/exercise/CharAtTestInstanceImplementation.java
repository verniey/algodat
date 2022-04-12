package main.java.exercise;

import main.java.framework.Instance;

public class CharAtTestInstanceImplementation extends CommonInstanceImplementation {

    private String word;
    private int index;
    private char expectedChar;

    public CharAtTestInstanceImplementation(String groupName, int number, String word, int index, char expectedChar) {
        super(groupName, number, InstanceType.CHAR_AT_TEST);
        this.word = word;
        this.index = index;
        this.expectedChar = expectedChar;
    }

    public String getWord() {
        return word;
    }

    public int getIndex() {
        return index;
    }

    public char getExpectedChar() {
        return expectedChar;
    }
}
