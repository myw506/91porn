package com.u91porn.utils;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.u91porn.di.ApplicationContext;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

/**
 * @author flymegoc
 * @date 2018/2/10
 */
public class MyProxySelector extends ProxySelector {
    private static final String TAG = MyProxySelector.class.getSimpleName();
    private List<Proxy> proxyList;
    private Context context;
    private boolean isTest = false;

    public MyProxySelector(List<Proxy> proxyList, @ApplicationContext Context context) {
        this.proxyList = proxyList;
        this.context = context;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    @Override
    public List<Proxy> select(URI uri) {
//        //暂时只支持91porn视频
//        String url = (String) SPUtils.get(context, Keys.KEY_SP_PORN_91_VIDEO_ADDRESS, "");
//        //如果代理地址不为空，且端口正确设置Http代理
//        boolean isOpenProxy = (boolean) SPUtils.get(context, Keys.KEY_SP_OPEN_HTTP_PROXY, false);
//        String proxyHost = (String) SPUtils.get(context, Keys.KEY_SP_PROXY_IP_ADDRESS, "");
//        int port = (int) SPUtils.get(context, Keys.KEY_SP_PROXY_PORT, 0);
//
//        //如果url为空，直接跳过
//        if (TextUtils.isEmpty(url)) {
//            return null;
//        }
//        URI uri1 = URI.create(url);
//        //对比是不是同一链接，是则启用，否则跳过
//        if (uri1.equals(uri)) {
//            if (!isTest) {
//                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, port));
//                proxyList.clear();
//                proxyList.add(proxy);
//            }
//            Logger.t(TAG).d("select(URI uri)-----------------------------::::是相等的，可以启用了");
//            return proxyList;
//        }
//        Logger.t(TAG).d("select(URI uri)-----------------------------::::" + uri.toString());
        return null;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        Logger.t(TAG).d("connectFailed(URI uri, SocketAddress sa-----------------:::" + uri.toString());
    }
}
