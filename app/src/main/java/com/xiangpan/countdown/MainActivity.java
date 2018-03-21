package com.xiangpan.countdown;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SimpleItemAnimator;

import com.xiangpan.countdown.Adapter.CountDownAdapter;
import com.xiangpan.countdown.Bean.TimeDownBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView mRecycleListView;
    private List<TimeDownBean> mTimeDownBeanList;
    private CountDownAdapter mCountDownAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        for (int i = 1; i < 14; i++) {
            TimeDownBean timeDownBean = new TimeDownBean(55 * 1000 * i, "测试内容" + i);
            mTimeDownBeanList.add(timeDownBean);
        }
        for (int i = 1; i < 10; i++) {
            TimeDownBean timeDownBean = new TimeDownBean(0, "没有倒计时测试内容" + i);
            mTimeDownBeanList.add(timeDownBean);
        }

    }

    private void initView() {
        mRecycleListView = (RecyclerView) findViewById(R.id.recycle_list_view);
        mTimeDownBeanList = new ArrayList<>();
        mCountDownAdapter = new CountDownAdapter(this, mTimeDownBeanList);
        mRecycleListView.setHasFixedSize(true);
        mRecycleListView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleListView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ((SimpleItemAnimator) mRecycleListView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecycleListView.setAdapter(mCountDownAdapter);
    }


}
