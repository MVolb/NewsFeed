package com.martin.volb.newsapp.ui.savedNews;

import android.widget.Toast;

import com.martin.volb.newsapp.ui.newsFeed.Article;
import com.martin.volb.newsapp.ui.newsFeed.NewsView;

import java.util.List;

public class SavedNewsPresenter {
    private NewsView newsView;

    public SavedNewsPresenter(NewsView newsView) {
        this.newsView = newsView;
    }

    public void requestData() {
        newsView.showProgress();

        if (getSavedArticles().size() > 0) {
            newsView.showNewsFeed(getSavedArticles());
        }else {
            newsView.showError("No bookmarks found.");
        }

    }

    private List<Article> getSavedArticles() {
        //get from db
        return null;
    }

}
