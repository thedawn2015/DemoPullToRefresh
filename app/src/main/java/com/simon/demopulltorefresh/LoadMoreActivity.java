package com.simon.demopulltorefresh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 上拉加载
 * Created by xw on 2016/6/16
 */
public class LoadMoreActivity extends AppCompatActivity {
    public static String TAG = LoadMoreActivity.class.getSimpleName();

    private LoadMoreListView activity_load_more_list_view;

    private List<String> items;

    private LoadMoreAdapter adapter;

    private int index;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LoadMoreActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_load_more);
        activity_load_more_list_view = (LoadMoreListView) findViewById(R.id.activity_load_more_list_view);

        adapter = new LoadMoreAdapter(this);

        //上拉加载
        activity_load_more_list_view.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void loadMoreWhenBottom() {
                Log.i(TAG, "loadMoreWhenBottom: ");
                initData();
                adapter.addItems(items);
            }
        });

        activity_load_more_list_view.setAdapter(adapter);

        items = new ArrayList<>();
        initData();
        adapter.addItems(items);
    }

    private void initData() {
        items.clear();
        for (int i = 0; i < 10; i++) {
            items.add("index " + index + " : " + i);
        }
        index++;
    }
}
