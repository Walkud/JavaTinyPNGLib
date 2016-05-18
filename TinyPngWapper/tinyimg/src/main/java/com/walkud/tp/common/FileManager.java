package com.walkud.tp.common;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 文件管理类
 * Created by Walkud on 16/5/13.
 */
public class FileManager {

    public static final String TYPE_PNG = "png";//png类型
    public static final String TYPE_JPG = "jpg";//jpg类型

    // SelectFileFilter,并实现accept()与getDescription()方法.
    public static class SelectFileFilter extends FileFilter {

        /* 在accept()方法中,当程序所抓到的是一个目录而不是文件时,我们返回true值,表示将此目录显示出来. */
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith(TYPE_PNG) || fileName.endsWith(TYPE_JPG))
                return true;
            return false;
        }

        // 实现getDescription()方法,返回描述文件的说明字符串!!!
        public String getDescription() {
            if (TYPE_PNG.equals("png") || TYPE_JPG.equals("jpg"))
                return "PNG And JPG Image File(*.png or *.jpg)";
            return "";
        }
    }

}
