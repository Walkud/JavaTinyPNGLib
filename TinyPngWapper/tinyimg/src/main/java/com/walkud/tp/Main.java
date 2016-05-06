package com.walkud.tp;

import com.walkud.tp.bean.ArgValue;
import com.walkud.tp.cli.CliMode;
import com.walkud.tp.exception.ArgumentException;
import com.walkud.tp.utils.ArgumentParse;
import com.walkud.tp.utils.MLog;
import com.walkud.tp.utils.TinifyUtil;

import java.io.File;
import java.util.Map;

/**
 * 主入口
 * Created by Walkud on 16/5/4.
 */
public class Main {

//    private static boolean isGui(List<String> argsAsArray) {
//        return argsAsArray.isEmpty() || argsAsArray.get(0).equalsIgnoreCase("-g");
//    }

    public static void main(String[] args) {
        try {


            Map<String, ArgValue> argValues = ArgumentParse.parse(args);
            //打印参数
            print(argValues);

            //设置图像压缩后存放路径
            setToPath(argValues);


            ArgValue operand = argValues.get(ArgValue.COMMAND_KEY);
            if (operand != null) {
                //判断ApiKey是否有效
                String apiKey = operand.getValue();
                if (!TinifyUtil.setKeyAndValidate(apiKey)) {
                    throw new ArgumentException("无效Key");
                }
            } else {
                throw new ArgumentException("Wrong arguments ==> " + operand);
            }


            CliMode.with(argValues);
//        //判断模式
//        if (isGui(argsAsArray)) {//GUI模式
//        } else {//命令行模式
//        }

        } catch (ArgumentException e) {
            MLog.p(e.getMessage());
        }
    }


    /**
     * 打印参数
     *
     * @param argValues
     * @return
     */
    public static void print(Map<String, ArgValue> argValues) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ArgValue> entry : argValues.entrySet()) {
            sb.append("command:" + entry.getKey() + ",value:" + entry.getValue().getValue() + "\n");
        }
        //输出当前用户路径
        String systemPath = System.getProperty("user.dir");
        MLog.d("systemPath ===> " + systemPath);
        //输出用户参数列表
        MLog.d("arguments ===> " + sb.toString());
    }


    /**
     * 设置图片压缩后的目录
     *
     * @param argValueMap
     */
    private static void setToPath(Map<String, ArgValue> argValueMap) throws ArgumentException {
        //判断path参数是否存在
        ArgValue argValue = argValueMap.get(ArgValue.COMMAND_PATH);
        if (argValue != null) {
            String path = argValue.getValue();
            File file = new File(path);
            if (!file.isDirectory()) {//判断目录是否有效
                throw new ArgumentException("-path ===> " + path + " The Directory is invalid");
            }
            TinifyUtil.setTargetDirectory(file);
        }
    }

}
