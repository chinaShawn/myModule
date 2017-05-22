package com.zte.shawn.lotterycheck.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by 10192984 on 2016/11/2.
 */
public class TextWatcherWithoutTimes implements TextWatcher {
    protected Context context;
    protected EditText red1;
    protected EditText red2;
    protected EditText red3;
    protected EditText red4;
    protected EditText red5;
    protected EditText green1;
    protected EditText green2;
    protected EditText date;


    public TextWatcherWithoutTimes(Context context, EditText red1, EditText red2, EditText red3, EditText red4, EditText red5, EditText green1, EditText green2, EditText date) {
        this.red1 = red1;
        this.red2 = red2;
        this.red3 = red3;
        this.red4 = red4;
        this.red5 = red5;
        this.green1 = green1;
        this.green2 = green2;
        this.date = date;
        this.context = context;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        {
            if (date.isFocused()) {
                if (s.toString().length() == 5) {
                    date.clearFocus();
                    red1.requestFocus();
                }
            } else if (s.toString().length() == 2) {
                if (red1.isFocused()) {
                    red1.clearFocus();
                    red2.requestFocus();
                } else if (red2.isFocused()) {
                    red2.clearFocus();
                    red3.requestFocus();
                } else if (red3.isFocused()) {
                    red3.clearFocus();
                    red4.requestFocus();
                } else if (red4.isFocused()) {
                    red4.clearFocus();
                    red5.requestFocus();
                } else if (red5.isFocused()) {
                    red5.clearFocus();
                    green1.requestFocus();
                } else if (green1.isFocused()) {
                    green1.clearFocus();
                    green2.requestFocus();
                } else if (green2.isFocused()) {
                    green2.clearFocus();
                    //InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(date.getWindowToken(),
                            //InputMethodManager.HIDE_NOT_ALWAYS);
                }

            }

        }
    }
}
