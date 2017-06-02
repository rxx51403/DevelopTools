package com.renxiaoxiao.developtools.common.okhttp.listener;

import java.util.ArrayList;

/**
 * Created by renxiaoxiao on 2016/11/1.
 * 当需要专门处理Cookie时创建此回调接口
 */

public interface DisposeHandleCookieListener {
    public void onCookie(ArrayList<String> cookieStrList);
}
