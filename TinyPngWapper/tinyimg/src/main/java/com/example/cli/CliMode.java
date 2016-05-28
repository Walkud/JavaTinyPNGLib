package com.example.cli;

import java.io.File;
import java.util.List;

/**
 * 命令行模式
 * Created by jan on 16/5/4.
 */
public class CliMode {


    private static final String ERROR_MESSAGE = "";

    private CliMode() {
    }

    public static void with(List<String> args) {
        if (args.size() < 2) {
            System.err.println("missing command line arguments " + "\n\n\n" + ERROR_MESSAGE);
            return;
        }

        File archiveFile = new File(args.get(1));
        if (!archiveFile.exists()) {
            System.err.println("File doesn't exist ==> " + archiveFile + "\n\n\n" + ERROR_MESSAGE);
            return;
        }

        final String operand = args.get(0).toLowerCase();
        switch (operand) {
            case "-export":
                break;
            case "-inspect":
                break;
            case "-methodcounts":
                break;
            case "-update":
                break;
            default:
                System.err.println("wrong operand ==> " + operand + "\n\n\n" + ERROR_MESSAGE);
        }
    }
}
