package com.u91porn.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * @author flymegoc
 * @date 2018/1/17
 */

public interface MeiZiTuServiceApi {

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("page/{page}/")
    Observable<String> index(@Path("page") int page);

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("hot/page/{page}/")
    Observable<String> hot(@Path("page") int page);

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("best/page/{page}/")
    Observable<String> best(@Path("page") int page);

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("xinggan/page/{page}/")
    Observable<String> sexy(@Path("page") int page);

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("japan/page/{page}/")
    Observable<String> japan(@Path("page") int page);

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("taiwan/page/{page}/")
    Observable<String> taiwan(@Path("page") int page);

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("mm/page/{page}/")
    Observable<String> mm(@Path("page") int page);

    @Headers({"Referer: " + Api.APP_MEIZITU_DOMAIN,
            "Domain-Name: " + Api.MEI_ZI_TU_DOMAIN_NAME})
    @GET("{id}")
    Observable<String> imageList(@Path("id") int id);
}
