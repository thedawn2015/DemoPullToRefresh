package com.simon.demopulltorefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 下拉刷新
 * Created by xw on 2016/6/16
 */
public class PullToRefreshActivity extends AppCompatActivity {
    public static String TAG = PullToRefreshActivity.class.getSimpleName();

    @BindView (R.id.list_view)
    ListView listView;
    @BindView (R.id.refresh_view)
    RefreshableView refreshView;

    private List<String> items;

    private LoadMoreAdapter adapter;

    private int index;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PullToRefreshActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ButterKnife.bind(this);

        adapter = new LoadMoreAdapter(this);
        listView.setAdapter(adapter);

        //下拉刷新的回调接口
        refreshView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refresh();
                refreshView.finishRefreshing();
            }
        }, 0);

        items = new ArrayList<>();
        initData();
        adapter.addItems(items);
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //        index = 0;
                initData();
                adapter.addItems(items);
            }
        });
    }

    private void initData() {
        items.clear();
        for (int i = 0; i < 10; i++) {
            items.add("index " + index + " : " + i);
        }
        index++;
    }
}
