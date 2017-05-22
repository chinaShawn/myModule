package com.zte.shawn.lotterycheck.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.adapter.PerfectAdapter;
import com.zte.shawn.lotterycheck.adapter.SimpleItemTouchHelperCallback;
import com.zte.shawn.lotterycheck.adapter.TestAdapter;
import com.zte.shawn.lotterycheck.util.DividerItemDecoration;

/**
 * Created by 10192984 on 2017/2/22.
 */
public class PerfectActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PerfectAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.test);
        //setHeadView(imageView);
        //setFab(R.layout.fab2);
        //initFAB();
        initRecycleView();
    }


    private void initFAB() {
        final View actionB = findViewById(R.id.action_b);

        com.getbase.floatingactionbutton.FloatingActionButton actionC = new com.getbase.floatingactionbutton.FloatingActionButton(getBaseContext());
        actionC.setTitle("Hide/Show Action above");
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(actionC);

        final com.getbase.floatingactionbutton.FloatingActionButton actionA = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionA.setTitle("Action A clicked");
            }
        });
    }

    private void initRecycleView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_list_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);// 布局管理器。
        mRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                PerfectActivity.this, DividerItemDecoration.VERTICAL_LIST));// 添加分割线。
        mRecyclerView.setAdapter(mAdapter = new PerfectAdapter(PerfectActivity.this));
        mRecyclerView.setNestedScrollingEnabled(false);
    }


}
