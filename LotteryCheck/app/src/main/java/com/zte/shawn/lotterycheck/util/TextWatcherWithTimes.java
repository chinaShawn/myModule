package com.zte.shawn.lotterycheck.util;

import android.content.Context;
import android.text.Editable;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by 10192984 on 2016/11/2.
 */
public class TextWatcherWithTimes extends TextWatcherWithoutTimes {

    private EditText times;


    public TextWatcherWithTimes(Context context, EditText red1, EditText red2, EditText red3, EditText red4, EditText red5, EditText green1, EditText green2, EditText date, EditText times) {
        super(context, red1, red2, red3, red4, red5, green1, green2, date);
        this.times = times;
    }


    @Override
    public void afterTextChanged(Editable s) {
        {
            if (date.isFocused()) {
                if (s.toString().length() == 5) {
                    date.clearFocus();
                    times.requestFocus();
                }
            }
            if (s.toString().length() == 2) {
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
                    date.requestFocus();
                } else if (times.isFocused()) {
                    times.clearFocus();
                }
            }
        }
    }
}
