package com.classicspin.wg.api;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.POST;
import rx.Observable;

public interface Api {

   @POST("bq1nMH2q")
   Observable<Response<ResponseBody>> sendRequest();
}
