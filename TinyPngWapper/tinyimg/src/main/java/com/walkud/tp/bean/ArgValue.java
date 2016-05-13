package com.walkud.tp.bean;

/**
 * 命令参数
 * Created by Walkud on 16/5/6.
 */
public class ArgValue {

    /**
     * ApiKey Command
     */
    public static final String COMMAND_KEY = "-key";

    /**
     * Image compressed directory Command
     */
    public static final String COMMAND_PATH = "-path";

    private String command;//命令
    private String value;//值

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
