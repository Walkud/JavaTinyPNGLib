package com.walkud.tp.utils;

import java.text.DecimalFormat;

/**
 * Created by Walkud on 16/5/13.
 */
public class StringUtil {

    /**
     * 文件大小单位转换
     *
     * @param size
     * @return
     */
    public static String converFileSize(long size) {
        DecimalFormat df = new DecimalFormat("###.##");
        float f = ((float) size / (float) (1024 * 1024));

        if (f < 1.0) {
            float f2 = ((float) size / (float) (1024));

            return df.format(new Float(f2).doubleValue()) + "KB";

        } else {
            return df.format(new Float(f).doubleValue()) + "M";
        }

    }
}
