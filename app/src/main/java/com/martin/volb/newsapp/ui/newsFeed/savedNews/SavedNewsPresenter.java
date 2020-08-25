package com.martin.volb.newsapp.ui.newsFeed.savedNews;

import android.content.Context;

import androidx.room.Room;

import com.martin.volb.newsapp.ui.newsFeed.data.Article;
import com.martin.volb.newsapp.ui.newsFeed.ArticleDao;
import com.martin.volb.newsapp.ui.newsFeed.NewsView;

import java.util.List;

public class SavedNewsPresenter {
    private NewsView newsView;

    public SavedNewsPresenter(NewsView newsView) {
        this.newsView = newsView;
    }

    public void requestData(Context context) {
        final ArticleDao articleDao = Room.databaseBuilder(context, ArticleDatabase.class, "saved_articles").build().articleDao();
        newsView.showProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Article> articles = articleDao.getAll();
                newsView.hideProgress();
                if (!articles.isEmpty()) {
                    newsView.showNewsFeed(articles);
                } else {
                    newsView.showError("No bookmarks found.");
                }
            }
        }).start();
    }

}
