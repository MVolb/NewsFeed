package com.martin.volb.newsapp.ui.newsFeed.savedNews;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.martin.volb.newsapp.ui.newsFeed.data.Article;
import com.martin.volb.newsapp.ui.newsFeed.ArticleDao;

@Database(entities = Article.class, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
