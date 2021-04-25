package com.example.coviddi;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.graphics.PointF;
import android.view.Gravity;
import android.widget.Toast;



import androidx.annotation.NonNull;

import com.example.coviddi.DataContract.Data;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.abs;

public class Presenter {
    ImageButton button_settings;
    Button Read_info;
    Button Start_test;
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
    {
        Log.e("Зашел в презентер",selected+"");
        String country=view.getCountry()[selected];
        Date dateNow = new Date(System.currentTimeMillis()-24*60*60*1000);
        Date DateYers=  new Date(System.currentTimeMillis()-2*24*60*60*1000);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(   "yyyy-MM-dd");
        model.DateNow=formatForDateNow.format(dateNow);
        model.getFromSQL(country);
        Log.e("ДАТА",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"confirmed",formatForDateNow.format(DateYers));
        model.getInfoToday(country,"confirmed",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"recovered",formatForDateNow.format(DateYers));
        model.getInfoToday(country,"recovered",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"deaths",formatForDateNow.format(dateNow));
        model.getInfoToday(country,"deaths",formatForDateNow.format(DateYers));

    }
    public void showInfo(Map<String,String> map)
    {
        for (Map.Entry<String, String> pair : map.entrySet())
    {
        String key = pair.getKey();                      //ключ
        switch(key)
        {
            case "confirmed": view.ShowNumbConf(pair.getValue()); break;
            case "recovered": view.ShowNumbRecov(pair.getValue()); break;
            case "deaths": view.ShowNumbDeath(pair.getValue()); break;
        }
    }


    }

public Context getContexts()
{

    return view.getApplicationContext();
}
public void loadInfoGraph(int selected)
{
    String country=view.getCountry()[selected];
    Date date;
    SimpleDateFormat formatForDateNow = new SimpleDateFormat(   "yyyy-MM-dd");
    String[] dates=new String[8];
    ArrayList<String> DateMas=new ArrayList<>();
    for(int i=7;i>=0;i--) {

        dates[i] = formatForDateNow.format(new Date(System.currentTimeMillis() - (i + 1) * 24 * 60 * 60 * 1000));
        Log.e("Даты",dates[i]);
        DateMas.add(dates[i]);



}

    model.getInfoTodayGraph(country,DateMas);
}
    private Toast toastMessage;
    private PointsGraphSeries<DataPoint> dotSeries;

    public void releaseGraph(Map<String,Integer> map){

        /*String dayOfMonth0=DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(Calendar.getInstance().getTime());
        String[] parts = dayOfMonth0.split("\\."); // String array, each element is text between dots
   int dayOfMonth1=Integer.parseInt(parts[0])-1;

       Log.e("DDD",dayOfMonth1+"");
        Random random = new Random();*/
        view.graphView.removeAllSeries();
        Log.e(map.size()+"","Размер мапки");
        Map<Calendar, Integer> graphMap = new HashMap<Calendar, Integer>();
        for (Map.Entry<String, Integer> pair : map.entrySet())
        {
            String Date = pair.getKey();
            String[] parts = Date.split("-"); // String array, each element is text between dots
            int dayOfMonth1=Integer.parseInt(parts[2]);
            //ключ
            graphMap.put(new GregorianCalendar(2021,3,dayOfMonth1), pair.getValue());
        }

       /* Map<Calendar, Integer> graphMap = new HashMap<Calendar, Integer>();
        for (int i=0;i<7;i++)
            graphMap.put(new GregorianCalendar(2021,3,dayOfMonth1-i), random.nextInt(11000)+3000);

        */
        Map<Calendar, Integer> sortedMap = new TreeMap<>(graphMap);
        DataPoint[] Data= new DataPoint[sortedMap.size()];
        int i=0;
        for (Map.Entry<Calendar,Integer> pair : sortedMap.entrySet())
        {

            Calendar date = pair.getKey();
            Integer confirmed = pair.getValue();
            Log.e(date.toString(),confirmed+"");
            Data[i]=new DataPoint(date.get(Calendar.DAY_OF_MONTH), confirmed);
            i++;
        }

        LineGraphSeries series= new LineGraphSeries<>(Data);
        view.graphView.addSeries(series);

        dotSeries = new PointsGraphSeries<>();
        view.graphView.addSeries(dotSeries);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {


                int cases = (int)dataPoint.getY();
                int day = (int)dataPoint.getX();
                int lastDay = (int)series.getHighestValueX();
                int difference = lastDay - day;
                String date;
                SimpleDateFormat formatForDateNow = new SimpleDateFormat(   "dd.MM.yyyy");
                date=formatForDateNow.format(new Date(System.currentTimeMillis() -  (difference+1)*24 * 60 * 60 * 1000));
                String msg = date+"\n"+Integer.toString(cases);
                DataPoint[] Data= new DataPoint[1];
                Data[0]= new DataPoint(dataPoint.getX(),dataPoint.getY());
                dotSeries.resetData(Data);
                if (toastMessage != null) toastMessage.cancel();
                toastMessage=Toast.makeText(getContexts(), msg, Toast.LENGTH_LONG);
                toastMessage.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toastMessage.show();
                Data = null;


            }
        });
        view.graphView.getGridLabelRenderer().setNumHorizontalLabels(Data.length);
        graphMap.clear();
        sortedMap.clear();

        view.graphView.getViewport().setXAxisBoundsManual(true);
    }


    public void setDatesGraph()
    {String date1,date2;
        Date date;
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(   "dd.MM.yyyy");
        date1=formatForDateNow.format(new Date(System.currentTimeMillis() -  24 * 60 * 60 * 1000));
        date2=formatForDateNow.format(new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000));
        view.DateText.setText(date2+"-"+date1);
    }

    public void Settings_Open(){

    }

}
