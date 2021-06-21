package com.ecorp.controller.impl;

import com.ecorp.controller.Command;
import com.ecorp.model.service.NumberInWordConverterService;
import com.ecorp.model.util.NumberValidator;

public class NumberConverterCommandImpl implements Command {

    @Override
    public String execute(String number) {
        if (NumberValidator.isZeroDigit(number)) {
            return "number length is less 1";
        }
        if (NumberValidator.isValid(number)) {
            NumberInWordConverterService service = NumberInWordConverterService.getService();
            if (NumberValidator.isNegative(number)) {
                String numberWithoutMinus = number.substring(1);
                return "минус " + service.convert(numberWithoutMinus);
            }
            return service.convert(number);
        } else {
            return "number isn't valid : " + number;
        }
    }

}
