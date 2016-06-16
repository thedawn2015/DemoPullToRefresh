package com.simon.demopulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();
    @BindView (R.id.btn_refresh)
    Button btnRefresh;
    @BindView (R.id.btn_load_more)
    Button btnLoadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick ({R.id.btn_refresh, R.id.btn_load_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:
                //下拉刷新
                PullToRefreshActivity.launch(MainActivity.this);
                break;
            case R.id.btn_load_more:
                //下拉加载
                LoadMoreActivity.launch(MainActivity.this);
                break;
        }
    }
}
