package com.u91porn.ui.setting;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.u91porn.data.DataManager;
import com.u91porn.data.network.Api;
import com.u91porn.rxjava.CallBackWrapper;
import com.u91porn.rxjava.RxSchedulersHelper;
import com.u91porn.ui.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * @author flymegoc
 * @date 2018/2/6
 */

public class SettingPresenter extends MvpBasePresenter<SettingView> implements ISetting {

    private DataManager dataManager;

    @Inject
    public SettingPresenter(LifecycleProvider<Lifecycle.Event> provider, DataManager dataManager) {
        super(provider);
        this.dataManager = dataManager;
    }

    @Override
    public void test91PornVideo(String baseUrl, final QMUICommonListItemView qmuiCommonListItemView, final String key) {
        // 全局 BaseUrl 的优先级低于 Domain-Name header 中单独配置的,其他未配置的接口将受全局 BaseUrl 的影响
        RetrofitUrlManager.getInstance().putDomain(Api.PORN91_VIDEO_DOMAIN_NAME, baseUrl);
        dataManager.testPorn91VideoAddress()
                .compose(RxSchedulersHelper.<String>ioMainThread())
                .compose(provider.<String>bindToLifecycle())
                .subscribe(new CallBackWrapper<String>() {

                    @Override
                    public void onBegin(Disposable d) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.showTesting(true);
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final String s) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.testSuccess(s, qmuiCommonListItemView, key);
                            }
                        });
                    }

                    @Override
                    public void onError(final String msg, int code) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.testFailure(msg, qmuiCommonListItemView, key);
                            }
                        });
                    }
                });
    }


    @Override
    public void test91PornForum(String baseUrl, final QMUICommonListItemView qmuiCommonListItemView, final String key) {
        RetrofitUrlManager.getInstance().putDomain(Api.PORN91_FORUM_DOMAIN_NAME, baseUrl);
        dataManager.testPorn91ForumAddress()
                .compose(RxSchedulersHelper.<String>ioMainThread())
                .compose(provider.<String>bindToLifecycle())
                .subscribe(new CallBackWrapper<String>() {

                    @Override
                    public void onBegin(Disposable d) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.showTesting(true);
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final String s) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.testSuccess(s, qmuiCommonListItemView, key);
                            }
                        });
                    }

                    @Override
                    public void onError(final String msg, int code) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.testFailure(msg, qmuiCommonListItemView, key);
                            }
                        });
                    }
                });
    }

    @Override
    public void testPigAv(String baseUrl, final QMUICommonListItemView qmuiCommonListItemView, final String key) {
        RetrofitUrlManager.getInstance().putDomain(Api.PIGAV_DOMAIN_NAME, baseUrl);
        dataManager.testPigAvAddress(baseUrl)
                .compose(RxSchedulersHelper.<String>ioMainThread())
                .compose(provider.<String>bindToLifecycle())
                .subscribe(new CallBackWrapper<String>() {

                    @Override
                    public void onBegin(Disposable d) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.showTesting(true);
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final String s) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.testSuccess(s, qmuiCommonListItemView, key);
                            }
                        });
                    }

                    @Override
                    public void onError(final String msg, int code) {
                        ifViewAttached(new ViewAction<SettingView>() {
                            @Override
                            public void run(@NonNull SettingView view) {
                                view.testFailure(msg, qmuiCommonListItemView, key);
                            }
                        });
                    }
                });
    }
}
