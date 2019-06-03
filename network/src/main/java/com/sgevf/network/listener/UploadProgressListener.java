package com.sgevf.network.listener;

public interface UploadProgressListener {
    void progress(long currentBytesCount, long totalBytesCount, String name);
}
