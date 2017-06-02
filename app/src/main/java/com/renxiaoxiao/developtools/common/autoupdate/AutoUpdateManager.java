package com.renxiaoxiao.developtools.common.autoupdate;

import com.renxiaoxiao.developtools.common.autoupdate.listener.AutoUpdateDownloadListener;
import com.renxiaoxiao.developtools.common.autoupdate.request.AutoUpdateDownloadRequest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by renxiaoxiao on 2016/9/20.
 * 自动更新下载调度管理器
 */

public class AutoUpdateManager {

    private static AutoUpdateManager manager;
    private AutoUpdateDownloadRequest request;
    private ThreadPoolExecutor threadPoolExecutor;

    // 静态单例模式
    private AutoUpdateManager() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    static {
        manager = new AutoUpdateManager();
    }

    public static AutoUpdateManager getInstance() {
        return manager;
    }

    public void startDownloads(String downloadUrl, String localPath, AutoUpdateDownloadListener listener) {
        if (request != null ) {
            return;
        }
        checkLocalFilePath(localPath);

        //开始真正的去下载任务
        request = new AutoUpdateDownloadRequest(downloadUrl, localPath, listener);
        Future<?> future = threadPoolExecutor.submit(request);

    }

    // 用来检查文件路径是否已经存在
    private void checkLocalFilePath(String path) {
        File dir = new File(path.substring(0,path.lastIndexOf("/") + 1));
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
