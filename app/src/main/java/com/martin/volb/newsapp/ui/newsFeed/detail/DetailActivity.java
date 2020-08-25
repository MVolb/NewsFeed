package com.martin.volb.newsapp.ui.newsFeed.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.martin.volb.newsapp.R;
import com.martin.volb.newsapp.ui.newsFeed.data.Article;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements DetailView {
    public static final String ARTICLE_INTENT_EXTRA_KEY = "article";
    private ImageView imageView;
    private TextView titleTextView;
    private TextView detailTextView;
    private Button newsExternalButton;
    private MenuItem bookmarkItem;
    private Article article;
    private DetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        imageView = findViewById(R.id.image_detail);
        titleTextView = findViewById(R.id.title_text_view);
        detailTextView = findViewById(R.id.detail_text_view);
        newsExternalButton = findViewById(R.id.newsExternalButton);
        article = (Article) getIntent().getSerializableExtra(ARTICLE_INTENT_EXTRA_KEY);
        presenter = new DetailPresenter(article, this);

        if (article != null) {
            showArticle(article);
        }

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowHomeEnabled(true);
        }

        newsExternalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                startActivity(browserIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        bookmarkItem = menu.findItem(R.id.bookmark);
        presenter.checkIfBookmarked(getApplicationContext());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bookmark) {
            presenter.onBookmarkToggled(getApplicationContext());
            return true;
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


    @Override
    public void showArticleBookmarked(final boolean bookmarked) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bookmarkItem.setIcon(bookmarked ? R.drawable.ic_bookmark_true : R.drawable.ic_bookmark_false);
            }
        });
    }
}
