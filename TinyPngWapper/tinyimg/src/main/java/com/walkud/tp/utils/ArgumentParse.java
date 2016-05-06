package com.walkud.tp.utils;

import com.walkud.tp.Configs;
import com.walkud.tp.bean.ArgValue;
import com.walkud.tp.exception.ArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 命令参数解析类
 * Created by Walkud on 16/5/6.
 */
public class ArgumentParse {

    /**
     * 解析命令参数
     *
     * @param args
     * @return
     * @throws ArgumentException
     */
    public static Map<String, ArgValue> parse(String[] args) throws ArgumentException {

        List<String> argumengs = Arrays.asList(args);

        checkArguments(argumengs);

        List<String> commands = new ArrayList<>();
        commands.add(ArgValue.COMMAND_KEY);
        commands.add(ArgValue.COMMAND_PATH);


        Map<String, ArgValue> argValueMap = new HashMap<>();
        int size = args.length;
        int i = 0;
        while (i < size) {

            String commond = argumengs.get(i).toLowerCase();
            //判断首个参数-key 是否存在
            if (i == 0 && !ArgValue.COMMAND_KEY.equals(commond)) {
                throwException("-key argument required");
            }

            if (commands.contains(commond)) {//判断是否为命令参数
                ArgValue argValue = new ArgValue();
                argValue.setCommand(commond);
                if (++i < size) {//判断是否带有参数值
                    String value = argumengs.get(i);//获取下一个参数
                    if (value.startsWith("-")) {//判断是否为命令参数
                        throwException("command:" + commond + " value is wrong");
                    } else {
                        argValue.setValue(value);
                        i++;
                    }
                    argValueMap.put(commond, argValue);
                } else {
                    throwException("command:" + commond + " value not found");
                }
            } else {//命令参数错误
                throwException(commond + " command was not found");
            }

        }

        return argValueMap;
    }


    /**
     * 检查参数
     *
     * @param args
     * @throws ArgumentException
     */
    private static void checkArguments(List<String> args) throws ArgumentException {
        //-help
        if (!args.isEmpty() && args.get(0).equalsIgnoreCase("-help")) {
            //输出参数帮助信息
            throw new ArgumentException(Configs.ARGUMENTS_MESSAGE);
        }

        //参数错误
        if (args.size() < 2) {
            throwException("command line arguments ");
        }

    }

    /**
     * 抛异常
     *
     * @param msg
     * @throws ArgumentException
     */
    private static void throwException(String msg) throws ArgumentException {
        throw new ArgumentException("Wrong: " + msg + ". Help===>" + Configs.HELP_COMMAND);
    }
}
