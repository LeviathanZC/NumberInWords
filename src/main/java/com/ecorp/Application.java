package com.ecorp;

import com.ecorp.controller.Command;
import com.ecorp.model.dao.WordRepository;
import com.ecorp.model.service.NumberInWordConverterService;

public class Application {

    public static void main(String[] args) {
//        Command.getConverter().execute(args[0]);
        System.out.println("=============================================");
        System.out.println(Command.getConverter().execute(""));
        System.out.println("=============================================");
        System.out.println(Command.getConverter().execute("2ytuj43wg35w4"));
        System.out.println("=============================================");
        System.out.println(Command.getConverter().execute("256315"));
        System.out.println("=============================================");
        System.out.println(Command.getConverter().execute("748253639875324135862716264253743724675841725346734582324673752463743"));
        System.out.println("=============================================");
        System.out.println(Command.getConverter().execute("-256315"));
        System.out.println("=============================================");
        System.out.println(Command.getConverter().execute("256315"));
        System.out.println("=============================================");

    }

}
