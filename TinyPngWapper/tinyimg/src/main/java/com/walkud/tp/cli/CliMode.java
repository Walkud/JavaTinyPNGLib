package com.walkud.tp.cli;

import com.walkud.tp.Configs;
import com.walkud.tp.utils.MLog;

import java.io.File;
import java.util.List;

/**
 * 命令行模式
 * Created by Walkud on 16/5/4.
 */
public class CliMode {

    /**
     * 参数信息
     */
    public static final String ARGUMENTS_MESSAGE = "-key xxx TinyPng apiKey\n";


    private CliMode() {
    }

    public static void with(List<String> args) {
        if (args.size() < 2) {
            System.err.println("wrong command line arguments " + "\n\n\n arguments list\n\n" + ARGUMENTS_MESSAGE);
            return;
        }

        final String operand = args.get(0).toLowerCase();
        switch (operand) {
            case "-key":
                break;
            default:
                MLog.p("wrong arguments ==> " + operand);
        }
    }

    private static void tinypng() {

    }
}
