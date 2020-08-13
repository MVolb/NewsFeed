package com.martin.volb.newsapp.ui.newsFeed;

import java.util.List;

public interface NewsView {
    void showNewsFeed(List<Article> articles);

    void showProgress();

    void hideProgress();

    void showError(String error);
}
