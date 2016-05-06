package com.walkud.tp;

/**
 * 常量类
 * Created by Walkud on 16/5/5.
 */
public class Configs {

    /**
     * 环境配置，true:正式环境，false:开发环境
     */
    public static final boolean ENVIRONMENT = false;

    public static final String JAR_NAME = "TinyImg.jar";

    /**
     * 帮助命令
     */
    public static final String HELP_COMMAND;

    /**
     * Help参数信息
     */
    public static final String ARGUMENTS_MESSAGE;


    /**
     * 构建帮助信息
     */
    static {
        StringBuilder helpBuilder = new StringBuilder();
        helpBuilder.append("java -jar " + JAR_NAME);

        HELP_COMMAND = helpBuilder.toString() + " -help";

        helpBuilder.append(" -key YOUR_API_KEY [option]\n");
        ARGUMENTS_MESSAGE = "-path xxx Compressed image to the path\n";
    }

}

