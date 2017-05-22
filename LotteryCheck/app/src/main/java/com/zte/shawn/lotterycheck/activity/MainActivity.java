package com.zte.shawn.lotterycheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zte.shawn.lotterycheck.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar( (Toolbar) findViewById(R.id.toolbar));
    }

    public void daLeTou(View view){
        startActivity(new Intent(this,MyDaLeTouActivity.class));
        //startActivity(new Intent(this,DataBindActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
