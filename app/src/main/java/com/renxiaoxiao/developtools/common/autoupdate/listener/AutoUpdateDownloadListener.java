package com.renxiaoxiao.developtools.common.autoupdate.listener;

/**
 * Created by renxiaoxiao on 2016/9/20.
 * 下载监听器
 */

public interface AutoUpdateDownloadListener {

    /**
     * 下载开始回调
     */
    public void onStarted();

    /**
     * 进度更新回调
     * @param progress
     * @param downloadUrl
     */
    public void onProgressChanged(int progress, String downloadUrl);

    /**
     * 下载完成回调
     * @param completeSize
     * @param downloadUrl
     */
    public void onFinished(int completeSize, String downloadUrl);

    /**
     * 下载失败回调
     */
    public void onFailure();
}