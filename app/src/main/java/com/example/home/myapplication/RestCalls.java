package com.example.home.myapplication;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by home on 2/10/2018.
 */

public interface RestCalls {
    @GET("top.json")
    Observable<RedditNewsResponse> getNews(@Query("limit") int limit);
}
