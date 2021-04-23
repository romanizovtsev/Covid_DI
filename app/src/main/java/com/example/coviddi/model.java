package com.example.coviddi;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.coviddi.DataContract.Data;
import com.example.coviddi.DataContract.DataDbHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.abs;

public class model {

    DataDbHelper dh;
    String DateNow;
    SQLiteDatabase db;
    Presenter presenter;
    public model(Presenter pres)
    {
        this.presenter=pres;
        this.dh=new DataDbHelper(presenter.getContexts());
    }
    Map<String,String> map=new HashMap<>();
    int flag=0;
    public void getInfoToday(String country,String status,String Date)
    {Log.e("GET INFO",country);
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
    {        Log.e("щщщщщщщщщ","ЗАШЕЛ");
        db = dh.getReadableDatabase();
        String formatString1 = "= '%s'";
        String insertQuerys1 =String.format(formatString1, DateNow);
        String insertQuerys2 =String.format(formatString1, country);
        String query = "SELECT * FROM "
                + Data.DateData.TABLE_NAME+" WHERE "+Data.DateData.COLUMN_DATE+insertQuerys1+" AND "+Data.DateData.COLUMN_COUNTRY+insertQuerys2;
        //+ Data.DateData.TABLE_NAME+" WHERE 'Date' ='2021-04-22'";

        Cursor cursor2 = db.rawQuery(query, null);
        String confirmed="",recovered="",deaths="";
        if(cursor2.moveToNext()) {
            Log.e("В базе","Уже есть такая запись");
        }
        else {
            Log.e("В базе","Добавляем запись!!!!!!!!!");
            for (Map.Entry<String, String> pair : map.entrySet())
                {
                     String key = pair.getKey();                      //ключ
                        switch(key)
                             { case "confirmed": confirmed=pair.getValue(); break;
                                case "recovered": recovered=pair.getValue(); break;
                                case "deaths": deaths=pair.getValue(); break;
                             }
            Log.e("BLAA2",deaths+"");
        }


      /* String formatString = "INSERT INTO '%s' ('%s','%s','%s','%s','%s') VALUES ('%s','%s','%d','%d','%d')";
        String insertQuery =String.format(formatString, Data.DateData.TABLE_NAME, Data.DateData.COLUMN_COUNTRY, Data.DateData.COLUMN_DATE,
                Data.DateData.COLUMN_CONFIRMED,Data.DateData.COLUMN_RECOVERED,Data.DateData.COLUMN_DEATHS,country,Date,Integer.parseInt(confirmed),Integer.parseInt(recovered),Integer.parseInt(deaths));
      Log.e(insertQuery,"!");*/
         // String insertQuery="DELETE FROM " +Data.DateData.TABLE_NAME;


       String formatString = " VALUES ('%s','%s','%d','%d','%d')";
        String insertQuery1 =String.format(formatString, country,DateNow,Integer.parseInt(confirmed),Integer.parseInt(recovered),Integer.parseInt(deaths));

            db = dh.getWritableDatabase();
            String insertQuery = "INSERT INTO " +
                    Data.DateData.TABLE_NAME +
                    " (" + Data.DateData.COLUMN_COUNTRY + ","
                    + Data.DateData.COLUMN_DATE + ","
                    + Data.DateData.COLUMN_CONFIRMED + ","
                    + Data.DateData.COLUMN_RECOVERED + ","
                    //+ Data.DateData.COLUMN_DEATHS+") VALUES ('Germany', '2021-04-22','200','200','200')";
                    + Data.DateData.COLUMN_DEATHS + ")" + insertQuery1;
            db.execSQL(insertQuery);
        }
    }
    public void getFromSQL(String country,String status,String Date)
    {   Map<String,String>map=new HashMap<>();
        Log.e("LOG_TAG", "Зашел в sql");

        // Создаем объект ContentValues, где имена столбцов ключи,
        // а информация о госте является значениями ключей
      /*  ContentValues values = new ContentValues();
        values.put(Data.DateData.COLUMN_COUNTRY, "Germany");
        values.put(Data.DateData.COLUMN_DATE, "2021-04-21");
        values.put(Data.DateData.COLUMN_CONFIRMED, 2000);
        values.put(Data.DateData.COLUMN_RECOVERED, 2000);
        values.put(Data.DateData.COLUMN_DEATHS, 2000);

        long newRowId = db.insert(Data.DateData.TABLE_NAME, null, values);
        /
       */

       db = dh.getReadableDatabase();
        String formatString = "= '%s'";
        String insertQuery1 =String.format(formatString, DateNow);
        String insertQuery2 =String.format(formatString, country);
       // String formatString = " '%s' ='%s' AND '%s' ='%s'";
      //  String insertQuery1 =String.format(formatString,Data.DateData.COLUMN_DATE, DateNow,Data.DateData.COLUMN_COUNTRY, country);
        String query = "SELECT " + Data.DateData._ID + ", "
                + Data.DateData.COLUMN_CONFIRMED +", "
                + Data.DateData.COLUMN_RECOVERED + ", "
                + Data.DateData.COLUMN_DEATHS+ " FROM "
                + Data.DateData.TABLE_NAME+" WHERE "+Data.DateData.COLUMN_DATE+insertQuery1+" AND "+Data.DateData.COLUMN_COUNTRY+insertQuery2;
                //+ Data.DateData.TABLE_NAME+" WHERE 'Date' ='2021-04-22'";

        Cursor cursor2 = db.rawQuery(query, null);
        Log.e("LOG_TAG", "Зашел в sql2");
        if(cursor2.moveToNext()) {
            Log.e("LOG_TAG", "Зашел в sql3");
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
            Log.e("BLAA3",deaths+"");
            presenter.showInfo(map);
           // Log.e("LOG_TAG", "ROW " + id + " HAS NAME " + name);
        }
        cursor2.close();
    }
}
