package com.example.coviddi;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.coviddi.DataContract.Data;
import com.example.coviddi.DataContract.DataDbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.abs;

public class model {
    int flag2=0;
    DataDbHelper dh;
    String countryNow="";
    String DateNow;
    SQLiteDatabase db;
    Presenter presenter;
    public model(Presenter pres)
    {
        this.presenter=pres;
        this.dh=new DataDbHelper(presenter.getContexts());
    }
    Map<String,String> map=new HashMap<>();
    Map<String,Integer> mapGraph=new HashMap<>();
    ArrayList<String> GraphListDate=new ArrayList<>();
    ArrayList<Integer> GraphListValue=new ArrayList<>();
    int flag=0;
    public void getInfoTodayGraph(String country,String Date)
    {clearAll(country);
    String status="confirmed";
        NetworkService.getInstance()
                .getJSONApi()
                .getPost(country,status)
                .enqueue(new Callback<post1>() {
                    @Override
                    public void onResponse(@NonNull Call<post1> call, @NonNull Response<post1> response) {
                        post1 post = response.body();
                        GraphListDate.add(Date);
                        GraphListValue.add(Integer.parseInt(post.getAll().getDates().get(Date)));
                       if (GraphListValue.size()==7)
                       {    for(int i=0;i<6;i++)
                                 {
                                     mapGraph.put(GraphListDate.get(i),abs(GraphListValue.get(i+1)-GraphListValue.get(i)));
                                 }
                                 presenter.releaseGraph(mapGraph);
                                GraphListDate.clear();
                                mapGraph.clear();
                                GraphListValue.clear();


                       }


                    }
                    @Override
                    public void onFailure(@NonNull Call<post1> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
    public void getInfoToday(String country,String status,String Date)
    {Log.e("GET INFO",country);
        clearAll(country);
        NetworkService.getInstance()
                .getJSONApi()
                .getPost(country,status)
                .enqueue(new Callback<post1>() {
                    @Override
                    public void onResponse(@NonNull Call<post1> call, @NonNull Response<post1> response) {
                        post1 post = response.body();
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
                            presenter.showInfo(map);
                            flag=0;
                            putToSQL(country,map,DateNow);
                            Log.e("вызвана","Шоу инфо");
                            map.clear();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<post1> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
    public void putToSQL(String country,Map <String,String>map,String Date)
    {
        db = dh.getReadableDatabase();
        String formatString1 = "= '%s'";
        String insertQuerys1 =String.format(formatString1, DateNow);
        String insertQuerys2 =String.format(formatString1, country);
        String query = "SELECT * FROM "
                + Data.DateData.TABLE_NAME+" WHERE "+Data.DateData.COLUMN_DATE+insertQuerys1+" AND "+Data.DateData.COLUMN_COUNTRY+insertQuerys2;
        Cursor cursor2 = db.rawQuery(query, null);
        String confirmed="",recovered="",deaths="";
        if(cursor2.moveToNext()) {
Log.e("Такая запись", "Уже есть");
        }
        else {
            for (Map.Entry<String, String> pair : map.entrySet())
                {
                     String key = pair.getKey();                      //ключ
                        switch(key)
                             { case "confirmed": confirmed=pair.getValue(); break;
                                case "recovered": recovered=pair.getValue(); break;
                                case "deaths": deaths=pair.getValue(); break;
                             }
        }
       String formatString = " VALUES ('%s','%s','%d','%d','%d')";
        String insertQuery1 =String.format(formatString, country,DateNow,Integer.parseInt(confirmed),Integer.parseInt(recovered),Integer.parseInt(deaths));
            db = dh.getWritableDatabase();
            String insertQuery = "INSERT INTO " +
                    Data.DateData.TABLE_NAME +
                    " (" + Data.DateData.COLUMN_COUNTRY + ","
                    + Data.DateData.COLUMN_DATE + ","
                    + Data.DateData.COLUMN_CONFIRMED + ","
                    + Data.DateData.COLUMN_RECOVERED + ","
                    + Data.DateData.COLUMN_DEATHS + ")" + insertQuery1;
            db.execSQL(insertQuery);
        }
    }
    public void getFromSQL(String country,String status,String Date)
    {   Map<String,String>map=new HashMap<>();
        Log.e("LOG_TAG", "Зашел в sql");
       db = dh.getReadableDatabase();
        String formatString = "= '%s'";
        String insertQuery1 =String.format(formatString, DateNow);
        String insertQuery2 =String.format(formatString, country);
        String query = "SELECT " + Data.DateData._ID + ", "
                + Data.DateData.COLUMN_CONFIRMED +", "
                + Data.DateData.COLUMN_RECOVERED + ", "
                + Data.DateData.COLUMN_DEATHS+ " FROM "
                + Data.DateData.TABLE_NAME+" WHERE "+Data.DateData.COLUMN_DATE+insertQuery1+" AND "+Data.DateData.COLUMN_COUNTRY+insertQuery2;
        Cursor cursor2 = db.rawQuery(query, null);
        if(cursor2.moveToNext()) {
            int id = cursor2.getInt(cursor2
                    .getColumnIndex(Data.DateData._ID));
            String confirmed = cursor2.getString(cursor2
                    .getColumnIndex(Data.DateData.COLUMN_CONFIRMED));
            String recovered = cursor2.getString(cursor2
                    .getColumnIndex(Data.DateData.COLUMN_RECOVERED));
            String deaths = cursor2.getString(cursor2
                    .getColumnIndex(Data.DateData.COLUMN_DEATHS));
map.put("confirmed",confirmed);
            map.put("recovered",recovered);
            map.put("deaths",deaths);
            presenter.showInfo(map);
            map.clear();
        }
        cursor2.close();
    }
    public void clearAll(String country)
    {
        if (countryNow==country)
        {
            map.clear();
            mapGraph.clear();
            GraphListDate.clear();
            GraphListValue.clear();
        }
        countryNow=country;
    }
}
