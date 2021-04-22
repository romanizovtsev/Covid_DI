package com.example.coviddi;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.abs;

public class Presenter {
    private MainActivity view;
    private final model model;
   Map<String,String> map=new HashMap<>();
    int flag=0;
    public Presenter(MainActivity view1)
    {
        this.view=view1;
        model=new model();
    }
    public void attachView(MainActivity mainActivity) {
        view = mainActivity;

    }

    public void detachView() {
        view = null;
    }
    public void getInfoToday(String country,String status,String Date)
    {

        NetworkService.getInstance()
                .getJSONApi()
                .getPost(country,status)
                .enqueue(new Callback<post1>() {

                    @Override
                    public void onResponse(@NonNull Call<post1> call, @NonNull Response<post1> response) {
                        post1 post = response.body();
                                //view.showInfo(status,post.getAll().getDates().get(Date)+"");
                        if(!map.containsKey(status)) {
                            map.put(status, post.getAll().getDates().get(Date) + "");
                            Log.e(status,"Первый занос");

                        }
                        else {
                            map.put(status, abs(Integer.parseInt(post.getAll().getDates().get(Date) + "") - Integer.parseInt(map.get(status))) + "");
                        Log.e("Выполнено вычитание", status);
                        flag++;
                        Log.e("dddddddddddddd",flag+"");

                        }
                        if((map.size()==3)&&(flag==3)){
                            view.showInfo(map);
                            flag=0;
                            Log.e("вызвана","Шоу инфо");
                        }

                    }


                    @Override
                    public void onFailure(@NonNull Call<post1> call, @NonNull Throwable t) {


                        t.printStackTrace();

                    }
                });



    }
    public void raznost (String status,int value)

    {

    }
}
