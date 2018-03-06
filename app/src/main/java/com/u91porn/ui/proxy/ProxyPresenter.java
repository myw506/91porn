package com.u91porn.ui.proxy;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.u91porn.data.DataManager;
import com.u91porn.data.model.BaseResult;
import com.u91porn.data.model.ProxyModel;
import com.u91porn.rxjava.CallBackWrapper;
import com.u91porn.rxjava.RxSchedulersHelper;
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
    private LifecycleProvider<Lifecycle.Event> provider;
    private int totalPage = 1;
    private int page = 1;
    private DataManager dataManager;

    @Inject
    public ProxyPresenter(LifecycleProvider<Lifecycle.Event> provider, DataManager dataManager) {
        this.provider = provider;
        this.dataManager = dataManager;
    }

    @Override
    public void testProxy(String proxyIpAddress, int proxyPort) {
        if (RegexUtils.isIP(proxyIpAddress) && proxyPort < Constants.PROXY_MAX_PORT && proxyPort > 0) {
            dataManager.testPorn91VideoAddress()
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
                                    view.testProxySuccess("测试成功，用时：" + successTime + " ms");
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
        dataManager.loadXiCiDaiLiProxyData(page)
                .map(new Function<BaseResult<List<ProxyModel>>, List<ProxyModel>>() {
                    @Override
                    public List<ProxyModel> apply(BaseResult<List<ProxyModel>> baseResult) throws Exception {
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
}
