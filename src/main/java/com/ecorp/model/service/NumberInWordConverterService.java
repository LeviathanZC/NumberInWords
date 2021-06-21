package com.ecorp.model.service;

import com.ecorp.model.dao.WordRepository;
import com.ecorp.model.exception.ServiceException;

import java.math.BigInteger;

public class NumberInWordConverterService {

    public static final int ONE = 1;

    public static final int TWO_TO_FOUR = 2;

    public static final int MANY = 3;

    public static final int MAX_POWER = 33;

    private static NumberInWordConverterService service;

    private NumberInWordConverterService() {
        repository = WordRepository.getRepository();
    }

    public static NumberInWordConverterService getService() {
        if (service == null) {
            service = new NumberInWordConverterService();
        }
        return service;
    }

    private WordRepository repository;

    private static final int ZERO = 0;

    private static final int HUNDRED = 100;

    private static final int TWENTY = 20;

    private static final int TEN = 10;

    public String convert(String number) throws ServiceException {
        int numberLength = number.length();
        StringBuilder builder = new StringBuilder();
        BigInteger bigNumber = new BigInteger(number);

        int numOfTriads = calcTriadsCount(numberLength);
        if (numOfTriads > MAX_POWER) {
            throw new ServiceException("Very Big Number - length:" + numberLength +
                    " triads : " + numOfTriads + " - more than maximum " + MAX_POWER);
        }

        BigInteger divisor = new BigInteger("1");
        BigInteger thousand = new BigInteger("1000");
        for (int i = 0; i < numOfTriads; i++) {
            divisor = divisor.multiply(thousand);
        }

        int triad;
        for (int i = numOfTriads - 1; i >= 0; i--) {
            divisor = divisor.divide(thousand);
            triad = bigNumber.divide(divisor).intValue();
            bigNumber = bigNumber.mod(divisor);

            if (triad == 0) {
                if (i > 0) continue;
                builder.append(repository.getPower(i, ONE));
            } else {

                if (triad >= HUNDRED) {
                    builder.append(repository.getHundred(triad / HUNDRED));
                    triad %= 100;
                }

                if (triad >= TWENTY) {
                    builder.append(repository.getDecimal(triad / 10));
                    triad %= 10;
                }

                if (triad >= TEN) {
                    builder.append(repository.getFrom10To20(triad - 10));
                } else {
                    if (triad >= ONE) {
                        builder.append(repository.getWord(triad, "0".equals(repository.getPower(i, ZERO)) ? 0 : 1));
                    }
                }

                switch (triad) {
                    case 1: {
                        builder.append(repository.getPower(i, ONE));
                        break;
                    }
                    case 2:
                    case 3:
                    case 4: {
                        builder.append(repository.getPower(i, TWO_TO_FOUR));
                        break;
                    }
                    default: {
                        builder.append(repository.getPower(i, MANY));
                        break;
                    }
                }
            }
        }
        normalize(builder);
        return builder.toString();
    }

    private void normalize(StringBuilder builder) {
        builder.deleteCharAt(builder.length() - 1);
    }

    private static final int TRIAD_LENGTH = 3;

    private int calcTriadsCount(int numberLength) {
        if (numberLength % TRIAD_LENGTH == 0) {
            return numberLength / TRIAD_LENGTH;
        } else {
            return numberLength / TRIAD_LENGTH + 1;
        }
    }
}
