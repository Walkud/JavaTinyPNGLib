package com.walkud.tp.utils;

/**
 * ApiKey
 * Created by Walkud on 16/5/4.
 */
public class ApiKey {

    private static String apiKey;//ApiKey


    public static void setApiKey(String apiKey) {
        check(apiKey);
        ApiKey.apiKey = apiKey;
    }

    /**
     * 获取ApiKey
     *
     * @return
     */
    public static String getApiKey() {
        return apiKey;
    }


    /**
     * ApiKey检查
     *
     * @param apiKey
     */
    public static void check(String apiKey) {
        if (apiKey == null || apiKey.trim().length() == 0) {
            throw new IllegalArgumentException("无效Key");
        }

    }

}
