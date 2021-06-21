package com.ecorp.model.util;

import java.math.BigInteger;

public class NumberValidator {

    private NumberValidator() {}

    public static boolean isZeroDigit(String number) {
        return number.length() == 0;
    }

    public static boolean isNegative(String number) {
        return number.charAt(0) == '-';
    }

    public static boolean isValid(String number) {
        try {
            new BigInteger(number);
        } catch (NumberFormatException exception) {
            System.out.println("incorrect number : " + exception);
            return false;
        }
        return true;
    }
}
