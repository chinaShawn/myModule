package com.zte.shawn.lotterycheck.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zte.shawn.lotterycheck.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 10192984 on 2016/12/19.
 */
public class BaseToolBarActivity extends BaseActivity {

    private ImageView collapsingToolbarImage;
    protected CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initView(@LayoutRes int layoutResID) {
         contentLayout = (CoordinatorLayout) LayoutInflater.from(this).inflate(R.layout.activity_base, null, false);
         LayoutInflater.from(this).inflate(R.layout.appbar_layout, contentLayout, true);
         LayoutInflater.from(this).inflate(layoutResID, contentLayout, true);

        setContentView(contentLayout);
        collapsingToolbarLayout  = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarImage = (ImageView) findViewById(R.id.collapsing_image);

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

    protected void setToolbarBackgroundColor(@ColorInt int drawable) {
        this.toolbar.setBackgroundColor(drawable);
    }

    protected void settCollapsingToolbarSrc(@DrawableRes int resourceId) {
        this.collapsingToolbarImage.setImageResource(resourceId);
    }


    protected void settCollapsingToolbarVisible(@Visibility int visiblity) {
        this.collapsingToolbarImage.setVisibility(visiblity);
    }
    protected void setCollapsingToolbarListener(View.OnClickListener listener) {
        this.collapsingToolbarImage.setOnClickListener(listener);
    }

    @CallSuper
    protected void initCollapsingToolbar() {
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.GREEN);
    }

    @IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

}
