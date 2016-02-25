package com.example.admin.loadmorelistviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * 创建者：黄凯军
 * 创建时间：2015/12/17 10:53
 * 类描述：
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    private Context mContext;
    private RelativeLayout footView;
    private int footViewHeight;
    private OnLoadMoreListener mOnLoadMoreListener;
    /**
     * 是否在加载更多
     */
    private boolean onLoadMore = false;

    private boolean canLoadMore;

    public LoadMoreListView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        initFootView();
    }


    /**
     * 初始化脚布局
     */
    private void initFootView() {
        footView = (RelativeLayout) View.inflate(mContext, R.layout.loadmore_listview_footview, null);
        footView.measure(0, 0);
        footViewHeight = footView.getMeasuredHeight();
        footView.setPadding(0, -footViewHeight, 0, 0);
        addFooterView(footView);
        setOnScrollListener(this);
    }

    /*
        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            int startX = 0;
            int startY = 0;
            int dx = 0;
            int dy = 0;
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) ev.getX();
                    startY = (int) ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    dx = (int) (startX - ev.getX());
                    dy = (int) (startY - ev.getY());

                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }


            return super.onTouchEvent(ev);
        }
    */
    public void setOnLoadMoreListener(OnLoadMoreListener l) {
        this.mOnLoadMoreListener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (onLoadMore) {
            return;
        }
        System.out.println("getLastVisiblePosition() "+getLastVisiblePosition()+"  getCount()  "+getCount());
        if (scrollState == SCROLL_STATE_IDLE  && getLastVisiblePosition() == (getCount() - 1) && canLoadMore) {
            if (mOnLoadMoreListener != null) {
                footView.setPadding(0,0,0,0);
                onLoadMore = true;
                setSelection(getCount());
                mOnLoadMoreListener.onLoadMore();
            }
        }
    }

    public void stopLoadMore() {
        footView.setPadding(0, -footViewHeight, 0, 0);
        onLoadMore = false;
    }

    public void setCanLoadMore(boolean b){
        canLoadMore = b;
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }


}
