package com.walkud.tp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walkud on 16/5/13.
 */
public class FileUtil {

    /**
     * 递归遍历素有文件
     *
     * @param strPath
     * @return
     */
    public static List<File> getFileList(String strPath) {
        List<File> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName().toLowerCase();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    filelist.addAll(getFileList(files[i].getAbsolutePath())); // 获取文件绝对路径
                } else if (fileName.endsWith("png") || fileName.endsWith("jpg")) { // 判断文件名是否以.png和.jpg结尾
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }


}
