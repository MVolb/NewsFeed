package com.martin.volb.newsapp.ui.newsFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("v2/top-headlines")
    Call<ArticleResponse> getArticles(@Query("country") String country, @Query("apiKey") String apiKey);
}
