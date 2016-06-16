package com.simon.demopulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by xw on 2016/6/16.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    public static String TAG = LoadMoreListView.class.getSimpleName();

    private OnLoadMoreListener loadMoreListener;

    private int LastVisiblePosition = 0;
    private int lastVisiblePositionY = 0;

    public LoadMoreListView(Context context) {
        super(context);
        initViews();
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            //滚动到底部
            if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                View v = view.getChildAt(view.getChildCount() - 1);
                int[] location = new int[2];
                //获取在整个屏幕内的绝对坐标
                v.getLocationOnScreen(location);
                int y = location[1];
                //                Log.e("x=" + location[0], "\ny=" + location[1]);
                //第一次拖至底部
                //                Log.i(TAG, "in onScrollStateChanged: LastVisiblePosition=" + LastVisiblePosition);
                //                Log.i(TAG, "in onScrollStateChanged: lastVisiblePositionY=" + lastVisiblePositionY);
                if (view.getLastVisiblePosition() != LastVisiblePosition && lastVisiblePositionY != y) {
                    Toast.makeText(view.getContext(), "再次拖至底部，即可翻页", Toast.LENGTH_SHORT).show();
                    LastVisiblePosition = view.getLastVisiblePosition();
                    lastVisiblePositionY = y;
                    return;
                } else if (view.getLastVisiblePosition() == LastVisiblePosition && lastVisiblePositionY == y) {
                    //第二次拖至底部
                    //                    Log.i(TAG, "onScrollStateChanged: 滑动到底部");
                    if (loadMoreListener != null) {
                        loadMoreListener.loadMoreWhenBottom();
                    }
                }
            }
            //            Log.i(TAG, "out onScrollStateChanged: LastVisiblePosition=" + LastVisiblePosition);
            //            Log.i(TAG, "out onScrollStateChanged: lastVisiblePositionY=" + lastVisiblePositionY);
            //未滚动到底部，第二次拖至底部都初始化
            LastVisiblePosition = 0;
            lastVisiblePositionY = 0;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //        Log.i(TAG, "onScroll: firstVisibleItem=" + firstVisibleItem);
        //        Log.i(TAG, "onScroll: visibleItemCount=" + visibleItemCount);
        //        Log.i(TAG, "onScroll: totalItemCount=" + totalItemCount);

        int lastItem = firstVisibleItem + visibleItemCount;
        if (lastItem == totalItemCount) {
            //            if (preFirstIndex != lastItem) {
            //                preFirstIndex = lastItem;
            //                if (loadMoreListener != null) {
            //                    loadMoreListener.loadMoreWhenBottom();
            //                }
            //            }
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface OnLoadMoreListener {
        void loadMoreWhenBottom();
    }
}
