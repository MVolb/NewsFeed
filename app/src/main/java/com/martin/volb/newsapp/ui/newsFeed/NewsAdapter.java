package com.martin.volb.newsapp.ui.newsFeed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.martin.volb.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CustomViewHodler> {
    private List<Article> dataList;
    private Context context;
    private ArticleClickListener listener;

    public NewsAdapter(List<Article> dataList, Context context, ArticleClickListener listener) {
        this.dataList = dataList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_element, parent, false);
        return new CustomViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHodler holder, int position) {
        final Article article = dataList.get(position);
        holder.textTitle.setText(article.getTitle());
        holder.textContent.setText(article.getDescription());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build()
                .load(article.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onArticleClicked(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHodler extends RecyclerView.ViewHolder {
        public final View view;
        private TextView textTitle;
        private TextView textContent;
        private ImageView coverImage;

        public CustomViewHodler(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            textTitle = view.findViewById(R.id.title);
            textContent = view.findViewById(R.id.textContent);
            coverImage = view.findViewById(R.id.coverImage);
        }
    }
}