package com.example.joseph.gellery_image;

import java.io.Serializable;

/**
 * Created by Joseph on 2016-02-01.
 */
public class MediaMetadata implements Serializable {
 String masterId;//123
String filePath;
String mimeType;
long fileSize;

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String id) {
        this.masterId = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

}

