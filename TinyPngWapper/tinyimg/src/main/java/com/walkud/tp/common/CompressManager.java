package com.walkud.tp.common;

import com.walkud.tp.bean.ImageInfo;
import com.walkud.tp.utils.StringUtil;
import com.walkud.tp.utils.TinifyUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片压缩管理
 * Created by Walkud on 16/5/13.
 */
public class CompressManager {
    private static CompressManager compressManager;

    private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    private CompressManager() {

    }

    public static CompressManager getInstance() {
        if (compressManager == null) {
            compressManager = new CompressManager();
        }
        return compressManager;
    }

    public interface Callback {

        void complete(int position, ImageInfo imageInfo);

        void startStatus(int position, String status);
    }

    /**
     * 开启线程压缩
     *
     * @param imageInfos
     */
    public void compress(final List<ImageInfo> imageInfos, final Callback callback) {
        for (int i = 0; i < imageInfos.size(); i++) {
            final int position = i;
            ImageInfo imageInfo = imageInfos.get(i);
            fixedThreadPool.execute(() -> {

                try {
                    //调用tinypngSdk上传压缩文件
                    String path = imageInfo.getPath();
                    callback.startStatus(position, "压缩中");
                    TinifyUtil.compression(path);

                    //获取之前大小
                    double beforeSize = imageInfo.getFileSize();
                    //获取压缩后的大小
                    File file = new File(path);
                    long size = file.length();
                    String sizeStr = StringUtil.converFileSize(size);
                    imageInfo.setCompressSize(sizeStr);

                    //计算压缩比例
                    int scale = (int) ((beforeSize - size) / beforeSize * 100);
                    String scaleStr = String.valueOf(scale) + "%";
                    imageInfo.setCompressRate(scaleStr);

                    imageInfo.setStatus("成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    imageInfo.setStatus("失败");
                }

                callback.complete(position, imageInfo);

            });
        }

    }

}
