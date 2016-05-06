package com.walkud.tp.utils;

import com.tinify.Source;
import com.tinify.Tinify;

import java.io.File;
import java.io.IOException;

/**
 * Tinify SDK 工具类
 * Created by Walkud on 16/5/6.
 */
public class TinifyUtil {

    //图片压缩后存放的目录
    private static File targetDirectory;

    /**
     * 设置图片压缩后存放的目录
     *
     * @param targetDirectory
     */
    public static void setTargetDirectory(File targetDirectory) {
        TinifyUtil.targetDirectory = targetDirectory;
    }

    /**
     * 获取图片压缩后存放的目录
     *
     * @return
     */
    public static File getTargetDirectory() {
        return targetDirectory;
    }

    /**
     * 设置ApiKey并验证是否有效
     *
     * @param apiKey
     */
    public static boolean setKeyAndValidate(String apiKey) {
        Tinify.setKey(apiKey);
        return Tinify.validate();
    }

    /**
     * 压缩图片<br/>
     * 如果toPath存在，则将压缩后的图片存放至toPath目录下
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @throws IOException
     */
    public static void compression(String filePath, String fileName) throws IOException {
        String file = filePath + fileName;
        Source source = Tinify.fromFile(file);
        if (targetDirectory == null) {
            source.toFile(file);
        } else {
            source.toFile(targetDirectory.getAbsolutePath() + fileName);
        }
    }


}
