package main.java.exercise;

import main.java.framework.InstanceSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class InstanceSetImplementation extends InstanceSet<CommonInstanceImplementation, StudentSolutionImplementation, CommonResultImplementation, VerifierImplementation, String[]> {

    public InstanceSetImplementation(Path instanceSetPath, Path outputPath) {
        super(instanceSetPath, outputPath, CommonResultImplementation.class);
    }

    @Override
    protected CommonInstanceImplementation instanceFromCsv(String line) {
        String[] splitLine = line.split(",");
        int number = Integer.parseInt(splitLine[0]);
        String groupName = splitLine[1];
        switch (InstanceType.getInstanceTypeByGroupName(groupName)) {
            case CHAR_AT_TEST:
                return new CharAtTestInstanceImplementation(splitLine[1], number, splitLine[2], Integer.parseInt(splitLine[3]), splitLine[4].charAt(0));
            case LOOKUP_KEY_TEST:
                return new LookupKeyTestInstanceImplementation(splitLine[1], number, splitLine[2], Boolean.parseBoolean(splitLine[3]));
            case INSERT_KEY_TEST:
                return new InsertKeyTestInstanceImplementation(splitLine[1], number, line.split(",", 3)[2].split(","));
            case INSERT_AND_LOOKUP:
                String keyGroup = splitLine[2];
                String[] keys = this.getAdditionalInput("words-" + keyGroup + ".txt");
                return new InsertAndLookupInstanceImplementation(splitLine[1], number, keys, Integer.parseInt(splitLine[3]), keyGroup);
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
    protected String[] parseAdditionalInput(BufferedReader reader) {
        List<String> keys = new ArrayList<String>();
        try {
            String key;
            while ((key = reader.readLine()) != null) {
                keys.add(key);
            }
        } catch (IOException e) {
            return new String[0];
        }
        return keys.toArray(new String[keys.size()]);
    }
}
