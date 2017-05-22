package com.zte.shawn.lotterycheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.adapter.DaleTouManagerAdapter;
import com.zte.shawn.lotterycheck.bean.DaLeTou;
import com.zte.shawn.lotterycheck.database.DaleTouDataBase;
import com.zte.shawn.lotterycheck.progress.CircleProgressDialog;
import com.zte.shawn.lotterycheck.util.DividerItemDecoration;
import com.zte.shawn.lotterycheck.util.MyToast;
import com.zte.shawn.networkmodule.bean.DaLeTouNetWork;
import com.zte.shawn.networkmodule.bean.KaiCaiWangBaseBean;
import com.zte.shawn.shuangseqiu.data.repository.DaleTouRepository;
import com.zte.shawn.utiltools.EventMessage.DaleTouEvent;
import com.zte.shawn.utiltools.constants.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10192984 on 2016/12/19.
 */
public class DaleTouManagerActivity extends BaseToolBarActivity {

    private SwipeMenuRecyclerView mRecyclerView;
    private DaleTouManagerAdapter mAdapter;
    private List<DaLeTou> list = new ArrayList<>();
    private CircleProgressDialog progressDialog;
    private KaiCaiWangBaseBean<DaLeTouNetWork> daLeTouNetWork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daletou_manager);
        initRecycleView();
        initCollapsingToolbar();
        initData();
        EventBus.getDefault().register(this); //第1步: 注册
    }

    private void initData() {
        list.clear();
        list.addAll(DaleTouDataBase.getInstance().getAllDaleTou());
        mAdapter.notifyDataSetChanged();
        progressDialog = new CircleProgressDialog(DaleTouManagerActivity.this, "正在同步哦", ((precent, total, done) -> progressDialog.updateProgress(precent)));
    }

    private void initRecycleView() {
        mRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycle_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);// 布局管理器。
        mRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                DaleTouManagerActivity.this, DividerItemDecoration.VERTICAL_LIST));// 添加分割线。
        mRecyclerView.setAdapter(mAdapter = new DaleTouManagerAdapter(DaleTouManagerActivity.this, list));
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    protected void initCollapsingToolbar() {
        super.initCollapsingToolbar();
        collapsingToolbarLayout.setTitle("已出发财符");
        setCollapsingToolbarListener((v) -> startActivity(new Intent(DaleTouManagerActivity.this, ChartActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manager_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) //得到被点击的item的itemId
        {
            case R.id.action_sync:  //对应的ID就是在add方法中所设定的Id
                progressDialog.onShow(DaleTouManagerActivity.this);
                DaleTouRepository.getInstance().getDaleTouFromNetWork(getApplicationContext());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //在注册了的Activity中,声明处理事件的方法
    @Subscribe(threadMode = ThreadMode.MAIN) //第2步:注册一个在后台线程执行的方法,用于接收事件
    public void onGetDateLouSuccess(DaleTouEvent event) {//参数必须是ClassEvent类型, 否则不会调用此方法
        Log.i("DaLeTouManagerActivity", "get message from EventBus on Success");
        daLeTouNetWork = (KaiCaiWangBaseBean<DaLeTouNetWork>) event.getValue();
        Toast.makeText(DaleTouManagerActivity.this, "数据可能有3-6分钟延迟", Toast.LENGTH_SHORT).show();
        saveDaLeTou();
        list.clear();
        list.addAll(DaleTouDataBase.getInstance().getAllDaleTou());
        mAdapter.notifyDataSetChanged();
        progressDialog.onDismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetDateLouError(DaleTouEvent event) {
        progressDialog.onDismiss();
        MyToast.showToast(getApplicationContext(), "获取数据失败，请稍候再试", AppConstants.TOAST_TIME);
    }


    private void saveDaLeTou() {
        for (DaLeTouNetWork leTouNetWork : daLeTouNetWork.getData()) {
            DaLeTou daLeTou = new DaLeTou();
            String openCode = leTouNetWork.getOpencode();
            String[] tmp = openCode.split("\\+");
            String[] red = tmp[0].split(",");
            String[] green = tmp[1].split(",");
            daLeTou.setDateNum(Integer.valueOf(leTouNetWork.getExpect()));
            daLeTou.setGreen1(Integer.valueOf(green[0]));
            daLeTou.setGreen2(Integer.valueOf(green[1]));
            daLeTou.setRead1(Integer.valueOf(red[0]));
            daLeTou.setRead2(Integer.valueOf(red[1]));
            daLeTou.setRead3(Integer.valueOf(red[2]));
            daLeTou.setRead4(Integer.valueOf(red[3]));
            daLeTou.setRead5(Integer.valueOf(red[4]));
            daLeTou.setOpentime(leTouNetWork.getOpentime());
            DaleTouDataBase.getInstance().saveDaLeTou(daLeTou);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
