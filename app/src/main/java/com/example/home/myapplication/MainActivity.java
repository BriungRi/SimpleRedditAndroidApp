package com.example.home.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button loadContentButton;
    private RecyclerView articleList;
    private RedditNewsAdapter redditNewsAdapter;
    private final RestCalls restCalls;

    public MainActivity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        restCalls = retrofit.create(RestCalls.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadContentButton = this.findViewById(R.id.loadContentButton);
        this.loadContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadContent();
            }
        });

        this.articleList = this.findViewById(R.id.articleList);
        this.articleList.setLayoutManager(new LinearLayoutManager(this));
        this.redditNewsAdapter = new RedditNewsAdapter();
        articleList.setAdapter(redditNewsAdapter);
    }

    private void showContent(RedditNewsResponse redditNewsResponse) {
        redditNewsAdapter.updateArticles(redditNewsResponse.getData().getChildren());
        redditNewsAdapter.notifyDataSetChanged();
    }

    private void loadContent() {
        restCalls.getNews(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new LoadContentObserver(this));
    }

    private static class LoadContentObserver extends DefaultObserver<RedditNewsResponse> {
        final WeakReference<MainActivity> mainActivityWeakReference;

        LoadContentObserver(MainActivity mainActivity) {
            this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void onNext(RedditNewsResponse redditNewsResponse) {
            MainActivity mainActivity = mainActivityWeakReference.get();
            if (mainActivity != null) {
                mainActivity.showContent(redditNewsResponse);
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
        }
    }
}


