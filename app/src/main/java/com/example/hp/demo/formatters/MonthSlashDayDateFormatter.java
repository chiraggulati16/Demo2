package com.example.hp.demo.formatters;

import android.content.Context;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ryan on 8/10/2017.
 */

public class MonthSlashDayDateFormatter implements IAxisValueFormatter {

    private Context mContext;

    public MonthSlashDayDateFormatter(Context context) {
        this.mContext=context;
    }


    @Override
    public String getFormattedValue(float unixSeconds, AxisBase axis) {
        Date date = new Date((long)unixSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.ENGLISH);
        return sdf.format(date);
    }
}
