package com.zte.shawn.lotterycheck.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.zte.shawn.lotterycheck.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 10192984 on 2017/2/22.
 */
public class BaseActivity extends AppCompatActivity {
    protected AppBarLayout appBarLayout;
    protected Toolbar toolbar;
    protected CoordinatorLayout contentLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        initView(layoutResID);
    }

    protected void initView(@LayoutRes int layoutResID) {
        contentLayout = (CoordinatorLayout) LayoutInflater.from(this).inflate(R.layout.activity_base, null, false);
        LayoutInflater.from(this).inflate(R.layout.head_back, contentLayout, true);
        LayoutInflater.from(this).inflate(layoutResID, contentLayout, true);

        setContentView(contentLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void setFABListener(View.OnClickListener listener) {
        if (fab == null) {
            setFab(R.layout.fab);
        }
        fab.setOnClickListener(listener);
    }

    protected void setFab(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, contentLayout, true);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    protected  void setHeadView(@LayoutRes int layoutResID){
        appBarLayout.addView(LayoutInflater.from(this).inflate(layoutResID, null, false));
    }

    protected void setHeadView(View view){
        appBarLayout.addView(view);
    }
}
