package com.zte.shawn.lotterycheck.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zte.shawn.lotterycheck.database.DaleTouDataBase;
import com.zte.shawn.lotterycheck.MyApp;
import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.adapter.DaleTouResultAdapter;
import com.zte.shawn.lotterycheck.bean.DaLeTou;
import com.zte.shawn.lotterycheck.bean.MyDaLeTou;
import com.zte.shawn.lotterycheck.constants.Constants;
import com.zte.shawn.lotterycheck.util.DividerItemDecoration;
import com.zte.shawn.lotterycheck.util.TextWatcherWithoutTimes;
import com.zte.shawn.lotterycheck.util.Utils;

import java.util.List;

/**
 * Created by 10192984 on 2016/11/1.
 */
public class DaleTouResultActivity extends BaseToolBarActivity {
    private String TAG = "DaleTouResultActivity";
    private MyDaLeTou myDaLeTou;
    private PopupWindow popupWindow;
    private SwipeMenuRecyclerView mRecyclerView;
    private DaleTouResultAdapter mAdapter;
    private List<DaLeTou> list;

    private EditText red1;
    private EditText red2;
    private EditText red3;
    private EditText red4;
    private EditText red5;
    private EditText green1;
    private EditText green2;
    private EditText date;
    private TextWatcherWithoutTimes tw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();

    }

    private void initView() {
        setContentView(R.layout.activity_daletou_result);
        initCollapsingToolbar();
        setFab(R.layout.fab2);
        ((TextView)findViewById(R.id.date_mydaletou)).setText(Utils.caculateIsOver(myDaLeTou,list.size()>0?list.get(0):null));
        preparePopupWindow();
        initFAB();

        initRecycleView();
        initMyDaleTouView();
    }

    private void initFAB() {
        final com.getbase.floatingactionbutton.FloatingActionButton actionB = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_b);
        final com.getbase.floatingactionbutton.FloatingActionsMenu menu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        final com.getbase.floatingactionbutton.FloatingActionButton actionA = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_a);
        actionB.setVisibility(View.GONE);
        actionA.setTitle("添加已出期数");
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
                menu.collapse();
            }
        });
    }

    protected void initCollapsingToolbar() {
        super.initCollapsingToolbar();
        collapsingToolbarLayout.setTitle("您中奖了吗");
    }

    private void initRecycleView() {
        mRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycle_list_view);
        // 设置菜单创建器。
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);// 布局管理器。
        mRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                DaleTouResultActivity.this, DividerItemDecoration.VERTICAL_LIST));// 添加分割线。
        mRecyclerView.setAdapter(mAdapter = new DaleTouResultAdapter(DaleTouResultActivity.this, list, myDaLeTou));
       mRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initMyDaleTouView() {
        ((TextView)findViewById(R.id.red1)).setText(myDaLeTou.getRead1()+"");
        ((TextView)findViewById(R.id.red2)).setText(myDaLeTou.getRead2()+"");
        ((TextView)findViewById(R.id.red3)).setText(myDaLeTou.getRead3()+"");
        ((TextView)findViewById(R.id.red4)).setText(myDaLeTou.getRead4()+"");
        ((TextView)findViewById(R.id.red5)).setText(myDaLeTou.getRead5()+"");
        ((TextView)findViewById(R.id.green1)).setText(myDaLeTou.getGreen1()+"");
        ((TextView)findViewById(R.id.green2)).setText(myDaLeTou.getGreen2()+"");
        ((TextView)findViewById(R.id.date)).setText(myDaLeTou.getDateNum()+"");
        ((TextView)findViewById(R.id.times)).setText(myDaLeTou.getTimes()+"");
    }


    private void initData() {
        Intent it = getIntent();
        myDaLeTou = (MyDaLeTou) it.getSerializableExtra(Constants.intent_data);
        list = DaleTouDataBase.getInstance().getAllDaleTouInChoice(myDaLeTou.getDateNum(),myDaLeTou.getTimes());
    }

    private void preparePopupWindow() {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(DaleTouResultActivity.this).inflate(
                R.layout.popuwindow_save_daletou, null);

        setTextWatcher(contentView);
        // 设置按钮的点击事件
        Button button = (Button) contentView.findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveDaLeTou(mRecyclerView);
            }
        });

        popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
       // popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);



        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.white_shape));//getResources().getDrawable()
    }

    private void showPopupWindow(View view) {
        // 设置好参数之后再show
        popupWindow.showAtLocation((View) view.getParent(), Gravity.CENTER, 0, 0);
    }


    private void setTextWatcher(View contentView) {
        red1 = (EditText) contentView.findViewById(R.id.red1);
        red2 = (EditText) contentView.findViewById(R.id.red2);
        red3 = (EditText) contentView.findViewById(R.id.red3);
        red4 = (EditText) contentView.findViewById(R.id.red4);
        red5 = (EditText) contentView.findViewById(R.id.red5);
        green1 = (EditText) contentView.findViewById(R.id.green1);
        green2 = (EditText) contentView.findViewById(R.id.green2);
        date = (EditText) contentView.findViewById(R.id.date);
        tw = new TextWatcherWithoutTimes(DaleTouResultActivity.this, red1, red2, red3, red4, red5, green1, green2, date);

        red1.addTextChangedListener(tw);
        red2.addTextChangedListener(tw);
        red3.addTextChangedListener(tw);
        red4.addTextChangedListener(tw);
        red5.addTextChangedListener(tw);
        green1.addTextChangedListener(tw);
        green2.addTextChangedListener(tw);
        date.addTextChangedListener(tw);
    }

    public void saveDaLeTou(View view) {
        if (!checkValide()) {
            Snackbar.make(view, "请输入完整，大哥", Snackbar.LENGTH_LONG).show();
        }else if(!checkDataIsOut()){
            Snackbar.make(view, "期数不在此范围内", Snackbar.LENGTH_LONG).show();
        }else {
            int red1 = Integer.valueOf(this.red1.getText().toString());
            int red2 = Integer.valueOf(this.red2.getText().toString());
            int red3 = Integer.valueOf(this.red3.getText().toString());
            int red4 = Integer.valueOf(this.red4.getText().toString());
            int red5 = Integer.valueOf(this.red5.getText().toString());
            int green1 = Integer.valueOf(this.green1.getText().toString());
            int green2 = Integer.valueOf(this.green2.getText().toString());
            int date = Integer.valueOf(this.date.getText().toString());

            DaLeTou daLeTou = new DaLeTou(date,red1, red2, red3, red4, red5, green1, green2,"");
            DaleTouDataBase.getInstance().saveDaLeTou(daLeTou);
            clearAllEditText();
            list.add(0, daLeTou);
            mAdapter.notifyItemInserted(0);
        }
    }

    private boolean checkDataIsOut(){
        int date = Integer.valueOf(this.date.getText().toString());
        int myDate = myDaLeTou.getDateNum();
        int times = myDaLeTou.getTimes();

        if(date>=myDate && date<myDate+times){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkValide() {
        if ("".equals(red1.getText().toString())
                || "".equals(red2.getText().toString())
                || "".equals(red3.getText().toString())
                || "".equals(red4.getText().toString())
                || "".equals(red5.getText().toString())
                || "".equals(green1.getText().toString())
                || "".equals(green2.getText().toString())
                || "".equals(date.getText().toString())
                ) {
            return false;
        }
        return true;
    }

    private void clearAllEditText() {
        red1.setText("");
        red2.setText("");
        red3.setText("");
        red4.setText("");
        red5.setText("");
        green1.setText("");
        green2.setText("");
        date.setText("");
    }


    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;// getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem deleteItem = new SwipeMenuItem(DaleTouResultActivity.this)
                    .setBackgroundDrawable(R.drawable.white_shape)
//                    .setImage(R.mipmap.ic_action_delete) // 图标。
                    .setText(R.string.delete) // 文字。
                    .setTextColor(Color.BLUE) // 文字颜色。
                    //.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size)) // 文字大小。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(DaleTouResultActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                if (menuPosition == 0 ) {// 删除按钮被点击。
                    Log.i(TAG, "onItemClick: 删除");

                    //删除mItems数据
                    DaleTouDataBase.getInstance().deleteDaLeTou(list.get(adapterPosition));
                    //删除RecyclerView列表对应item
                    list.remove(adapterPosition);
                    mAdapter.notifyItemRemoved(adapterPosition);
                }
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(DaleTouResultActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
