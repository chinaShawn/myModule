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

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.adapter.DaleTouManagerAdapter;
import com.zte.shawn.lotterycheck.adapter.SimpleItemTouchHelperCallback;
import com.zte.shawn.lotterycheck.adapter.TestAdapter;
import com.zte.shawn.lotterycheck.bean.Test;
import com.zte.shawn.lotterycheck.interfaces.OnItemClickListener;
import com.zte.shawn.lotterycheck.interfaces.OnStartDragListener;
import com.zte.shawn.lotterycheck.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by 10192984 on 2016/12/21.
 */
public class ChartActivity extends BaseToolBarActivity implements OnStartDragListener {
    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;
    private ItemTouchHelper touchHelper;
    private int ss = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        //initData();
        //drawCircle();
        drawPie();
    }

    public void setPostition(int postition){
        postition = 3;
    }

    private void initData() {
        Test test = new Test();
        List<Test.Point> green = new ArrayList<>();
        Test.Point point1 = test.new Point();
        point1.year = 2012;
        point1.score = 500;
        point1.color = "green";
        green.add(point1);

        Test.Point point2 = test.new Point();
        point2.year = 2013;
        point2.score = 560;
        point2.color = "green";
        green.add(point2);

        Test.Point point3 = test.new Point();
        point3.year = 2014;
        point3.score = 530;
        point3.color = "green";
        green.add(point3);

        Test.Point point4 = test.new Point();
        point4.year = 2015;
        point4.score = 600;
        point4.color = "green";
        green.add(point4);


        List<Test.Point> blue = new ArrayList<>();
        Test.Point point5 = test.new Point();
        point5.year = 2012;
        point5.score = 440;
        point5.color = "blue";
        blue.add(point5);

        Test.Point point6 = test.new Point();
        point6.year = 2013;
        point6.score = 450;
        point6.color = "blue";
        blue.add(point6);

        Test.Point point7 = test.new Point();
        point7.year = 2014;
        point7.score = 420;
        point7.color = "blue";
        blue.add(point7);

        Test.Point point8 = test.new Point();
        point8.year = 2015;
        point8.score = 480;
        point8.color = "blue";
        blue.add(point8);

        test.Calculate(blue, green);


        List<Test.Point> blueAnswer = test.getBuleAnswer();
        List<PointValue> values = new ArrayList<PointValue>();
        for (Test.Point point : blueAnswer) {
            values.add(new PointValue(point.year - 2011, point.y));
        }


        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setHasLabels(true);

        List<PointValue> values2 = new ArrayList<PointValue>();
        List<Test.Point> greenAnswer = test.getGreenAnswer();
        for (Test.Point point : greenAnswer) {
            values2.add(new PointValue(point.year - 2011, point.y));
        }
        // In most cased you can call data model methods in builder-pattern-like manner.
        Line line2 = new Line(values2).setColor(Color.GREEN).setHasLabels(true);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        lines.add(line2);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        //  data.setValueLabelBackgroundColor(Color.BLUE);
        //data.setValueLabelBackgroundEnabled(true);// 设置是否有数据背景
        //line.setFilled(true);//是否填充曲线的面积

        LineChartView chart = (LineChartView) findViewById(R.id.chart);
        chart.setLineChartData(data);
    }

    private void initView() {
        setContentView(R.layout.activity_chart);
        initCollapsingToolbar();
        setFab(R.layout.fab2);
        initFAB();
        initRecycleView();
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
                ChartActivity.this, DividerItemDecoration.VERTICAL_LIST));// 添加分割线。
        mRecyclerView.setAdapter(mAdapter = new TestAdapter(ChartActivity.this, this));
        mRecyclerView.setNestedScrollingEnabled(false);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(mAdapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
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

    protected void initCollapsingToolbar() {
        super.initCollapsingToolbar();
        collapsingToolbarLayout.setTitle("这是统计？");
        setCollapsingToolbarListener(v->startActivity(new Intent(ChartActivity.this, PerfectActivity.class)));
    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
            touchHelper.startDrag(viewHolder);
    }

    private void  drawCircle(){
        int[] lEntities = {30,4,16,50};
        int numValues = lEntities.length;
        int people_count = 0;

        List<SliceValue> values = new ArrayList<SliceValue>();
        SliceValue sliceValue = null;
        for (int i = 0; i < numValues; ++i) {
                //将数据进行格式化，保留两位小数
                sliceValue = new SliceValue();
                //设置每个扇形区域的值，float型
                sliceValue.setValue(lEntities[i]);
                //设置每个扇形区域的颜色
                sliceValue.setColor(R.color.red);


                //设置每个扇形区域的Lable，不设置的话，默认显示数值
                sliceValue.setLabel(String.valueOf(lEntities[i]));

            values.add(sliceValue);
        }
        //这些含义都和柱状图的类似

        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(true);

        //设置原因为空心圆
        data.setHasCenterCircle(true);

        PieChartView chart = (PieChartView) findViewById(R.id.pieCharView);
        chart.setViewportCalculationEnabled(true);//饼图自适应大小
        chart.setChartRotationEnabled(false);
        chart.setValueSelectionEnabled(false);
        chart.setActivated(false);

        chart.setPieChartData(data);

    }

    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };

    private void drawPie(){
        PieChart pieChart = (PieChart ) findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);//设置使用百分比
        pieChart.getDescription().setEnabled(false);//设置描述
        pieChart.setExtraOffsets(20, 15, 20, 15);
        pieChart.setDrawCenterText(false);//设置绘制环中文字
        pieChart.setRotationEnabled(false);
        pieChart.setSelected(false);
        pieChart.highlightValues(null);
        pieChart.setHoleRadius(75f);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.DKGRAY);

        //图例设置
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
        Map<String, Float> pieValues = new HashMap<>();
        pieValues.put("oo",23f);
        pieValues.put("ss",24f);
        pieValues.put("tt",13f);
        pieValues.put("xx",40f);
        //设置饼图数据
        setPieChartData(pieChart, pieValues);

        pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);//数据显示动画
    }

    /**
     * 设置饼图数据源
     */
    private void setPieChartData(PieChart pieChart, Map<String, Float> pieValues) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        Set set = pieValues.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);//设置饼块之间的间隔
        dataSet.setSelectionShift(0f);//设置饼块选中时偏离饼图中心的距离

        dataSet.setColors(PIE_COLORS);//设置饼块的颜色
        dataSet.setValueLinePart1OffsetPercentage(90f);//数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1Length(0.3f); //引线
        dataSet.setValueLinePart2Length(0.4f); //水平线
        dataSet.setValueLineColor(Color.BLUE);//设置连接线的颜色
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);

        pieChart.invalidate();
    }


}
