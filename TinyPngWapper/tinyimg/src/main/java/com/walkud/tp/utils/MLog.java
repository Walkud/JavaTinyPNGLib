package com.walkud.tp.utils;

import com.walkud.tp.Configs;

/**
 * 日志输出类
 * Created by Walkud on 16/5/5.
 */
public class MLog {

    /**
     * Debug输出
     *
     * @param msg
     */
    public static void d(String msg) {
        if (!Configs.ENVIRONMENT) {
            p(msg);
        }
    }

    /**
     * 输出日志
     *
     * @param msg
     */
    public static void p(String msg) {
        System.out.println(msg);
    }
}
