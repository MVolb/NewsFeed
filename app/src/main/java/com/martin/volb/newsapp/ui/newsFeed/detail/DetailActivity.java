package com.martin.volb.newsapp.ui.newsFeed.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.martin.volb.newsapp.R;
import com.martin.volb.newsapp.ui.newsFeed.Article;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String ARTICLE_INTENT_EXTRA_KEY = "article";
    private ImageView imageView;
    private TextView titleTextView;
    private TextView detailTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        imageView = findViewById(R.id.image_detail);
        titleTextView = findViewById(R.id.title_text_view);
        detailTextView = findViewById(R.id.detail_text_view);

        Article article = (Article) getIntent().getSerializableExtra(ARTICLE_INTENT_EXTRA_KEY);

        if (article != null) {
            showArticle(article);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bookmark) {
            //addToBookmarks();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showArticle(Article article) {
        titleTextView.setText(article.getTitle());
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build()
                .load(article.getUrlToImage())
                .placeholder(R.drawable.missing_image_background)
                .error(R.drawable.missing_image_background)
                .into(imageView);

        detailTextView.setText(article.getContent());
    }


}
