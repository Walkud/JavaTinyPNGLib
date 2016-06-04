package com.walkud.tp.bean;

/**
 * 图片信息
 * Created by Walkud on 16/5/13.
 */
public class ImageInfo {

    private String fileName;//文件名
    private String path;//文件路径
    private String status;//状态结果
    private long fileSize;
    private String size;//大小
    private String compressSize;//压缩后大小
    private int compressRate;//压缩率

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCompressSize() {
        return compressSize;
    }

    public void setCompressSize(String compressSize) {
        this.compressSize = compressSize;
    }

    public int getCompressRate() {
        return compressRate;
    }

    public void setCompressRate(int compressRate) {
        this.compressRate = compressRate;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
