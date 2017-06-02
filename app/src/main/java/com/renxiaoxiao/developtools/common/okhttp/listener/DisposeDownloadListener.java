package com.renxiaoxiao.developtools.common.okhttp.listener;

/**
 * Created by renxiaoxiao on 2016/11/1.
 * 下载进度回调
 */

public interface DisposeDownloadListener extends DisposeDataListener {

    public void onProgress(int progress);

}
