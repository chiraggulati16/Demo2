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
import java.util.Date;
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
    final ArrayList<Entry> histoEntries = new ArrayList();
    private SingleSelectToggleGroup buttonGroup;

    private int displayWidth;

    public IAxisValueFormatter XAxisFormatter;
    public final MonthSlashDayDateFormatter monthSlashDayXAxisFormatter = new MonthSlashDayDateFormatter(getContext());
    public final TimeDateFormatter dayCommaTimeDateFormatter = new TimeDateFormatter(getContext());
    public final MonthSlashYearFormatter monthSlashYearFormatter = new MonthSlashYearFormatter();

    private final static String CHART_URL_WEEK = "https://min-api.cryptocompare.com/data/histohour?fsym=%s&tsym=USD&limit=168&aggregate=1&e=CCCAGG";
    private final static String CHART_URL_ALL_DATA = "https://min-api.cryptocompare.com/data/histoday?fsym=%s&tsym=USD&allData=true";
    private final static String CHART_URL_YEAR = "https://min-api.cryptocompare.com/data/histoday?fsym=%s&tsym=USD&limit=183&aggregate=2";
    private final static String CHART_URL_MONTH = "https://min-api.cryptocompare.com/data/histohour?fsym=%s&tsym=USD&limit=240&aggregate=3";
    private final static String CHART_URL_3_MONTH = "https://min-api.cryptocompare.com/data/histohour?fsym=%s&tsym=USD&limit=240&aggregate=14";
    private final static String CHART_URL_1_DAY = "https://min-api.cryptocompare.com/data/histominute?fsym=%s&tsym=USD&limit=144&aggregate=10";

    SimpleDateFormat fullDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
    public Chart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chart, container, false);
        chartInterface = ChartApi.getChartClient().create(ChartInterface.class);

        Intent intent=getActivity().getIntent();
        final   String sym=intent.getStringExtra(CoinAdapter.Coin_Symbol);
        final TextView percentChangeText = view.findViewById(R.id.percent_change);
        final String change=intent.getStringExtra(CoinAdapter.Coin_Change_one);
        if(change.contains("-"))
        {
            percentChangeText.setTextColor(getResources().getColor(R.color.darkRed));
            percentChangeText.setText("24h Change: "+change+"%");

        }
        else
        {
            percentChangeText.setTextColor(getResources().getColor(R.color.darkGreen));
            percentChangeText.setText("24h Change: "+change+"%");

        }
        chartProgressBar=view.findViewById(R.id.chartProgressSpinner);

        mchart=view.findViewById(R.id.chart);
        mchart.setVisibility(View.VISIBLE);
        XAxisFormatter = dayCommaTimeDateFormatter;
        getLineData("/data/histominute?fsym="+sym+"&tsym=USD&limit=144&aggregate=10");

        buttonGroup = view.findViewById(R.id.chart_interval_button_grp);
        setDayChecked(Calendar.getInstance());
        buttonGroup.check(R.id.dayButton);
        buttonGroup.setOnCheckedChangeListener(new SingleSelectToggleGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SingleSelectToggleGroup group, int checkedId) {
                Calendar.getInstance();
                switch (checkedId) {
                    case R.id.dayButton:
                        setDayChecked(Calendar.getInstance());
                        getLineData("/data/histominute?fsym="+sym+"&tsym=USD&limit=144&aggregate=10");
                        break;
                    case R.id.weekButton:
                        setWeekChecked(Calendar.getInstance());
                        getLineData("/data/histohour?fsym="+sym+"&tsym=USD&limit=168&aggregate=1");
                        break;
                    case R.id.monthButton:
                        setMonthChecked(Calendar.getInstance());
                        getLineData("/data/histohour?fsym="+sym+"&tsym=USD&limit=240&aggregate=3");
                        break;
                    case R.id.threeMonthButton:
                        setThreeMonthChecked(Calendar.getInstance());
                        getLineData("/data/histohour?fsym="+sym+"&tsym=USD&limit=240&aggregate=14");
                        break;
                    case R.id.yearButton:
                        setYearChecked(Calendar.getInstance());
                        getLineData("/data/histoday?fsym="+sym+"&tsym=USD&limit=183&aggregate=2");
                        break;
                    case R.id.allTimeButton:
                        setAllTimeChecked();
                        getLineData("/data/histoday?fsym="+sym+"&tsym=USD&allData=true");
                        break;
                }
            }
        });


        return view;
    }

    private void setDayChecked(Calendar cal) {
        cal.clear();
        XAxisFormatter = dayCommaTimeDateFormatter;
    }

    private void setWeekChecked(Calendar cal) {
        cal.clear();
        XAxisFormatter = monthSlashDayXAxisFormatter;
    }

    private void setMonthChecked(Calendar cal) {
        cal.clear();
        XAxisFormatter = monthSlashDayXAxisFormatter;
    }

    private void setThreeMonthChecked(Calendar cal) {
        cal.clear();
        XAxisFormatter = monthSlashDayXAxisFormatter;
    }

    private void setYearChecked(Calendar cal) {
        cal.clear();
        XAxisFormatter = monthSlashYearFormatter;
    }

    private void setAllTimeChecked() {
        XAxisFormatter = monthSlashYearFormatter;
    }


    private void getLineData(final String url) {

        chartProgressBar.setVisibility(View.VISIBLE);
        Call<ChartData> call=chartInterface.getChartHistory(url);
       call.enqueue(new Callback<ChartData>() {
           @Override
           public void onResponse(Call<ChartData> call, Response<ChartData> response) {
             ChartData  history1=response.body();
               for(int i=0; i< history1.getData().size(); i++) {
                   double high=history1.getData().get(i).getHigh();
                   double low=history1.getData().get(i).getLow();
                   double open=history1.getData().get(i).getOpen();
                   double close=history1.getData().get(i).getClose();
                   double time=history1.getData().get(i).getTime();

                   Entry histoEntry = new Entry(
                           (float) (time*1000),
                           (float) close
                   );

                   histoEntries.add(histoEntry);
                   LineDataSet data=new LineDataSet(histoEntries,"USD");
                    //  data = (LineDataSet) mchart.getData().getDataSetByIndex(0);
                       LineData lineData = new LineData(data);
                       mchart.setData(lineData);
                       mchart.animateX(800);
                       formatChart(mchart, data);
                       mchart.notifyDataSetChanged();
                       mchart.invalidate();
                   DecimalFormat decimal = new DecimalFormat("0.00");
                   final TextView currPriceText = getActivity().findViewById(R.id.current_price);
                  double currPrice = histoEntries.get(histoEntries.size() - 1).getY();
                  String price=decimal.format(currPrice);
                   currPriceText.setText("$ " +price);
                   TextView chartDateTextView = getActivity().findViewById(R.id.graphFragmentDateTextView);
                   chartDateTextView.setText(getFormattedFullDate(histoEntries.get(histoEntries.size() - 1).getX()));

                   chartProgressBar.setVisibility(View.GONE);
               }

           }

           @Override
           public void onFailure(Call<ChartData> call, Throwable t) {

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
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAvoidFirstLastClipping(true);
        mchart.getAxisLeft().setEnabled(true);
        mchart.getAxisLeft().setDrawGridLines(false);
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
        dataSet.setColors(Color.WHITE);
        dataSet.setLineWidth(2);


    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
