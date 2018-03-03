package com.u91porn.utils;

import android.content.Context;
import android.text.TextUtils;

import com.u91porn.di.ApplicationContext;
import com.u91porn.utils.constants.Keys;

import java.util.Random;

/**
 * @author flymegoc
 * @date 2018/1/26
 */

public class AddressHelper {

    private Context context;
    private Random mRandom;

    /**
     * 无需手动初始化,已在di中全局单例
     *
     * @param context ApplicationContext
     */
    public AddressHelper(@ApplicationContext Context context) {
        this.context = context;
        mRandom = new Random();
    }

    /**
     * 获取随机ip地址
     *
     * @return random ip
     */
    public String getRandomIPAddress() {

        return String.valueOf(mRandom.nextInt(255)) + "." + String.valueOf(mRandom.nextInt(255)) + "." + String.valueOf(mRandom.nextInt(255)) + "." + String.valueOf(mRandom.nextInt(255));
    }

    public String getVideo91PornAddress() {
        return (String) SPUtils.get(context, Keys.KEY_SP_CUSTOM_ADDRESS, "");
    }

    public String getForum91PornAddress() {
        return (String) SPUtils.get(context, Keys.KEY_SP_FORUM_91_PORN_ADDRESS, "");
    }

    public String getPigAvAddress() {
        return (String) SPUtils.get(context, Keys.KEY_SP_PIG_AV_ADDRESS, "");
    }

    public boolean isEmpty(String key) {
        String address = (String) SPUtils.get(context, key, "");
        return TextUtils.isEmpty(address);
    }
}
