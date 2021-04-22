package com.example.coviddi;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public void loadInfo(String country)
    {
        Date dateNow = new Date(System.currentTimeMillis()-24*60*60*1000);
        Date DateYers=  new Date(System.currentTimeMillis()-2*24*60*60*1000);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(   "yyyy-MM-dd");
        model.getFromSQL(country,"deaths",formatForDateNow.format(DateYers));
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
}
