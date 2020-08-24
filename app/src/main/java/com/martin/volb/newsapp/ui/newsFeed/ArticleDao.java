package com.martin.volb.newsapp.ui.newsFeed;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Insert
    void insertArticle(Article article);

    @Delete
    void delete(Article article);

    @Query("SELECT * FROM article WHERE url LIKE :url LIMIT 1")
    Article getArticle(String url);

}
