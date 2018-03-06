package com.u91porn.parser;

import com.u91porn.data.model.BaseResult;
import com.u91porn.data.model.ProxyModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * 代理抓取
 *
 * @author flymegoc
 * @date 2018/1/20
 */

public class ParseProxy {
    private static final String TAG = ParseProxy.class.getSimpleName();

    public static BaseResult<List<ProxyModel>> parseXiCiDaiLi(String html, int page) {
        BaseResult<List<ProxyModel>> baseResult = new BaseResult<>();
        baseResult.setTotalPage(1);
        Document doc = Jsoup.parse(html);

        Element ipList = doc.getElementById("ip_list");

        return baseResult;
    }
}
