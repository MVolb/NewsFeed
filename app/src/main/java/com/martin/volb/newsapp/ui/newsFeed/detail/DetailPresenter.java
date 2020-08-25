package com.martin.volb.newsapp.ui.newsFeed.detail;

import android.content.Context;

import androidx.room.Room;

import com.martin.volb.newsapp.ui.newsFeed.data.Article;
import com.martin.volb.newsapp.ui.newsFeed.ArticleDao;
import com.martin.volb.newsapp.ui.newsFeed.savedNews.ArticleDatabase;

public class DetailPresenter {
    private DetailView detailView;
    private Article article;

    public DetailPresenter(Article article, DetailView detailView) {
        this.detailView = detailView;
        this.article = article;
    }

    public void onBookmarkToggled(Context context) {
        final ArticleDao articleDao = Room.databaseBuilder(context, ArticleDatabase.class, "saved_articles").build().articleDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Article savedArticle = articleDao.getArticle(article.getUrl());
                if (savedArticle != null) {
                    articleDao.delete(savedArticle);
                    detailView.showArticleBookmarked(false);
                } else {
                    articleDao.insertArticle(article);
                    detailView.showArticleBookmarked(true);
                }
            }
        }).start();
    }

    public void checkIfBookmarked(Context context) {
        final ArticleDao articleDao = Room.databaseBuilder(context, ArticleDatabase.class, "saved_articles").build().articleDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                detailView.showArticleBookmarked(articleDao.getArticle(article.getUrl()) != null);
            }
        }).start();
    }
}
