package com.walkud.tp;

import com.walkud.tp.utils.MLog;

import java.util.Arrays;
import java.util.List;

/**
 * 主入口
 * Created by Walkud on 16/5/4.
 */
public class Main {

//    private static boolean isGui(List<String> argsAsArray) {
//        return argsAsArray.isEmpty() || argsAsArray.get(0).equalsIgnoreCase("-g");
//    }

    public static void main(String[] args) {
        final List<String> argsAsArray = Arrays.asList(args);
        print(argsAsArray);
//        CliMode.with(argsAsArray);
//        //判断模式
//        if (isGui(argsAsArray)) {//GUI模式
//        } else {//命令行模式
//        }
    }

    /**
     * 打印参数
     *
     * @param list
     * @return
     */
    public static void print(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str + ",");
        }
        //输出当前用户路径
        String systemPath = System.getProperty("user.dir");
        MLog.d("systemPath ===> " + systemPath);
        //输出用户参数列表
        MLog.d("arguments ===> " + sb.toString());
    }


}
