package com.ecorp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParametrizeTest {

    private String valueNumber;
    private String expected;

    public ParametrizeTest(String number, String expected) {
        this.valueNumber = number;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\" словами -> \"{1}\"")
    public static Iterable<Object[]> dataForTest() {
        List<String> csv = readFileData();
        return Arrays.asList(splitAndNormalize(csv));
    }

    private static String[][] splitAndNormalize(List<String> csv) {
        String[][] data = new String[csv.size()][2];
        String[] splitted;
        for (int i = 0; i < csv.size(); i++) {
            splitted = csv.get(i).split(", ");
            splitted[1] = splitted[1].substring(1, splitted[1].length() - 1);
            data[i] = splitted;
        }
        return data;
    }

    private static List<String> readFileData() {
        Path dataFile = Paths.get("src", "test", "resources", "data.csv");
        List<String> csv = null;
        try {
            csv = Files.readAllLines(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csv;
    }

    @Test
    public void paramTest(){
        assertEquals(expected, Command.getConverter().execute(valueNumber));
    }

}
