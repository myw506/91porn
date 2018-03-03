package com.u91porn.di.component;

import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.google.gson.Gson;
import com.u91porn.MyApplication;
import com.u91porn.cookie.SetCookieCache;
import com.u91porn.cookie.SharedPrefsCookiePersistor;
import com.u91porn.data.cache.CacheProviders;
import com.u91porn.data.model.User;
import com.u91porn.data.network.Forum91PronServiceApi;
import com.u91porn.data.network.GitHubServiceApi;
import com.u91porn.data.network.MeiZiTuServiceApi;
import com.u91porn.data.network.Mm99ServiceApi;
import com.u91porn.data.network.NoLimit91PornServiceApi;
import com.u91porn.data.network.PigAvServiceApi;
import com.u91porn.data.network.ProxyServiceApi;
import com.u91porn.di.ApplicationContext;
import com.u91porn.di.module.ApiServiceModule;
import com.u91porn.di.module.ApplicationModule;
import com.u91porn.utils.AddressHelper;
import com.u91porn.utils.MyProxySelector;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author flymegoc
 * @date 2018/2/4
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiServiceModule.class})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);

    @ApplicationContext
    Context getContext();

    HttpProxyCacheServer getHttpProxyCacheServer();

    CacheProviders getCacheProviders();

    User getUser();

    GitHubServiceApi getGitHubServiceApi();

    MeiZiTuServiceApi getMeiZiTuServiceApi();

    Mm99ServiceApi getMm99ServiceApi();

    NoLimit91PornServiceApi getNoLimit91PornServiceApi();

    Forum91PronServiceApi getForum91PronServiceApi();

    PigAvServiceApi getPigAvServiceApi();

    AddressHelper getAddressHelper();

    ProxyServiceApi getProxyServiceApi();

    SharedPrefsCookiePersistor getSharedPrefsCookiePersistor();

    SetCookieCache getSetCookieCache();

    PersistentCookieJar getPersistentCookieJar();

    MyProxySelector getMyProxySelector();

    Gson getGson();
}
