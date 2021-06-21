package com.ecorp.model.dao;

import java.util.Arrays;
import java.util.ResourceBundle;

public class WordRepository {

    private static WordRepository repository;

    private WordRepository() {
    }

    public static WordRepository getRepository() {
        if (repository == null) {
            repository = new WordRepository();
            configure();
        }
        return repository;
    }

    private static ResourceBundle resources;

    private static final String PATH = "property/words";

    private static void configure() {
        resources = ResourceBundle.getBundle(PATH);
        POWER_COUNT = split(resources.getString(WordProperties.POWERS)).length + CONST_NAMES_QUANTITY;
        powerMatrix = new String[POWER_COUNT][COLUMNS];
        initPowerMatrix();
        initWordMatrix();
    }


    private static int POWER_COUNT;

    private static final int COLUMNS = 4;

    private static final int CONST_NAMES_QUANTITY = 4;

    private static String[][] powerMatrix;

    private static String[][] wordMatrix;

    public String getPower(int index1, int index2) {
        return powerMatrix[index1][index2];
    }

    public String getWord(int index, int type) {
        return wordMatrix[index][type];
    }

    public String getFrom10To20(int index) {
        return wordMatrix[index][2];
    }

    public String getDecimal(int index) {
        return wordMatrix[index][3];
    }

    public String getHundred(int index) {
        return wordMatrix[index][4];
    }

    private static char WHITESPACE = ' ';

    private static void initPowerMatrix() {
        String[] temp = null;
        String[] postfix = split(resources.getString(WordProperties.POSTFIX));
        for (int j = 0; j < POWER_COUNT; j++) {
            if (j < CONST_NAMES_QUANTITY) {
                switch (j) {
                    case 0: {
                        temp = new String[]{"0", "", "", ""};
                        break;
                    }
                    case 1: {
                        temp = split(resources.getString(WordProperties.THOUSAND));
                        break;
                    }
                    case 2: {
                        temp = split(resources.getString(WordProperties.MILLION));
                        break;
                    }
                    case 3: {
                        temp = split(resources.getString(WordProperties.BILLION));
                        break;
                    }
                }
                if (j > 0) {
                    for (int i = 1; i < temp.length; i++) {
                        temp[i] += " ";
                    }
                }
            } else {
                String power = split(resources.getString(WordProperties.POWERS))[j - CONST_NAMES_QUANTITY];
                temp = new String[COLUMNS];
                for (int i = 0; i < COLUMNS; i++) {
                    if (i == 0) {
                        temp[i] = "0";
                    } else {
                        temp[i] = power + postfix[i - 1] + WHITESPACE;
                    }
                }
            }
            fillRow(temp, j);
        }
    }

    private static void initWordMatrix() {
        wordMatrix = new String[][]{
                {"", "", "десять ", "", ""},
                {"один ", "одна ", "одиннадцать ", "десять ", "сто "},
                {"два ", "две ", "двенадцать ", "двадцать ", "двести "},
                {"три ", "три ", "тринадцать ", "тридцать ", "триста "},
                {"четыре ", "четыре ", "четырнадцать ", "сорок ", "четыреста "},
                {"пять ", "пять ", "пятнадцать ", "пятьдесят ", "пятьсот "},
                {"шесть ", "шесть ", "шестнадцать ", "шестьдесят ", "шестьсот "},
                {"семь ", "семь ", "семнадцать ", "семьдесят ", "семьсот "},
                {"восемь ", "восемь ", "восемнадцать ", "восемьдесят ", "восемьсот "},
                {"девять ", "девять ", "девятнадцать ", "девяносто ", "девятьсот "}
        };
    }

    private static String[] split(String str) {
        return str.split(", ");
    }

    private static void fillRow(String[] data, int row) {
        System.arraycopy(data, 0, powerMatrix[row], 0, COLUMNS);
    }
}
