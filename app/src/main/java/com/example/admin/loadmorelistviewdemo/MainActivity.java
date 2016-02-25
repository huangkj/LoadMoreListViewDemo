package com.example.admin.loadmorelistviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<String> datas;
    private MyAdapter myAdapter;
    private SwipeRefreshLayout swipe_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        swipe_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipe_layout.setOnRefreshListener(this);
        datas = new ArrayList();
        for (int i = 0; i < 20; i++) {
            datas.add("这是第" + i + "个数据");
        }
        final LoadMoreListView lv = (LoadMoreListView) findViewById(R.id.lv);
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
        lv.setCanLoadMore(true);

        lv.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(datas.size() >100){
                    lv.setCanLoadMore(false);
                    lv.stopLoadMore();
                    Toast.makeText(MainActivity.this,"没有数据了",0).show();
                    return;
                }else{
                    lv.setCanLoadMore(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.add("加载更多得到的数据");
                        datas.add("加载更多得到的数据");
                        datas.add("加载更多得到的数据");
                        myAdapter.notifyDataSetChanged();
                        lv.stopLoadMore();
                    }
                }, 2000);
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe_layout.setRefreshing(false);
                datas.add(0,"这是刷新出来的数据");
                myAdapter.notifyDataSetChanged();
            }
        }, 2000);

    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(datas.get(position));
            textView.setPadding(0,20,0,20);
            return textView;
        }
    }

}
