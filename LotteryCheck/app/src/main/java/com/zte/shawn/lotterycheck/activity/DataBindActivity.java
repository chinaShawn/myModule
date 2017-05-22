package com.zte.shawn.lotterycheck.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zte.shawn.lotterycheck.R;
import com.zte.shawn.lotterycheck.databinding.DataBindingBinding;
import com.zte.shawn.lotterycheck.viewmodel.viewmodel1;

/**
 * Created by 10192984 on 2017/5/8.
 */

public class DataBindActivity extends AppCompatActivity {
    private DataBindingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.data_binding);
        viewmodel1 viewmodel1 = new viewmodel1("tang","mingxiao");
        binding.setUser(viewmodel1);
    }
}
