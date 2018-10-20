package com.example.hp.demo.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.demo.Adapters.CoinAdapter;
import com.example.hp.demo.Apis.ApiClient;
import com.example.hp.demo.Apis.ChartApi;
import com.example.hp.demo.CMCChart.CmcChart;
import com.example.hp.demo.GraphHistory.ChartData;
import com.example.hp.demo.Interfaces.ApiInterface;
import com.example.hp.demo.Interfaces.ChartInterface;
import com.example.hp.demo.R;
import com.example.hp.demo.formatters.MonthSlashDayDateFormatter;
import com.example.hp.demo.formatters.MonthSlashYearFormatter;
import com.example.hp.demo.formatters.TimeDateFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chart extends Fragment implements OnChartValueSelectedListener {



    private LineChart mchart;
    private ChartInterface chartInterface;
    private ProgressBar chartProgressBar;
    private SingleSelectToggleGroup buttonGroup;

    public IAxisValueFormatter XAxisFormatter;
    public final MonthSlashDayDateFormatter monthSlashDayXAxisFormatter = new MonthSlashDayDateFormatter(getContext());
    public final TimeDateFormatter dayCommaTimeDateFormatter = new TimeDateFormatter(getContext());
    public final MonthSlashYearFormatter monthSlashYearFormatter = new MonthSlashYearFormatter();


    SimpleDateFormat fullDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
    public Chart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chart, container, false);
        chartInterface = ChartApi.getChartClient().create(ChartInterface.class);

        chartProgressBar=view.findViewById(R.id.chartProgressSpinner);

        mchart=view.findViewById(R.id.chart);
        mchart.setVisibility(View.VISIBLE);
        XAxisFormatter = dayCommaTimeDateFormatter;
        buttonGroup = view.findViewById(R.id.chart_interval_button_grp);
        buttonGroup.check(R.id.dayButton);
        buttonGroup.setOnCheckedChangeListener(new SingleSelectToggleGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SingleSelectToggleGroup group, int checkedId) {
                Calendar.getInstance();
                switch (checkedId) {
                    case R.id.dayButton:
                        setDayChecked(Calendar.getInstance());
                     //   getLineData("/data/histominute?fsym="+sym+"&tsym=USD&limit=144&aggregate=10");
                        break;
                    case R.id.weekButton:
                        setWeekChecked(Calendar.getInstance());
                       // getLineData("/data/histohour?fsym="+sym+"&tsym=USD&limit=168&aggregate=1");
                        break;
                    case R.id.monthButton:
                        setMonthChecked(Calendar.getInstance());
                        //getLineData("/data/histohour?fsym="+sym+"&tsym=USD&limit=240&aggregate=3");
                        break;
                    case R.id.threeMonthButton:
                        setThreeMonthChecked(Calendar.getInstance());
                        //getLineData("/data/histohour?fsym="+sym+"&tsym=USD&limit=240&aggregate=14");
                        break;
                    case R.id.yearButton:
                        setYearChecked(Calendar.getInstance());
                        //getLineData("/data/histoday?fsym="+sym+"&tsym=USD&limit=183&aggregate=2");
                        break;
                    case R.id.allTimeButton:
                        setAllTimeChecked();
                        //getLineData("/data/histoday?fsym="+sym+"&tsym=USD&allData=true");
                        break;
                }
            }
        });


        return view;
    }

    private void setDayChecked(Calendar cal) {
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        long startTime = cal.getTimeInMillis();
        cal.clear();
        Intent intent=getActivity().getIntent();
        final   String name=intent.getStringExtra(CoinAdapter.Coin_Website);

        getLineData(name+"/"+startTime+"/"+endTime);
        XAxisFormatter = dayCommaTimeDateFormatter;
    }

    private void setWeekChecked(Calendar cal) {
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_YEAR, -7);
        long startTime = cal.getTimeInMillis();
        cal.clear();
        Intent intent=getActivity().getIntent();
        final   String name=intent.getStringExtra(CoinAdapter.Coin_Website);

        getLineData(name+"/"+startTime+"/"+endTime);
        XAxisFormatter = monthSlashDayXAxisFormatter;
    }

    private void setMonthChecked(Calendar cal) {
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.MONTH, -1);
        long startTime = cal.getTimeInMillis();
        cal.clear();
        Intent intent=getActivity().getIntent();
        final   String name=intent.getStringExtra(CoinAdapter.Coin_Website);
        getLineData(name+"/"+startTime+"/"+endTime);
        XAxisFormatter = monthSlashDayXAxisFormatter;
    }

    private void setThreeMonthChecked(Calendar cal) {
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.MONTH, -3);
        long startTime = cal.getTimeInMillis();
        cal.clear();
        Intent intent=getActivity().getIntent();
        final   String name=intent.getStringExtra(CoinAdapter.Coin_Website);
        getLineData(name+"/"+startTime+"/"+endTime);
        XAxisFormatter = monthSlashDayXAxisFormatter;
    }

    private void setYearChecked(Calendar cal) {
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.YEAR, -1);
        long startTime = cal.getTimeInMillis();
        cal.clear();
        Intent intent=getActivity().getIntent();
        final   String name=intent.getStringExtra(CoinAdapter.Coin_Website);
        getLineData(name+"/"+startTime+"/"+endTime);
        XAxisFormatter = monthSlashYearFormatter;
    }

    private void setAllTimeChecked() {
        Intent intent=getActivity().getIntent();
        final   String name=intent.getStringExtra(CoinAdapter.Coin_Website);
        getLineData(name);
        XAxisFormatter = monthSlashYearFormatter;
    }


    private void getLineData(final String url) {
        chartProgressBar.setVisibility(View.VISIBLE);
        Call<CmcChart> call=chartInterface.getChartHistory(url);
       call.enqueue(new Callback<CmcChart>() {
           @Override
           public void onResponse(Call<CmcChart> call, Response<CmcChart> response) {
               TextView currPriceText = getActivity().findViewById(R.id.current_price);
               TextView chartDateTextView = getActivity().findViewById(R.id.graphFragmentDateTextView);
               TextView change=getActivity().findViewById(R.id.percent_change);
             CmcChart  history1=response.body();
               List<Entry> closePrices = new ArrayList<>();
               for (List<Float> priceTimeUnit : history1.getPriceUsd()) {
                   closePrices.add(new Entry(priceTimeUnit.get(0), priceTimeUnit.get(1)));
               }
               if (closePrices.size() == 0) {
                   mchart.setData(null);
                   mchart.setEnabled(false);
                   mchart.invalidate();
                   change.setText("");
                   currPriceText.setText("");
                   mchart.setNoDataText("No Data");
                   chartProgressBar.setVisibility(View.GONE);
                   return;
               }
                   LineDataSet data=new LineDataSet(closePrices,"USD");
                    //  data = (LineDataSet) mchart.getData().getDataSetByIndex(0);
                       LineData lineData = new LineData(data);
                       mchart.setData(lineData);
                       mchart.animateX(800);
                       formatChart(mchart, data);
                       mchart.notifyDataSetChanged();
                       mchart.invalidate();
                   DecimalFormat decimal = new DecimalFormat("0.00");

               float currPrice = closePrices.get(closePrices.size() - 1).getY();
               String price=decimal.format(currPrice);
               currPriceText.setText("$ " +price);
               chartDateTextView.setText(getFormattedFullDate(closePrices.get(closePrices.size() - 1).getX()));

               float firstPrice = closePrices.get(0).getY();
               for (Entry e: closePrices) {
                   if (firstPrice != 0) {
                       break;
                   } else {
                       firstPrice = e.getY();
                   }
               }
               float difference = (currPrice - firstPrice);
               float percentChange = (difference / firstPrice) * 100;
               String perChange=decimal.format(percentChange);

               if (perChange.contains("-")) {

                   String getchange="Change: "+perChange+"%";
                   change.setText(getchange);
                   change.setTextColor(getResources().getColor(R.color.darkRed));
               }
               else
               {
                   String getchange="Change: "+"+"+perChange+"%";
                   change.setText(getchange);
                   change.setTextColor(getResources().getColor(R.color.darkGreen));
               }

                   chartProgressBar.setVisibility(View.GONE);
               }

           @Override
           public void onFailure(Call<CmcChart> call, Throwable t) {

               Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();

           }
       });


   }

    private String getFormattedFullDate(float x) {
        Date date = new Date((long)x);
        return fullDateFormat.format(date);
    }

    private void formatChart(final LineChart mchart, LineDataSet dataSet) {
        XAxis xAxis = mchart.getXAxis();
        xAxis.setValueFormatter(XAxisFormatter);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(true);
        mchart.getAxisLeft().setEnabled(true);
        mchart.getAxisLeft().setDrawGridLines(false);
        mchart.getAxisLeft().setTextColor(Color.WHITE);
        mchart.getXAxis().setDrawGridLines(false);
        mchart.getAxisRight().setEnabled(false);
        mchart.getLegend().setEnabled(false);
        mchart.setDoubleTapToZoomEnabled(false);
        mchart.setScaleEnabled(false);
        mchart.getDescription().setEnabled(false);
        mchart.setContentDescription("");
        mchart.setNoDataText(getString(R.string.noChartDataString));
        mchart.setNoDataTextColor(R.color.darkRed);
        Legend legend = mchart.getLegend();
        legend.setEnabled(false);

        dataSet.setDrawCircles(false);
        dataSet.setDrawFilled(false);
        dataSet.setDrawValues(false);
        //dataSet.setFillColor(backgroundColor);
        dataSet.setColors(Color.GRAY);
        dataSet.setLineWidth(2);

        mchart.setOnChartValueSelectedListener(this);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
         DecimalFormat cryptoFormatter = new DecimalFormat("#,###.##");
        TextView currentPrice = getActivity().findViewById(R.id.current_price);
        TextView dateTextView = getActivity().findViewById(R.id.graphFragmentDateTextView);
        float price=e.getY();
        String selectPrice=cryptoFormatter.format(price);
        currentPrice.setText("$ "+selectPrice);
        dateTextView.setText(getFormattedFullDate(e.getX()));

    }

    @Override
    public void onNothingSelected() {

    }
}
