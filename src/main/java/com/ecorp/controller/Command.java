package com.ecorp.controller;

import com.ecorp.controller.impl.NumberConverterCommandImpl;

public interface Command {

    Command converter = new NumberConverterCommandImpl();

    static Command getConverter() {
        return converter;
    }

    String execute(String number);
}
