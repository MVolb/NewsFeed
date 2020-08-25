package com.martin.volb.newsapp.ui.newsFeed;

import com.martin.volb.newsapp.ui.newsFeed.data.Article;
import com.martin.volb.newsapp.ui.newsFeed.data.ArticleResponse;
import com.martin.volb.newsapp.ui.newsFeed.network.NewsService;
import com.martin.volb.newsapp.ui.newsFeed.network.RetroFitClientNews;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter {
    private NewsView newsView;

    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
    }

    public void requestData(String apiKey) {
        newsView.showProgress();
        NewsService service = RetroFitClientNews.getRetrofitInstance().create(NewsService.class);
        Call<ArticleResponse> call = service.getArticles(Locale.getDefault().getCountry().toLowerCase(), apiKey);

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                newsView.hideProgress();
                newsView.showNewsFeed(response.body() != null ? response.body().getArticles() : new ArrayList<Article>());
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                newsView.hideProgress();
                newsView.showError(t.getLocalizedMessage());
            }
        });
    }
}