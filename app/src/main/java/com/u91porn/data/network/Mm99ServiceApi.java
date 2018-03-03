package com.u91porn.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author flymegoc
 * @date 2018/2/1
 */

public interface Mm99ServiceApi {

    @Headers({"Domain-Name: " + Api.MM_99_DOMAIN_NAME})
    @GET
    Observable<String> imageList(@Url String url);

    @Headers({"Referer: http://www.99mm.me/meitui/",
            "Domain-Name: " + Api.MM_99_DOMAIN_NAME})
    @GET("url.php")
    Observable<String> imageLists(@Query("act") String act, @Query("id") int id);
}
