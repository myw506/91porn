package com.u91porn.ui.proxy;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.u91porn.data.network.Api;
import com.u91porn.data.network.NoLimit91PornServiceApi;
import com.u91porn.data.network.ProxyServiceApi;
import com.u91porn.data.model.BaseResult;
import com.u91porn.data.model.ProxyModel;
import com.u91porn.parser.ParseProxy;
import com.u91porn.rxjava.CallBackWrapper;
import com.u91porn.rxjava.RxSchedulersHelper;
import com.u91porn.utils.AddressHelper;
import com.u91porn.utils.CheckResultUtils;
import com.u91porn.utils.HeaderUtils;
import com.u91porn.utils.RegexUtils;
import com.u91porn.utils.constants.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author flymegoc
 * @date 2018/1/20
 */

public class ProxyPresenter extends MvpBasePresenter<ProxyView> implements IProxy {

    private static final String TAG = ProxyPresenter.class.getSimpleName();
    private long successTime = 0;
    private ProxyServiceApi proxyServiceApi;
    private LifecycleProvider<Lifecycle.Event> provider;
    private int totalPage = 1;
    private int page = 1;
    private NoLimit91PornServiceApi noLimit91PornServiceApi;
    private AddressHelper addressHelper;
    @Inject
    public ProxyPresenter(ProxyServiceApi proxyServiceApi, LifecycleProvider<Lifecycle.Event> provider, NoLimit91PornServiceApi noLimit91PornServiceApi,AddressHelper addressHelper) {
        this.proxyServiceApi = proxyServiceApi;
        this.provider = provider;
        this.noLimit91PornServiceApi = noLimit91PornServiceApi;
        this.addressHelper=addressHelper;
    }

    @Override
    public void testProxy(String proxyIpAddress, int proxyPort) {
        if (RegexUtils.isIP(proxyIpAddress) && proxyPort < Constants.PROXY_MAX_PORT && proxyPort > 0) {
            noLimit91PornServiceApi.indexPhp(HeaderUtils.getIndexHeader(addressHelper))
                    .compose(RxSchedulersHelper.<String>ioMainThread())
                    .subscribe(new CallBackWrapper<String>() {
                        @Override
                        public void onBegin(Disposable d) {
                            successTime = System.currentTimeMillis();
                            ifViewAttached(new ViewAction<ProxyView>() {
                                @Override
                                public void run(@NonNull ProxyView view) {
                                    view.showLoading(false);
                                }
                            });
                        }

                        @Override
                        public void onSuccess(final String s) {
                            successTime = System.currentTimeMillis() - successTime;
                            ifViewAttached(new ViewAction<ProxyView>() {
                                @Override
                                public void run(@NonNull ProxyView view) {
                                    view.showContent();
                                    if (CheckResultUtils.check91PronVideoConnectIsSuccess(s)) {
                                        view.testProxySuccess("测试成功，用时：" + successTime + " ms");
                                    } else {
                                        view.testProxyError("访问成功，但无法获取内容");
                                    }

                                }
                            });
                        }

                        @Override
                        public void onError(final String msg, int code) {
                            ifViewAttached(new ViewAction<ProxyView>() {
                                @Override
                                public void run(@NonNull ProxyView view) {
                                    view.testProxyError("测试失败：" + msg);
                                }
                            });
                        }
                    });
        } else {
            ifViewAttached(new ViewAction<ProxyView>() {
                @Override
                public void run(@NonNull ProxyView view) {
                    view.testProxyError("代理IP或端口不正确");
                }
            });
        }

    }

    @Override
    public void parseGouBanJia(final boolean pullToRefresh) {
        if (pullToRefresh) {
            page = 1;
        }
        proxyServiceApi.parseGouBanJia(getParseUrl(page))
                .map(new Function<String, List<ProxyModel>>() {
                    @Override
                    public List<ProxyModel> apply(String s) throws Exception {
                        BaseResult<List<ProxyModel>> baseResult = ParseProxy.parseGouBanJia(s);
                        if (page == 1) {
                            totalPage = baseResult.getTotalPage();
                        }
                        return baseResult.getData();
                    }
                })
                .compose(RxSchedulersHelper.<List<ProxyModel>>ioMainThread())
                .compose(provider.<List<ProxyModel>>bindUntilEvent(Lifecycle.Event.ON_STOP))
                .subscribe(new CallBackWrapper<List<ProxyModel>>() {
                    @Override
                    public void onBegin(Disposable d) {
                        ifViewAttached(new ViewAction<ProxyView>() {
                            @Override
                            public void run(@NonNull ProxyView view) {
                                if (!pullToRefresh && page == 1) {
                                    view.beginParseProxy();
                                }
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final List<ProxyModel> proxyModels) {
                        ifViewAttached(new ViewAction<ProxyView>() {
                            @Override
                            public void run(@NonNull ProxyView view) {
                                if (page == 1) {
                                    view.parseGouBanJiaSuccess(proxyModels);
                                    view.showContent();
                                } else {
                                    view.setMoreData(proxyModels);
                                    view.loadMoreDataComplete();
                                }
                                if (page >= totalPage) {
                                    view.noMoreData();
                                } else {
                                    page++;
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ifViewAttached(new ViewAction<ProxyView>() {
                            @Override
                            public void run(@NonNull ProxyView view) {
                                if (page == 1) {
                                    view.showError("解析失败了");
                                } else {
                                    view.loadMoreFailed();
                                }
                            }
                        });
                    }
                });

    }

    private String getParseUrl(int page) {
        return Api.APP_PROXY_GUO_BAN_JIA_DOMAIN + "free/country/美国/index" + page + ".shtml";
    }
}
