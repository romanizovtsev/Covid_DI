package com.example.coviddi;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.abs;

public class Presenter {
    private MainActivity view;
    private final model model;

    public Presenter(MainActivity view1)
    {
        this.view=view1;
        model=new model(this);
    }
    public void attachView(MainActivity mainActivity) {
        view = mainActivity;

    }

    public void detachView() {
        view = null;
    }
    public void loadInfo(int selected)
    {Log.e("Зашел в презентер",selected+"");
        String country=view.getCountry()[selected];
        Date dateNow = new Date(System.currentTimeMillis()-24*60*60*1000);
        Date DateYers=  new Date(System.currentTimeMillis()-2*24*60*60*1000);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(   "yyyy-MM-dd");
        model.DateNow=formatForDateNow.format(dateNow);
        model.getFromSQL(country,"deaths","2021-04-22");
        Log.e("ДАТА",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"confirmed",formatForDateNow.format(DateYers));
        model.getInfoToday(country,"confirmed",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"recovered",formatForDateNow.format(DateYers));
        model.getInfoToday(country,"recovered",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"deaths",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"deaths",formatForDateNow.format(DateYers));

    }
    public void showInfo(Map<String,String> map)
    {    for (Map.Entry<String, String> pair : map.entrySet())
    {
        String key = pair.getKey();                      //ключ
        switch(key)
        { case "confirmed": view.ShowNumbConf(pair.getValue()); break;
            case "recovered": view.ShowNumbRecov(pair.getValue()); break;
            case "deaths": view.ShowNumbDeath(pair.getValue()); break;
        }
    }


    }

public Context getContexts()
{
    return view.getApplicationContext();
}
    public void releaseGraph(){
        Integer dayOfMonth=20;
        Random random = new Random();
        Map<Calendar, Integer> graphMap = new HashMap<Calendar, Integer>();
        for (int i=0;i<7;i++)
            graphMap.put(new GregorianCalendar(2021,3,dayOfMonth+i), random.nextInt(11000)+3000);
        Map<Calendar, Integer> sortedMap = new TreeMap<>(graphMap);


        DataPoint[] Data= new DataPoint[sortedMap.size()];
        int i=0;
        for (Map.Entry<Calendar,Integer> pair : sortedMap.entrySet())
        {
            Calendar date = pair.getKey();
            Integer confirmed = pair.getValue();
            Data[i]=new DataPoint(date.get(Calendar.DAY_OF_MONTH), confirmed);
            i++;
        }
        LineGraphSeries series= new LineGraphSeries<>(Data);

        view.graphView.addSeries(series);
        view.graphView.getGridLabelRenderer().setNumHorizontalLabels(Data.length);
        //graphView.getViewport().setXAxisBoundsManual(true);
    }
}
