package com.u91porn.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

/**
 * @author flymegoc
 * @date 2018/1/20
 */

public interface ProxyServiceApi {

    @Headers({"Domain-Name: " + Api.GUO_BAN_JIA_DOMAIN_NAME})
    @GET
    Observable<String> parseGouBanJia(@Url String url);
}
