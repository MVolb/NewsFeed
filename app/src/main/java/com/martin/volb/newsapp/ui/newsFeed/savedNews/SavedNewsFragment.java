package com.martin.volb.newsapp.ui.newsFeed.savedNews;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.martin.volb.newsapp.R;
import com.martin.volb.newsapp.ui.newsFeed.data.Article;
import com.martin.volb.newsapp.ui.newsFeed.ArticleClickListener;
import com.martin.volb.newsapp.ui.newsFeed.NewsAdapter;
import com.martin.volb.newsapp.ui.newsFeed.NewsView;
import com.martin.volb.newsapp.ui.newsFeed.detail.DetailActivity;

import java.util.List;

public class SavedNewsFragment extends Fragment implements NewsView, ArticleClickListener {


    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SavedNewsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        presenter = new SavedNewsPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setColorSchemeColors(getContext().getColor(R.color.colorPrimary), getContext().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.requestData(getContext());
            }
        });

        presenter.requestData(getContext());
    }

    @Override
    public void showNewsFeed(final List<Article> articles) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new NewsAdapter(articles, getContext(), SavedNewsFragment.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(final String error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onArticleClicked(Article article) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.ARTICLE_INTENT_EXTRA_KEY, article);
        startActivity(intent);
    }
}