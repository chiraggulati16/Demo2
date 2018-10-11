package com.example.hp.demo.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.demo.Adapters.CoinAdapter;
import com.example.hp.demo.Apis.ApiClient;
import com.example.hp.demo.Apis.ChartApi;
import com.example.hp.demo.GraphHistory.ChartData;
import com.example.hp.demo.Interfaces.ApiInterface;
import com.example.hp.demo.Interfaces.ChartInterface;
import com.example.hp.demo.R;
import com.example.hp.demo.formatters.TimeDateFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chart extends Fragment {



    private LineChart mchart;
    private ChartInterface chartInterface;
    public Chart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chart, container, false);
        chartInterface = ChartApi.chartApiClient().create(ChartInterface.class);

        Intent intent=getActivity().getIntent();
        String sym=intent.getStringExtra(CoinAdapter.Coin_Symbol);
        mchart=view.findViewById(R.id.chart);
        mchart.setVisibility(View.VISIBLE);
        LineDataSet dataSet = getLineData(sym);
        LineData lineData = new LineData(dataSet);
        mchart.setData(lineData);
        formatChart(mchart, dataSet);
        mchart.invalidate();
        return view;
    }


    private LineDataSet getLineData(final String sym) {
         final ArrayList<Entry> histoEntries = new ArrayList();
        Call<ChartData> call=chartInterface.getHistory("/data/histohour?fsym="+sym+"&tsym=USD&limit=60&aggregate=3&e=CCCAGG");
       call.enqueue(new Callback<ChartData>() {
           @Override
           public void onResponse(Call<ChartData> call, Response<ChartData> response) {
             ChartData  history1=response.body();
               for(int i=0; i<history1.getData().size(); i++) {
                   DecimalFormat decimal = new DecimalFormat("0.00");
                   Double high=history1.getData().get(i).getHigh();
                   Double low=history1.getData().get(i).getLow();
                   Double open=history1.getData().get(i).getOpen();
                   Double close=history1.getData().get(i).getClose();
                   double time=history1.getData().get(i).getTime();

                   Double highValue= Double.valueOf(decimal.format(high));
                   Double lowValue= Double.valueOf(decimal.format(low));
                   Double openValue= Double.valueOf(decimal.format(open));
                   Double closeValue= Double.valueOf(decimal.format(close));

                   Entry histoEntry = new Entry(
                           (float) time,
                           (float) (lowValue + highValue)/2.0f
                   );

                   histoEntries.add(histoEntry);

               }

           }

           @Override
           public void onFailure(Call<ChartData> call, Throwable t) {

           }
       });
        return new LineDataSet(histoEntries, getString(R.string.history_label));

   }

    private void formatChart(LineChart mchart, LineDataSet dataSet) {
        XAxis xAxis = mchart.getXAxis();
        xAxis.setTextColor(Color.WHITE);

        xAxis.setValueFormatter(new TimeDateFormatter(getContext()));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        xAxis.setAxisLineColor(R.color.white);
        xAxis.setAxisLineWidth(1.5f);
        //xAxis.enableGridDashedLine(20, 10, 0);

        YAxis yAxisLeft = mchart.getAxisLeft();
        yAxisLeft.setAxisLineColor(R.color.white);
        yAxisLeft.setTextColor(Color.WHITE);
        yAxisLeft.setAxisLineWidth(1.5f);

        mchart.setDrawGridBackground(false);

        //  chart.setBackgroundColor(backgroundColor);

        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(true);
        mchart.setPinchZoom(false);
        mchart.getDescription().setEnabled(false);

        Legend legend = mchart.getLegend();
        legend.setEnabled(false);

        dataSet.setDrawCircles(false);
        dataSet.setDrawFilled(false);
        dataSet.setDrawValues(false);
        //dataSet.setFillColor(backgroundColor);
        dataSet.setColors(Color.WHITE);
        dataSet.setLineWidth(2);

    }

}
