package com.u91porn.ui.setting;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.u91porn.data.network.Api;
import com.u91porn.data.network.Forum91PronServiceApi;
import com.u91porn.data.network.NoLimit91PornServiceApi;
import com.u91porn.data.network.PigAvServiceApi;
import com.u91porn.exception.MessageException;
import com.u91porn.rxjava.CallBackWrapper;
import com.u91porn.rxjava.RxSchedulersHelper;
import com.u91porn.ui.MvpBasePresenter;
import com.u91porn.utils.AddressHelper;
import com.u91porn.utils.CheckResultUtils;
import com.u91porn.utils.HeaderUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * @author flymegoc
 * @date 2018/2/6
 */

public class SettingPresenter extends MvpBasePresenter<SettingView> implements ISetting {

    private AddressHelper addressHelper;

    private NoLimit91PornServiceApi noLimit91PornServiceApi;
    private Forum91PronServiceApi forum91PronServiceApi;
    private PigAvServiceApi pigAvServiceApi;

    @Inject
    public SettingPresenter(LifecycleProvider<Lifecycle.Event> provider, AddressHelper addressHelper, NoLimit91PornServiceApi noLimit91PornServiceApi, Forum91PronServiceApi forum91PronServiceApi, PigAvServiceApi pigAvServiceApi) {
        super(provider);
        this.addressHelper = addressHelper;
        this.noLimit91PornServiceApi = noLimit91PornServiceApi;
        this.forum91PronServiceApi = forum91PronServiceApi;
        this.pigAvServiceApi = pigAvServiceApi;
    }

    @Override
    public void test91PornVideo(String baseUrl, final QMUICommonListItemView qmuiCommonListItemView, final String key) {
        // 全局 BaseUrl 的优先级低于 Domain-Name header 中单独配置的,其他未配置的接口将受全局 BaseUrl 的影响
        RetrofitUrlManager.getInstance().putDomain(Api.PORN91_VIDEO_DOMAIN_NAME, baseUrl);
        noLimit91PornServiceApi
                .indexPhp(HeaderUtils.getIndexHeader(addressHelper))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        if (!CheckResultUtils.check91PronVideoConnectIsSuccess(s)) {
                            throw new MessageException("很遗憾，测试失败了");
                        }
                        return "恭喜，测试成功了";
                    }
                })
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
        forum91PronServiceApi
                .index()
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        if (!CheckResultUtils.check91PornForumConnectIsSuccess(s)) {
                            throw new MessageException("很遗憾，测试失败了");
                        }
                        return "恭喜，测试成功了";
                    }
                })
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
        pigAvServiceApi
                .video(baseUrl)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        if (!CheckResultUtils.checkPigAvVideoConnectIsSuccess(s)) {
                            throw new MessageException("很遗憾，测试失败了");
                        }
                        return "恭喜，测试成功了";
                    }
                })
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
